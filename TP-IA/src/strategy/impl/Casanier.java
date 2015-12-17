package strategy.impl;

import java.util.List;
import java.util.Map;

import person.Person;
import strategy.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class Casanier implements StrategieI {

	/**
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 *
	 * <pre>
	 * A Casanier always stay at home.
	 * </pre>
	 */
	@Override
	public boolean goToTheBar(final Person me,
			final List<Map<Person, Boolean>> turnHistoric) {
		return false;
	}
}
