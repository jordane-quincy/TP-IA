package strategie.impl;

import strategie.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class Pavlovien implements StrategieI {

	@Override
	public boolean allerAuBar() {
		//Je reste chez moi et ensuite je fais la même action que les autres du coup précédent
		return false;
	}

}
