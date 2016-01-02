package strategy.impl;

import java.util.List;
import java.util.Map;

import person.Person;
import strategy.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class Rancunier implements StrategieI {

	boolean hasBeenBetrayed = false;

	/**
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 * 
	 * <pre>
	 * A Pavlovien go to the bar and continue to go to the bar until the bar is full. After that, stay at home.
	 * </pre>
	 */
	@Override
	public boolean goToTheBar(final Person me,
			final List<Map<Person, Boolean>> turnHistoric) {
		// le comportement simple est d’aller au bar, et si je me sens trahis,
		// je reste chez moi

		final int nbTour = turnHistoric.size();
		if (nbTour < 1) {
			// Go to the bar at first round
			return true;
		} else {

			// if he has never been betrayed
			if (!this.hasBeenBetrayed) {

				// expected to get 2 points each turn
				final int scoreExpectedIfEachTurnWeGoToANonFullBarEveryTurn = nbTour * 2;
				if (me.getScore() == scoreExpectedIfEachTurnWeGoToANonFullBarEveryTurn) {
					// the bar was never full
					return true;
				} else {
					// we have been betrayed :-(
					this.hasBeenBetrayed = true;
					return false;
				}
			} else {
				// never forget : betrayed one time == stay at home forever
				return false;
			}
		}
	}
}
