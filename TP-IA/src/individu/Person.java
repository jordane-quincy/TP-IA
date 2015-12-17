package Person;

import java.util.List;
import java.util.Map;

import strategie.StrategieI;
import strategie.impl.Cyclique;
import tournament.TournamentI;

/**
 * A person (maybe an alcoholic).
 * 
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class Person {

	int id;
	int score = 0;
	StrategieI strategie;

	/**
	 * Constructor, used by the {@link PersonFactory}.
	 * 
	 * @param id
	 *            the unique identifier
	 * @param strategie
	 */
	public Person(final int id, final StrategieI strategie) {
		this.id = id;
		this.strategie = strategie;
	}

	/**
	 * Determine via the strategy if he/she want to go to the bar.
	 * 
	 * @param historiqueDesTours
	 *            the List of all previous turn infos
	 * @return true if the person go to the bar, false otherwise
	 */
	public boolean vaAuBar(final List<Map<Person, Boolean>> historiqueDesTours) {
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
	 * To be sure of the order of Person in Collections.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.id;
		return result;
	}

	/**
	 * Redefinition of the method equals.
	 */
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
		final Person other = (Person) obj;
		if (this.id != other.id) {
			return false;
		}
		return true;
	}

	/**
	 * Update his/her score.
	 * 
	 * @param vaAuBar
	 *            {@link #vaAuBar(List)}
	 * @param barPlein
	 *            {@link bar.Bar#isBarPlein()}
	 */
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

	/**
	 * Get the name of the strategy.
	 * 
	 * @return the name of the current strategy.
	 */
	public String getStrategieName() {
		return this.strategie.getClass().getSimpleName();
	}

	/**
	 * During the {@link TournamentI}, the strategy could change.
	 * 
	 * @param i
	 *            an {@link Person}
	 */
	public void takeTheStrategieOf(final Person i) {
		this.strategie = i.strategie;
	}

	/**
	 * Redefinition of the method to have all needed informations in a cool way.
	 */
	@Override
	public String toString() {
		String i = "";
		if (this.strategie instanceof Cyclique) {
			i = " [" + String.valueOf(((Cyclique) this.strategie).getI()) + "]";
		}

		return "Person n°" + this.id + "(" + this.getStrategieName() + i
				+ ") : " + this.score + " point" + (this.score > 1 ? "s" : "");
	}
}
