package strategie.impl;

import individu.Individu;

import java.util.List;
import java.util.Map;
import java.util.Random;

import strategie.StrategieI;

/**
 * Not recommended behavior in real life : always go to the bar.
 * 
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class Lunatique implements StrategieI {

	@Override
	public boolean allerAuBar(final Individu moi,
			final List<Map<Individu, Boolean>> historiqueDesTours) {
		// je lance une pièce de monnaie et je choisis l’action (probabilité des
		// actions de 0,5)
		return new Random().nextBoolean();
	}

}
