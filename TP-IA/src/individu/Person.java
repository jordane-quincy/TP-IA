package individu;

import java.util.List;
import java.util.Map;

import strategie.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class Person {

	int id;
	int score = 0;
	StrategieI strategie;

	public Person(final int id, final StrategieI strategie) {
		this.id = id;
		this.strategie = strategie;
	}

	public boolean vaAuBar(final List<Map<Person, Boolean>> historiqueDesTours) {
		return this.strategie.allerAuBar(historiqueDesTours);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final Person autrePersonne = (Person) obj;

		if (this.id != autrePersonne.id) {
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
