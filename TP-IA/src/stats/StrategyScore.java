package stats;

import strategie.StrategieI;

/**
 * StrategyScore is a class to hold informations needed to log stats.
 *
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class StrategyScore {

	public int totalScore;
	public int nbPerson;

	/**
	 * Constructor
	 */
	public StrategyScore() {
		this.totalScore = 0;
		this.nbPerson = 0;
	}

	/**
	 * @return the totalScore
	 */
	public int getTotalScore() {
		return this.totalScore;
	}

	/**
	 * @return the nbPerson
	 */
	public int getNbPerson() {
		return this.nbPerson;
	}

	/**
	 * A helper method to increment the score of the strategy without forget to
	 * increment the number of person using this {@link StrategieI}.
	 *
	 * @param personTotalScore
	 */
	public void incrementStrategyScore(final int personTotalScore) {
		this.nbPerson++;
		this.totalScore += personTotalScore;
	}
}
