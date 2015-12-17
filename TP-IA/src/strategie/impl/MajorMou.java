package strategie.impl;

import individu.Person;

import java.util.List;
import java.util.Map;

import strategie.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class MajorMou implements StrategieI {

	@Override
	public boolean goToTheBar(final Person me,
			final List<Map<Person, Boolean>> turnHistoric) {
		// TODO Je reste chez me et ensuite je joue l'aciton que les autres ont
		// joués majoritairement sur tous les coups précédents

		final int nbTour = turnHistoric.size();
		if (nbTour < 1) {
			// Stay at home at first round
			return false;
		} else {

			int nbInviduAuBarTotal = 0;
			int nbInviduTotal = 0;
			for (final Map<Person, Boolean> tour : turnHistoric) {
				for (final Person i : tour.keySet()) {
					final boolean estPartiAuBar = tour.get(i);
					if (estPartiAuBar) {
						nbInviduAuBarTotal++;
					}
					nbInviduTotal++;
				}
			}

			final double ratioNbPersonAuBarSurNbPersonParTour = (double) nbInviduTotal
					/ nbInviduAuBarTotal / (turnHistoric.size() + 1);
			System.out
					.print("( nbInviduTotal /  nbInviduAuBarTotal) / turnHistoric.size() = ("
							+ nbInviduTotal
							+ "/"
							+ nbInviduAuBarTotal
							+ ")/"
							+ (turnHistoric.size() + 1) + ") = ");
			System.out.format("%.3f : ",
					ratioNbPersonAuBarSurNbPersonParTour);
			System.out
					.println(ratioNbPersonAuBarSurNbPersonParTour >= 0.5d);
			return ratioNbPersonAuBarSurNbPersonParTour >= 0.5d;
		}
	}
}
