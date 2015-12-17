package strategy.impl;

import java.util.List;
import java.util.Map;

import person.Person;
import strategy.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class Pavlovien implements StrategieI {

	@Override
	public boolean goToTheBar(final Person me,
			final List<Map<Person, Boolean>> turnHistoric) {
		// Je reste chez moi et ensuite je fais la même action que les autres du
		// coup précédent

		final int nbTurn = turnHistoric.size();
		if (nbTurn < 1) {
			// Stay at home at first round
			return false;
		} else {
			int nbPersonInTheBarAtTheLastTurn = 0;
			int nbPersonAtTheLastTurn = 0;
			final Map<Person, Boolean> previousTurn = turnHistoric
					.get(nbTurn - 1);
			for (final Person i : previousTurn.keySet()) {
				final boolean wentToTheBar = previousTurn.get(i);
				if (wentToTheBar) {
					nbPersonInTheBarAtTheLastTurn++;
				}
				nbPersonAtTheLastTurn++;
			}
			final double ratioNbPersonAtTheBarOnNbPersonAtTheLastTurn = (double) nbPersonAtTheLastTurn
					/ nbPersonInTheBarAtTheLastTurn;

			System.out
					.print("nbPersonAtTheLastTurn / nbPersonInTheBarAtTheLastTurn = "
							+ nbPersonAtTheLastTurn
							+ "/"
							+ nbPersonInTheBarAtTheLastTurn + " = ");
			System.out.format("%.3f : ",
					ratioNbPersonAtTheBarOnNbPersonAtTheLastTurn);
			System.out
					.println(ratioNbPersonAtTheBarOnNbPersonAtTheLastTurn >= 0.5d);
			return ratioNbPersonAtTheBarOnNbPersonAtTheLastTurn >= 0.5d;
		}
	}
}
