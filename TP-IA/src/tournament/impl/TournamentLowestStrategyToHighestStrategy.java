package tournament.impl;

import java.util.List;
import java.util.Map;

import person.Person;
import tournament.TournamentI;

/**
 * Tournoi class.
 *
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class TournamentLowestStrategyToHighestStrategy implements TournamentI {

	@Override
	public void evolution(final List<Person> population,
			final List<Map<Person, Boolean>> historiqueDesTours) {

		final Map<Person, Boolean> resultatDernierTour = historiqueDesTours
				.get(historiqueDesTours.size() - 1);

		Person individuScoreMin = null;
		Person individuScoreMax = null;
		// We loop over all persons
		for (final Person curPerson : resultatDernierTour.keySet()) {
			if (individuScoreMin == null) {
				// init
				individuScoreMin = curPerson;
			} else {
				// else, if we find someone with a lower score : save it
				if (curPerson.getScore() < individuScoreMin.getScore()) {
					individuScoreMin = curPerson;
				}
			}
		}

		for (final Person curPerson : resultatDernierTour.keySet()) {
			// we look for the highest score with a different strategy
			if (!curPerson.getStrategieName().equals(
					individuScoreMin.getStrategieName())) {
				if (individuScoreMax == null) {
					// init
					individuScoreMax = curPerson;
				} else {
					// else, if we find someone with a higher score: save it
					if (curPerson.getScore() > individuScoreMax.getScore()) {
						individuScoreMax = curPerson;
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
