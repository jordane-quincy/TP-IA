package strategie.impl;

import individu.Individu;

import java.util.List;
import java.util.Map;

import strategie.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class Casanier implements StrategieI {

	@Override
	public boolean allerAuBar(final Individu moi,
			final List<Map<Individu, Boolean>> historiqueDesTours) {
		return false;
	}

}
