package stats;

import strategie.StrategieI;

/**
 * StrategyScore is a class to hold informations needed to log stats.
 * 
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class StrategyScore {

	public int scoreTotal;
	public int nbPerson;

	/**
	 * Constructor
	 */
	public StrategyScore() {
		this.scoreTotal = 0;
		this.nbPerson = 0;
	}

	/**
	 * @return the scoreTotal
	 */
	public int getScoreTotal() {
		return this.scoreTotal;
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
	 * @param scoreTotalIndividu
	 */
	public void incrementStrategyScore(final int scoreTotalIndividu) {
		this.nbPerson++;
		this.scoreTotal += scoreTotalIndividu;
	}
}
