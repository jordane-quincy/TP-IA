package strategy.impl;

import java.util.List;
import java.util.Map;

import person.Person;
import strategy.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class Pavlovien implements StrategieI {

	@Override
	public boolean goToTheBar(final Person me,
			final List<Map<Person, Boolean>> turnHistoric) {
		// Je reste chez me et ensuite je fais la même action que les autres du
		// coup précédent

		final int nbTour = turnHistoric.size();
		if (nbTour < 1) {
			// Stay at home at first round
			return false;
		} else {
			int nbInviduAuBarAuDernierTour = 0;
			int nbInviduAuDernierTour = 0;
			final Map<Person, Boolean> tourPrecedent = turnHistoric
					.get(nbTour - 1);
			for (final Person i : tourPrecedent.keySet()) {
				final boolean estPartiAuBar = tourPrecedent.get(i);
				if (estPartiAuBar) {
					nbInviduAuBarAuDernierTour++;
				}
				nbInviduAuDernierTour++;
			}
			final double ratioNbPersonAuBarSurNbPersonAuDernierTour = (double) nbInviduAuDernierTour
					/ nbInviduAuBarAuDernierTour;

			System.out
					.print("nbInviduAuDernierTour / nbInviduAuBarAuDernierTour = "
							+ nbInviduAuDernierTour
							+ "/"
							+ nbInviduAuBarAuDernierTour + " = ");
			System.out.format("%.3f : ",
					ratioNbPersonAuBarSurNbPersonAuDernierTour);
			System.out
					.println(ratioNbPersonAuBarSurNbPersonAuDernierTour >= 0.5d);
			return ratioNbPersonAuBarSurNbPersonAuDernierTour >= 0.5d;
		}
	}
}
