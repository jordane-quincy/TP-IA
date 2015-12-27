package strategy.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import person.Person;
import strategy.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class Mefiant implements StrategieI {

	@Override
	public boolean goToTheBar(final Person me,
			final List<Map<Person, Boolean>> turnHistoric) {
		// je reste chez me et ensuite je joue l'action que l'autre a joué

		final int nbTurn = turnHistoric.size();
		if (nbTurn < 1) {
			// Stay at home at first round
			return false;
		} else {

			Person other = null;
			final Map<Person, Boolean> previousTurn = turnHistoric
					.get(turnHistoric.size() - 1);
			final Iterator<Entry<Person, Boolean>> it = previousTurn.entrySet()
					.iterator();
			final boolean isOtherFound = false;
			while (it.hasNext() && !isOtherFound) {
				final Person i = it.next().getKey();

				// in case of the person "me" is the last of the list, the next
				// is the first of the turn
				if (other == null) {
					other = i;
				}

				// in other case, when we find the place of "me" during the last
				// turn, other is the next one.
				if (me.getId() == i.getId()) {
					// we found himself in the previous turn
					// now we get the next person in the turn
					other = it.next().getKey();
				}
			}

			return previousTurn == null || other == null ? false : previousTurn
					.get(other);
		}
	}
}
