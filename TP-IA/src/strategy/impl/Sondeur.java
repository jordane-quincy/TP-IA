package strategy.impl;

import java.util.List;
import java.util.Map;

import person.Person;
import strategy.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public abstract class Sondeur implements StrategieI {

	// Must be implemented in sub classes.
	@Override
	public abstract boolean goToTheBar(Person me,
			final List<Map<Person, Boolean>> turnHistoric);

	/**
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 * 
	 * <pre>
	 * A Sondeur stay at home, then go at bar multiple time and return true if the majority of the population is at bar.
	 * </pre>
	 */
	protected boolean haveToGoToTheBar(
			final List<Map<Person, Boolean>> turnHistoric) {
		// je reste chez me, ensuite je vais deux fois au bar. Si la majorité
		// reste chez eux, je reste également chez me (sinon je vais au bar).

		final int nbTurn = turnHistoric.size();
		if (nbTurn < 1) {
			// Stay at home at first round
			return false;
		} else if (nbTurn <= 3) {
			// Go to the bar
			return true;
		} else {

			int nbTotalPersonAtTheBar = 0;
			int nbTotalPerson = 0;
			final Map<Person, Boolean> turn = turnHistoric.get(turnHistoric
					.size() - 1);
			for (final Person i : turn.keySet()) {
				final boolean estPartiAuBar = turn.get(i);
				if (estPartiAuBar) {
					nbTotalPersonAtTheBar++;
				}
				nbTotalPerson++;
			}

			final double ratioNbPersonAtTheBarOnNbPersonAtThisTurn = (double) nbTotalPerson
					/ nbTotalPersonAtTheBar;

			return ratioNbPersonAtTheBarOnNbPersonAtThisTurn >= 0.5d;
		}
	}
}
