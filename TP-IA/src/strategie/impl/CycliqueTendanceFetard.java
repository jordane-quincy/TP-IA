package strategie.impl;

import individu.Individu;

import java.util.List;
import java.util.Map;

import strategie.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class CycliqueTendanceFetard implements StrategieI {

	int i;

	public CycliqueTendanceFetard() {
		this.i = (int) (Math.random() * 100);
	}

	@Override
	public boolean allerAuBar(
			final List<Map<Individu, Boolean>> historiqueDesTours) {
		// TODO Auto-generated method stub
		return false;
	}

}
