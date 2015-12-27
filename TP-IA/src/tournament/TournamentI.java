package tournament;

import java.util.List;
import java.util.Map;

import person.Person;

/**
 * An interface to hide different implementations of tournament method.
 * 
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public interface TournamentI {

	/**
	 * A method to update the population.
	 * 
	 * @param population
	 *            the list of {@link Person}
	 * @param turnHistoric
	 *            the List of all previous turn informations
	 * @return the new population
	 */
	List<Person> evolution(final List<Person> population,
			final List<Map<Person, Boolean>> turnHistoric);
}
