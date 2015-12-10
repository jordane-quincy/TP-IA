package individu;

import java.util.List;
import java.util.Map;

import strategie.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class Individu {

	int id;
	int score = 0;
	StrategieI strategie;

	public Individu(final int id, final StrategieI strategie) {
		this.id = id;
		this.strategie = strategie;
	}

	public boolean vaAuBar(final List<Map<Individu, Boolean>> historiqueDesTours) {
		return this.strategie.allerAuBar(historiqueDesTours);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.id;
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final Individu other = (Individu) obj;
		if (this.id != other.id) {
			return false;
		}
		return true;
	}

	public void mettreAJourLeScore(final boolean vaAuBar, final boolean barPlein) {

	}

	public String getStrategieName() {
		return this.strategie.getClass().getSimpleName();
	}
}
