package strategie.impl;

import individu.Person;

import java.util.List;
import java.util.Map;

import strategie.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class Sondeur implements StrategieI {

	@Override
	public boolean goToTheBar(final Person me,
			final List<Map<Person, Boolean>> historiqueDesTours) {
		// je reste chez me, ensuite je vais deux fois au bar. Si la majorité
		// reste chez eux, je reste également chez me (sinon je vais au bar).

		final int nbTour = historiqueDesTours.size();
		if (nbTour < 1) {
			// Stay at home at first round
			return false;
		} else if (nbTour <= 3) {
			// Go to the bar at first round
			return true;
		} else {

			int nbInviduAuBarTotal = 0;
			int nbInviduTotal = 0;
			final Map<Person, Boolean> tour = historiqueDesTours
					.get(historiqueDesTours.size() - 1);
			for (final Person i : tour.keySet()) {
				final boolean estPartiAuBar = tour.get(i);
				if (estPartiAuBar) {
					nbInviduAuBarTotal++;
				}
				nbInviduTotal++;
			}

			final double ratioNbPersonAuBarSurNbPersonDuTour = (double) nbInviduTotal
					/ nbInviduAuBarTotal;
			System.out.print("( nbInviduTotal /  nbInviduAuBarTotal) = ("
					+ nbInviduTotal + "/" + nbInviduAuBarTotal + ") = ");
			System.out.format("%.3f : ",
					ratioNbPersonAuBarSurNbPersonDuTour);
			System.out
					.println(!(ratioNbPersonAuBarSurNbPersonDuTour >= 0.5d));
			return !(ratioNbPersonAuBarSurNbPersonDuTour >= 0.5d);
		}
	}
}
