package strategie.impl;

import individu.Individu;

import java.util.List;
import java.util.Map;

import strategie.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class MajorMou implements StrategieI {

	@Override
	public boolean allerAuBar(final Individu moi,
			final List<Map<Individu, Boolean>> historiqueDesTours) {
		// TODO Je reste chez moi et ensuite je joue l'aciton que les autres ont
		// joués majoritairement sur tous les coups précédents

		final int nbTour = historiqueDesTours.size();
		if (nbTour < 1) {
			// Stay at home at first round
			return false;
		} else {

			int nbInviduAuBarTotal = 0;
			int nbInviduTotal = 0;
			for (final Map<Individu, Boolean> tour : historiqueDesTours) {
				for (final Individu i : tour.keySet()) {
					final boolean estPartiAuBar = tour.get(i);
					if (estPartiAuBar) {
						nbInviduAuBarTotal++;
					}
					nbInviduTotal++;
				}
			}

			final double ratioNbIndividuAuBarSurNbIndividuParTour = (double) nbInviduTotal
					/ nbInviduAuBarTotal / (historiqueDesTours.size() + 1);
			System.out
					.print("( nbInviduTotal /  nbInviduAuBarTotal) / historiqueDesTours.size() = ("
							+ nbInviduTotal
							+ "/"
							+ nbInviduAuBarTotal
							+ ")/"
							+ (historiqueDesTours.size() + 1) + ") = ");
			System.out.format("%.3f : ",
					ratioNbIndividuAuBarSurNbIndividuParTour);
			System.out
					.println(ratioNbIndividuAuBarSurNbIndividuParTour >= 0.5d);
			return ratioNbIndividuAuBarSurNbIndividuParTour >= 0.5d;
		}
	}
}
