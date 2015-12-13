package tournament;

import individu.Individu;

import java.util.List;
import java.util.Map;

public interface TournamentI {

	void evolution(final List<Individu> population,
			final List<Map<Individu, Boolean>> historiqueDesTours);
}
