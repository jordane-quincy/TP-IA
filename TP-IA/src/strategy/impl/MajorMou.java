package strategy.impl;

import java.util.List;
import java.util.Map;

import person.Person;
import strategy.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class MajorMou implements StrategieI {

	/**
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 * 
	 * <pre>
	 * A MajorMou stay at home at first turn.
	 * After that, he/she compute the ratio of people who go to the bar for all turn and do like the majority.
	 * </pre>
	 */
	@Override
	public boolean goToTheBar(final Person me,
			final List<Map<Person, Boolean>> turnHistoric) {

		final int nbTurn = turnHistoric.size();
		if (nbTurn < 1) {
			// Stay at home at first round
			return false;
		} else {

			int nbTotalPersonInTheBar = 0;
			int nbTotalPerson = 0;
			for (final Map<Person, Boolean> turn : turnHistoric) {
				for (final Person i : turn.keySet()) {
					final boolean wentToTheBar = turn.get(i);
					if (wentToTheBar) {
						nbTotalPersonInTheBar++;
					}
					nbTotalPerson++;
				}
			}

			final double rationNbPersonInTheBarOnNbPersonPerTurn = (double) nbTotalPerson
					/ nbTotalPersonInTheBar / (turnHistoric.size() + 1);

			return rationNbPersonInTheBarOnNbPersonPerTurn >= 0.5d;
		}
	}
}
