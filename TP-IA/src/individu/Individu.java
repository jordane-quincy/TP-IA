package individu;

import java.util.List;
import java.util.Map;

import strategie.StrategieI;
import strategie.impl.Cyclique;

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
		return this.strategie.allerAuBar(this, historiqueDesTours);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * To be sure of the order of Individu in Collections.
	 */
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
		int pointGagne = 0;
		if (vaAuBar) {
			pointGagne = barPlein ? 0 : 2;
		} else {
			pointGagne = 1;
		}

		System.out.println(this.id + " maj score : " + this.score + " + "
				+ pointGagne);

		this.score += pointGagne;
	}

	public String getStrategieName() {
		return this.strategie.getClass().getSimpleName();
	}

	/**
	 * During the Tournoi, the lowest score will took the strategy of the person
	 * with the highest score.
	 * 
	 * @param i
	 *            an Individu
	 */
	public void takeTheStrategieOf(final Individu i) {
		this.strategie = i.strategie;
	}

	@Override
	public String toString() {

		String i = "";
		if (this.strategie instanceof Cyclique) {
			i = " [" + String.valueOf(((Cyclique) this.strategie).getI()) + "]";
		}

		return "Individu n°" + this.id + "(" + this.getStrategieName() + i
				+ ") : " + this.score + " point" + (this.score > 1 ? "s" : "");
	}
}
