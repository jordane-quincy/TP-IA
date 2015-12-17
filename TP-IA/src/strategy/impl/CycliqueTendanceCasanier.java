package strategy.impl;

import java.util.List;
import java.util.Map;

import person.Person;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class CycliqueTendanceCasanier extends Cyclique {

	/**
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 * A CycliqueTendanceCasanier
	 *
	 * <pre>
	 * Example with i = 2 :
	 * 	Turn 1 = Stay at home
	 * 	Turn 2 = Go to the bar
	 * 	Turn 3 = Stay at home
	 * 	Turn 4 = Go to the bar
	 * 	Turn 5 = Stay at home
	 * 	Turn 6 = Go to the bar
	 * 	Turn 7 = Stay at home
	 * </pre>
	 */
	@Override
	public boolean goToTheBar(final Person me,
			final List<Map<Person, Boolean>> turnHistoric) {
		return !this.haveToGoToTheBar(turnHistoric);
	}
}
