package strategy.impl;

import java.util.List;
import java.util.Map;
import java.util.Random;

import person.Person;
import strategy.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class Lunatique implements StrategieI {

	/**
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 * 
	 * <pre>
	 * A Lunatique has 50 percent change to go to the bar.
	 * </pre>
	 */
	@Override
	public boolean goToTheBar(final Person me,
			final List<Map<Person, Boolean>> turnHistoric) {
		// je lance une pièce de monnaie et je choisis l’action (probabilité des
		// actions de 0,5)
		return new Random().nextBoolean();
	}

}
