package strategie.impl;

import strategie.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class MajorMou implements StrategieI {

	@Override
	public boolean allerAuBar() {
		//TODO Je reste chez moi et ensuite je joue l'aciton que les autres ont joués majoritairement sur tous les coups précédents
		return false;
	}
}
