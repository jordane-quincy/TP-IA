package individu;

import strategie.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class Individu {

	int id;
	int score;
	StrategieI strategie;

	public Individu(final int id, final StrategieI strategie) {
		this.id = id;
		this.strategie = strategie;
	}

	public boolean vaAuBar() {
		return this.strategie.allerAuBar();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final Individu autrePersonne = (Individu) obj;

		if (this.id != autrePersonne.id) {
			return false;
		}

		return true;
	}
}
