package individu;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class Individu {

	int id;
	int score;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Get the current score of this person.
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
}
