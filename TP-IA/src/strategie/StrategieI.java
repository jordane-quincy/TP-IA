package strategie;

import individu.Person;

import java.util.List;
import java.util.Map;

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
	 * @param turnHistoric
	 * @return
	 */
	boolean goToTheBar(Person me,
			List<Map<Person, Boolean>> turnHistoric);
}
