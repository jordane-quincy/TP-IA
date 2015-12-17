package strategie.impl;

import java.util.List;
import java.util.Map;

import strategie.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class EasyGo implements StrategieI {

	boolean hasBeenBetrayed = false;

	@Override
	public boolean allerAuBar(final Individu moi,
			final List<Map<Individu, Boolean>> historiqueDesTours) {
		// le comportement est de rester chez soi et si je trouve plus
		// interessant
		// d’aller au bar, je décide d’aller au bar.

		final int nbTour = historiqueDesTours.size();
		if (nbTour < 1) {
			// Go to the bar at first round
			return true;
		} else {

			// if he has never been betrayed
			if (!this.hasBeenBetrayed) {

				// expected to get 2 points each turn
				final int scoreExpectedIfEachTurnWeGoToANonFullBarEveryTurn = nbTour * 2;
				if (moi.getScore() == scoreExpectedIfEachTurnWeGoToANonFullBarEveryTurn) {
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
