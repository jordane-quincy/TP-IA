package strategy.impl;

import java.util.List;
import java.util.Map;

import person.Person;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class SondeurFou extends Sondeur {
	// je reste chez me, ensuite je vais deux fois au bar. Si la majorité
	// reste chez eux, je vais au bar (sinon je reste chez moi)
	@Override
	public boolean goToTheBar(final Person me,
			final List<Map<Person, Boolean>> turnHistoric) {
		return this.haveToGoToTheBar(turnHistoric);
	}
}
