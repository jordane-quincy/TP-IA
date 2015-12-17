package strategy.impl;

import java.util.List;
import java.util.Map;

import person.Person;
import strategy.StrategieI;

/**
 * Not recommended behavior in real life : always go to the bar.
 *
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class Fetard implements StrategieI {

	/**
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 *
	 * <pre>
	 * A Fetard always go to the bar.
	 * </pre>
	 */
	@Override
	public boolean goToTheBar(final Person me,
			final List<Map<Person, Boolean>> turnHistoric) {
		return true;
	}

}
