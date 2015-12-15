package strategie.impl;

import individu.Individu;

import java.util.List;
import java.util.Map;

import strategie.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class Mefiant implements StrategieI {

	@Override
	public boolean allerAuBar(final Individu moi,
			final List<Map<Individu, Boolean>> historiqueDesTours) {
		// TODO je reste chez moi et ensuite je joue l'action que l'autre a joué

		final int nbTour = historiqueDesTours.size();
		if (nbTour < 1) {
			// Stay at home at first round
			return false;
		} else {
			int idAutreATrouver = 0;
			if (moi.getId() == 0) {
				// l'autre == celui qui a l'id juste apres comme il n'y a
				// personne avant
				idAutreATrouver = 1;
			} else {
				// l'autre == celui qui a l'id juste avant
				idAutreATrouver = moi.getId() - 1;
			}

			boolean autreEstPartiAuBar = false;
			final Map<Individu, Boolean> tour = historiqueDesTours
					.get(historiqueDesTours.size() - 1);
			for (final Individu i : tour.keySet()) {
				if (i.getId() == idAutreATrouver) {
					autreEstPartiAuBar = tour.get(i);
					break;// no need to continue to loop
				}
			}

			return autreEstPartiAuBar;
		}
	}
}
