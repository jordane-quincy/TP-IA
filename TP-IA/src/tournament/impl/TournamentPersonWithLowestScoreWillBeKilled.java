package tournament.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import person.Person;
import tournament.TournamentI;

/**
 * Tournoi class.
 * 
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class TournamentPersonWithLowestScoreWillBeKilled implements TournamentI {

	@Override
	public List<Person> evolution(final List<Person> population,
			final List<Map<Person, Boolean>> turnHistoric) {

		final List<Person> populationWithoutLooser = new ArrayList<Person>(
				population);

		final Map<Person, Boolean> lastTurnResult = turnHistoric
				.get(turnHistoric.size() - 1);

		Person personMinScore = null;
		// We loop over all persons
		for (final Person currentPerson : lastTurnResult.keySet()) {
			if (personMinScore == null) {
				// init
				personMinScore = currentPerson;
			} else {
				// else, if we find someone with a lower score : save it
				if (currentPerson.getScore() < personMinScore.getScore()) {
					personMinScore = currentPerson;
				}
			}
		}

		populationWithoutLooser.remove(personMinScore);

		return populationWithoutLooser;
	}
}
