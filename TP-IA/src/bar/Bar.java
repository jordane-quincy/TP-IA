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

	public boolean choisiAllerAuBar(final Individu i,
			final List<Map<Individu, Boolean>> historiqueDesTours) {
		final boolean vaAuBar = i.vaAuBar(historiqueDesTours);
		if (vaAuBar) {
			this.listDesIndividusPresents.add(i);
		}

		i.mettreAJourLeScore(vaAuBar, this.barPlein());

		return vaAuBar;
	}

	public void reset() {
		this.listDesIndividusPresents.clear();
	}

	private boolean barPlein() {
		return this.nbPlacesDisponibles >= this.listDesIndividusPresents.size();
	}
}
