package strategy.impl;

import java.util.List;
import java.util.Map;

import person.Person;
import strategy.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class Mefiant implements StrategieI {

	@Override
	public boolean goToTheBar(final Person me,
			final List<Map<Person, Boolean>> turnHistoric) {
		// TODO je reste chez me et ensuite je joue l'action que l'autre a joué

		final int nbTurn = turnHistoric.size();
		if (nbTurn < 1) {
			// Stay at home at first round
			return false;
		} else {
			int idOfPersonNearMe = 0;
			if (me.getId() == 0) {
				// l'autre == celui qui a l'id juste apres comme il n'y a
				// personne avant
				idOfPersonNearMe = 1;
			} else {
				// l'autre == celui qui a l'id juste avant
				idOfPersonNearMe = me.getId() - 1;
			}

			boolean autreEstPartiAuBar = false;
			final Map<Person, Boolean> tour = turnHistoric
					.get(turnHistoric.size() - 1);
			for (final Person i : tour.keySet()) {
				if (i.getId() == idOfPersonNearMe) {
					autreEstPartiAuBar = tour.get(i);
					break;// no need to continue to loop
				}
			}

			return autreEstPartiAuBar;
		}
	}
}
