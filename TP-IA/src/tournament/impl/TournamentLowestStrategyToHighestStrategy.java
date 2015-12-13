package tournament.impl;

import individu.Individu;

import java.util.List;
import java.util.Map;

import tournament.TournamentI;

/**
 * Tournoi class.
 * 
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class TournamentLowestStrategyToHighestStrategy implements TournamentI {

	@Override
	public void evolution(final List<Individu> population,
			final List<Map<Individu, Boolean>> historiqueDesTours) {

		final Map<Individu, Boolean> resultatDernierTour = historiqueDesTours
				.get(historiqueDesTours.size() - 1);

		Individu individuScoreMin = null;
		Individu individuScoreMax = null;
		// We loop over all persons
		for (final Individu curIndividu : resultatDernierTour.keySet()) {
			if (individuScoreMin == null) {
				// init
				individuScoreMin = curIndividu;
			} else {
				// else, if we find someone with a lower score : save it
				if (curIndividu.getScore() < individuScoreMin.getScore()) {
					individuScoreMin = curIndividu;
				}
			}
		}

		for (final Individu curIndividu : resultatDernierTour.keySet()) {
			// we look for the highest score with a different strategy
			if (!curIndividu.getStrategieName().equals(
					individuScoreMin.getStrategieName())) {
				if (individuScoreMax == null) {
					// init
					individuScoreMax = curIndividu;
				} else {
					// else, if we find someone with a higher score: save it
					if (curIndividu.getScore() > individuScoreMax.getScore()) {
						individuScoreMax = curIndividu;
					}
				}
			}
		}

		// avoid null pointer exception in case of all population have the
		// same
		// strategy
		if (individuScoreMax != null) {
			System.out.println("\tIndividu avec le moins de points : "
					+ individuScoreMin);
			System.out.println("\tIndividu avec le plus de points : "
					+ individuScoreMax);

			individuScoreMin.takeTheStrategieOf(individuScoreMax);
		}
	}
}
