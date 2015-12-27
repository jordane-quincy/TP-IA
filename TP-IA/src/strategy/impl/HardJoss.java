package strategy.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import person.Person;
import strategy.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class HardJoss implements StrategieI {
	@Override
	public boolean goToTheBar(final Person me,
			final List<Map<Person, Boolean>> turnHistoric) {
		// je reste chez moi, ensuite si la majorité de la population reste
		// chez eux, je choisis avec une proba. de 10% d’aller au bar et 90%
		// d’aller au bar
		final int nbTour = turnHistoric.size();
		if (nbTour < 1) {
			// Go to the bar at first round
			return true;
		} else {

			int nbTotalPersonAtHome = 0;
			int nbTotalPerson = 0;
			final Map<Person, Boolean> turn = turnHistoric.get(turnHistoric
					.size() - 1);
			for (final Person i : turn.keySet()) {
				final boolean estPartiAuBar = turn.get(i);
				if (!estPartiAuBar) {
					nbTotalPersonAtHome++;
				}
				nbTotalPerson++;
			}

			final double ratioNbPersonAtHomeOnNbPersonAtThisTurn = (double) nbTotalPerson
					/ nbTotalPersonAtHome;

			final boolean moreThan50PercentOfThePopulationStayAtHome = ratioNbPersonAtHomeOnNbPersonAtThisTurn >= 0.5d;

			if (!moreThan50PercentOfThePopulationStayAtHome) {
				return true;
			} else {
				// random : 0 included, 100 excluded
				final int randomBetween0And99 = ThreadLocalRandom.current()
						.nextInt(0, 100);

				return randomBetween0And99 < 10 ? false : true;
			}
		}
	}
}
