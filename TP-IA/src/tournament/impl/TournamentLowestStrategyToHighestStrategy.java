package tournament.impl;

import java.util.List;
import java.util.Map;

import person.Person;
import tournament.TournamentI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class TournamentLowestStrategyToHighestStrategy implements TournamentI {

	/**
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 * 
	 * <pre>
	 * In this mode, the person with the lowest score take the strategy of the highest score.
	 * </pre>
	 */
	@Override
	public List<Person> evolution(final List<Person> population,
			final List<Map<Person, Boolean>> turnHistoric) {

		final Map<Person, Boolean> lastTurnResult = turnHistoric
				.get(turnHistoric.size() - 1);

		Person personMinScore = null;
		Person personMaxScore = null;
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

		for (final Person currentPerson : lastTurnResult.keySet()) {
			// we look for the highest score with a different strategy
			if (!currentPerson.getStrategieName().equals(
					personMinScore.getStrategieName())) {
				if (personMaxScore == null) {
					// init
					personMaxScore = currentPerson;
				} else {
					// else, if we find someone with a higher score: save it
					if (currentPerson.getScore() > personMaxScore.getScore()) {
						personMaxScore = currentPerson;
					}
				}
			}
		}

		// avoid null pointer exception in case of all population have the
		// same
		// strategy
		if (personMaxScore != null) {
			System.out.println("\tIndividu avec le moins de points : "
					+ personMinScore);
			System.out.println("\tIndividu avec le plus de points : "
					+ personMaxScore);

			personMinScore.takeTheStrategieOf(personMaxScore);
		}

		return population;
	}
}
