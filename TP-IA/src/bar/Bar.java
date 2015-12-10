package bar;

import individu.Individu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class Bar {

	int nbPlacesDisponibles;
	List<Individu> listDesIndividusPresents = new ArrayList<Individu>();

	public Bar(final int nbPlacesDisponibles) {
		this.nbPlacesDisponibles = nbPlacesDisponibles;
	}

	public boolean choisiAllerAuBar(final Individu i,
			final List<Map<Individu, Boolean>> historiqueDesTours) {
		final boolean vaAuBar = i.vaAuBar(historiqueDesTours);
		if (vaAuBar) {
			i.mettreAJourLeScore(barPlein);

			this.listDesIndividusPresents.add(i);
		}

		return vaAuBar;
	}

	public void reset() {
		this.listDesIndividusPresents.clear();
	}

	private boolean barPlein() {
		return this.nbPlacesDisponibles >= this.listDesIndividusPresents.size();
	}
}
