package strategie.impl;

import individu.Individu;

import java.util.List;
import java.util.Map;

import strategie.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class Casanier implements StrategieI {

	/**
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 * 
	 * <pre>
	 * A Casanier always stay at home.
	 * </pre>
	 */
	@Override
	public boolean allerAuBar(final Individu moi,
			final List<Map<Individu, Boolean>> historiqueDesTours) {
		return false;
	}

}
