package strategy.impl;

import java.util.List;
import java.util.Map;

import person.Person;
import strategy.StrategieI;
import bar.Bar;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class EasyGo implements StrategieI {

	/**
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 * 
	 * <pre>
	 * A EasyGo stay at home at first turn.
	 * After that, he/she check if the bar was full during the previous turn :
	 * 	if yes, he/she stay at home,
	 * 	otherwise he/she goes to the bar.
	 * </pre>
	 */
	@Override
	public boolean goToTheBar(final Person me,
			final List<Map<Person, Boolean>> turnHistoric) {
		// le comportement est de rester chez soi et si je trouve plus
		// interessant
		// d’aller au bar, je décide d’aller au bar.

		final int nbTour = turnHistoric.size();
		if (nbTour < 1) {
			// Go to the bar at first round
			return true;
		} else {

			// Bar.getInstance(0) because the arg will not be really used (the
			// bar was already initialized in the main)
			final boolean barWasFullAtTheLastTurn = Bar.getInstance(0)
					.getBarState(nbTour - 1);

			if (barWasFullAtTheLastTurn) {
				// Stay at home
				return false;
			} else {
				// Go to the bar
				return true;
			}

		}
	}
}
