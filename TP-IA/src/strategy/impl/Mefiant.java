package strategy.impl;

import java.util.List;
import java.util.Map;

import person.Person;
import strategy.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class Mefiant implements StrategieI {

	Person personNearMe = null;

	/**
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 * 
	 * <pre>
	 * A Mefiant stay at home at first turn.
	 * After that, he/she compute the ratio of people who go to the bar for all turn and do like the majority.
	 * </pre>
	 */
	@Override
	public boolean goToTheBar(final Person me,
			final List<Map<Person, Boolean>> turnHistoric) {
		// TODO je reste chez me et ensuite je joue l'action que l'autre a joué

		final int nbTurn = turnHistoric.size();
		if (nbTurn < 1) {
			// Stay at home at first round
			return false;
		} else {
			// init the other person
			if (this.personNearMe == null) {
				int personNearMeId = -1;
				if (me.getId() == 0) {
					// l'autre == celui qui a l'id juste apres comme il n'y a
					// personne avant
					personNearMeId = 1;
				} else {
					// l'autre == celui qui a l'id juste avant
					personNearMeId = me.getId() - 1;
				}

				final Map<Person, Boolean> previousTurn = turnHistoric
						.get(turnHistoric.size() - 1);
				for (final Person other : previousTurn.keySet()) {
					if (personNearMeId == other.getId()) {
						// we find him !
						this.personNearMe = other;
						break; // no need to continue
					}
				}
			}

			final Map<Person, Boolean> previousTurn = turnHistoric
					.get(turnHistoric.size() - 1);

			return previousTurn.get(this.personNearMe);
		}
	}
}
