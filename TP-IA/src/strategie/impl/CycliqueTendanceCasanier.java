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
	public boolean goToTheBar(
			final List<Map<Person, Boolean>> turnHistoric) {

		// TODO Auto-generated method stub
		int nbrDeTour = turnHistoric.size

		return false;
	}

}
