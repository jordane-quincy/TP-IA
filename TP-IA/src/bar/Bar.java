package bar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import person.Person;

/**
 * Bar class, following the multithread Singleton design pattern.
 * 
 * @author DURIEZ Jean-Baptiste and QUINCY Jordane
 */
public class Bar {

	// singleton
	private static Bar uniqueInstance;

	private final int nbPlacesAvailaible;
	private final List<Person> listOfPresentPerson = new ArrayList<Person>();
	private final List<Boolean> listOfBarState = new ArrayList<Boolean>();

	/**
	 * Return the unique instance of the Bar.
	 * 
	 * @param nbPlacesAvailaible
	 * @return the Bar
	 */
	public static synchronized Bar getInstance(final int nbPlacesAvailaible) {
		if (uniqueInstance == null) {
			uniqueInstance = new Bar(nbPlacesAvailaible);
		}
		return uniqueInstance;
	}

	/**
	 * Override clone method because we must use getInstance to get the bar.
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	/**
	 * The "real" constructor.
	 * 
	 * @param nbPlacesAvailaible
	 */
	private Bar(final int nbPlacesAvailaible) {
		this.nbPlacesAvailaible = nbPlacesAvailaible;
	}

	/**
	 * Add the person (or not) to the bar according to his/her strategy.
	 * 
	 * @param i
	 *            a person
	 * @param turnHistoric
	 *            the List of all previous turn informations
	 * @return true if the person go to the bar, false otherwise
	 */
	public boolean choseGoToBar(final Person i,
			final List<Map<Person, Boolean>> turnHistoric) {
		final boolean goToTheBar = i.goToTheBar(turnHistoric);
		if (goToTheBar) {
			this.listOfPresentPerson.add(i);
		}

		return goToTheBar;
	}

	/**
	 * Clean the bar to get ready for a new week of happy hours.
	 */
	public void reset() {
		// All drunk people have to leave to bar.
		this.listOfPresentPerson.clear();
		// Vaccum cleaner robot are amazing !
		this.runTheAutoCleaningRobot();
	}

	/**
	 * Because we are in IA.
	 */
	public void runTheAutoCleaningRobot() {
		// TODO : No time to create a real robot with a start/stop feature
		// remotely controlled, sorry :-/
	}

	/**
	 * Determine if too many people went to the bar this week.
	 * 
	 * @return true if the bar is full, false otherwise
	 */
	public boolean isFullBar() {
		// System.out.println(this.nbPlacesAvailaible <
		// this.listOfPresentPerson.size() ? "barPlein" :
		// "place(s) dispo");

		final boolean isFullBar = this.nbPlacesAvailaible < this.listOfPresentPerson
				.size();

		// save the state for this week
		this.listOfBarState.add(isFullBar);

		return isFullBar;
	}

	/**
	 * Determine if the bar was full or not at a turn.
	 * 
	 * @param turnNumber
	 *            the number of the turn
	 * @return true is the bar was full at the turn turnNumber, false otherwise
	 */
	public boolean getBarState(final int turnNumber) {
		return this.listOfBarState.get(turnNumber);
	}
}
