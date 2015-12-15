package strategie.impl;

import individu.Individu;

import java.util.List;
import java.util.Map;

import strategie.StrategieI;

/**
 * Not recommended behavior in real life : always go to the bar.
 * 
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class Fetard implements StrategieI {

	/**
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 * 
	 * <pre>
	 * A Fetard always go to the bar.
	 * </pre>
	 */
	@Override
	public boolean allerAuBar(final Individu moi,
			final List<Map<Individu, Boolean>> historiqueDesTours) {
		return true;
	}

}
