package strategy;

import java.util.List;
import java.util.Map;

import person.Person;

/**
 * An interface to hide different implementations of {@link Person} strategy.
 * 
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public interface StrategieI {

	/**
	 * Determine if he/she want to go to the bar.
	 * 
	 * @param me
	 *            the {@link Person}
	 * @param turnHistoric
	 *            the List of all previous turn informations
	 * @return true if "me" go to the bar at the current turn, false otherwise
	 */
	boolean goToTheBar(Person me, List<Map<Person, Boolean>> turnHistoric);
}
