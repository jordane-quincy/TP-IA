package strategy.impl;

import java.util.List;
import java.util.Map;

import person.Person;
import strategy.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public abstract class Cyclique implements StrategieI {

	int i;

	public Cyclique() {
		// 1 + car un cyclique choisit d'aller au bar au moins une fois
		this.i = 1 + (int) (Math.random() * 3);
	}

	// Must be implemented in sub classes.
	@Override
	public abstract boolean goToTheBar(Person me,
			final List<Map<Person, Boolean>> turnHistoric);

	/**
	 * Periodic people choose to do something one time and the opposite during X
	 * turn.
	 *
	 * @param turnHistoric
	 * @return
	 */
	// used only by sub classes
	protected boolean haveToGoToTheBar(
			final List<Map<Person, Boolean>> turnHistoric) {
		return turnHistoric.size() % this.i == 0;
	}

	/**
	 * @return the i
	 */
	public int getI() {
		return this.i;
	}

}
