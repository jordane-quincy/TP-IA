package strategie.impl;

import individu.Individu;

import java.util.List;
import java.util.Map;

import strategie.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class Sondeur implements StrategieI {

	@Override
	public boolean allerAuBar(final Individu moi,
			final List<Map<Individu, Boolean>> historiqueDesTours) {
		// je reste chez moi, ensuite je vais deux fois au bar. Si la majorité
		// reste chez eux, je reste également chez moi (sinon je vais au bar).

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
			final Map<Individu, Boolean> tour = historiqueDesTours
					.get(historiqueDesTours.size() - 1);
			for (final Individu i : tour.keySet()) {
				final boolean estPartiAuBar = tour.get(i);
				if (estPartiAuBar) {
					nbInviduAuBarTotal++;
				}
				nbInviduTotal++;
			}

			final double ratioNbIndividuAuBarSurNbIndividuDuTour = (double) nbInviduTotal
					/ nbInviduAuBarTotal;
			System.out.print("( nbInviduTotal /  nbInviduAuBarTotal) = ("
					+ nbInviduTotal + "/" + nbInviduAuBarTotal + ") = ");
			System.out.format("%.3f : ",
					ratioNbIndividuAuBarSurNbIndividuDuTour);
			System.out
					.println(!(ratioNbIndividuAuBarSurNbIndividuDuTour >= 0.5d));
			return !(ratioNbIndividuAuBarSurNbIndividuDuTour >= 0.5d);
		}
	}
}
