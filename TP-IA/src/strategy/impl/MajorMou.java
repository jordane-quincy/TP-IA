package strategy.impl;

import java.util.List;
import java.util.Map;

import person.Person;
import strategy.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class MajorMou implements StrategieI {

	@Override
	public boolean goToTheBar(final Person me,
			final List<Map<Person, Boolean>> turnHistoric) {
		// TODO Je reste chez me et ensuite je joue l'aciton que les autres ont
		// joués majoritairement sur tous les coups précédents

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
			System.out
					.print("( nbTotalPerson /  nbTotalPersonInTheBar) / turnHistoric.size() = ("
							+ nbTotalPerson
							+ "/"
							+ nbTotalPersonInTheBar
							+ ")/"
							+ (turnHistoric.size() + 1) + ") = ");
			System.out.format("%.3f : ",
					rationNbPersonInTheBarOnNbPersonPerTurn);
			System.out
					.println(rationNbPersonInTheBarOnNbPersonPerTurn >= 0.5d);
			return rationNbPersonInTheBarOnNbPersonPerTurn >= 0.5d;
		}
	}
}
