package strategie.impl;

import strategie.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class CycliqueTendanceCasanier implements StrategieI {

	int i;

	public CycliqueTendanceCasanier(){
		this.i = (int) (Math.random() * 100);
	}

	@Override
	public boolean allerAuBar(
			final List<Map<Individu, Boolean>> historiqueDesTours) {
				
		// TODO Auto-generated method stub
		return false;
	}

}
