package strategie.impl;

import individu.Person;

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
	public boolean goToTheBar(
			final List<Map<Person, Boolean>> turnHistoric) {
				var test = turnHistoric.size
		// TODO Auto-generated method stub
		return false;
	}

}
