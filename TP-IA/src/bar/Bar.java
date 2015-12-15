package bar;

import individu.Individu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Bar class, following the multithread Singleton design pattern.
 * 
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class Bar {

	// singleton
	private static Bar uniqueInstance;

	private final int nbPlacesDisponibles;
	private final List<Individu> listDesIndividusPresents = new ArrayList<Individu>();

	/**
	 * Return the unique instance of the Bar.
	 * 
	 * @param nbPlacesDisponibles
	 * @return the Bar
	 */
	public static synchronized Bar getInstance(final int nbPlacesDisponibles) {
		if (uniqueInstance == null) {
			uniqueInstance = new Bar(nbPlacesDisponibles);
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
	 * @param nbPlacesDisponibles
	 */
	private Bar(final int nbPlacesDisponibles) {
		this.nbPlacesDisponibles = nbPlacesDisponibles;
	}

	/**
	 * Add the person (or not) to the bar according to his/her strategy.
	 * 
	 * @param i
	 *            a person
	 * @param historiqueDesTours
	 *            the List of all previous turn informations
	 * @return true if the person go to the bar, false otherwise
	 */
	public boolean choisiAllerAuBar(final Individu i,
			final List<Map<Individu, Boolean>> historiqueDesTours) {
		final boolean vaAuBar = i.vaAuBar(historiqueDesTours);
		if (vaAuBar) {
			this.listDesIndividusPresents.add(i);
		}

		return vaAuBar;
	}

	/**
	 * Clean the bar to get ready for a new week of happy hours.
	 */
	public void reset() {
		// All drunk people have to leave to bar.
		this.listDesIndividusPresents.clear();
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
	public boolean isBarPlein() {
		// System.out.println(this.nbPlacesDisponibles <
		// this.listDesIndividusPresents.size() ? "barPlein" :
		// "place(s) dispo");

		return this.nbPlacesDisponibles < this.listDesIndividusPresents.size();
	}
}
