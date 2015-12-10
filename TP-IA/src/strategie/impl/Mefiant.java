package strategie.impl;

import strategie.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class Mefiant implements StrategieI {

	@Override
	public boolean allerAuBar() {
		//TODO je reste chez moi et ensuite je joue l'action que l'autre a joué
		return false;
	}
}
