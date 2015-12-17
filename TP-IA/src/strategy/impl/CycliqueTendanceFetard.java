package strategy.impl;

import java.util.List;
import java.util.Map;

import person.Person;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class CycliqueTendanceFetard extends Cyclique {

	/**
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 * 
	 * A CycliqueTendanceFetard
	 * 
	 * <pre>
	 * Example with i = 3 :
	 * 	Turn 1 = Go to the bar
	 * 	Turn 2 = Stay at home
	 * 	Turn 3 = Stay at home
	 * 	Turn 4 = Go to the bar
	 * 	Turn 5 = Stay at home
	 * 	Turn 6 = Stay at home
	 * 	Turn 7 = Go to the bar
	 * </pre>
	 */
	@Override
	public boolean goToTheBar(final Person me,
			final List<Map<Person, Boolean>> historiqueDesTours) {
		return this.haveToGoToTheBarAtThisTurn(historiqueDesTours);
	}
}
