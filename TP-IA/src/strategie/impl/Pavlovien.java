package strategie.impl;

import individu.Individu;

import java.util.List;
import java.util.Map;

import strategie.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class Pavlovien implements StrategieI {

	@Override
	public boolean allerAuBar(final Individu moi,
			final List<Map<Individu, Boolean>> historiqueDesTours) {
		// Je reste chez moi et ensuite je fais la même action que les autres du
		// coup précédent

		final int nbTour = historiqueDesTours.size();
		if (nbTour < 1) {
			// Stay at home at first round
			return false;
		} else {
			int nbInviduAuBarAuDernierTour = 0;
			int nbInviduAuDernierTour = 0;
			final Map<Individu, Boolean> tourPrecedent = historiqueDesTours
					.get(nbTour - 1);
			for (final Individu i : tourPrecedent.keySet()) {
				final boolean estPartiAuBar = tourPrecedent.get(i);
				if (estPartiAuBar) {
					nbInviduAuBarAuDernierTour++;
				}
				nbInviduAuDernierTour++;
			}
			final double ratioNbIndividuAuBarSurNbIndividuAuDernierTour = (double) nbInviduAuDernierTour
					/ nbInviduAuBarAuDernierTour;

			System.out
					.print("nbInviduAuDernierTour / nbInviduAuBarAuDernierTour = "
							+ nbInviduAuDernierTour
							+ "/"
							+ nbInviduAuBarAuDernierTour + " = ");
			System.out.format("%.3f : ",
					ratioNbIndividuAuBarSurNbIndividuAuDernierTour);
			System.out
					.println(ratioNbIndividuAuBarSurNbIndividuAuDernierTour >= 0.5d);
			return ratioNbIndividuAuBarSurNbIndividuAuDernierTour >= 0.5d;
		}
	}
}
