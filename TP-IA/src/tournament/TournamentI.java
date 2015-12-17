package tournament;

import individu.Person;

import java.util.List;
import java.util.Map;

public interface TournamentI {

	void evolution(final List<Person> population,
			final List<Map<Person, Boolean>> historiqueDesTours);
}
