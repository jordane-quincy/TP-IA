package tournament;

import java.util.List;
import java.util.Map;

import person.Person;

public interface TournamentI {

	void evolution(final List<Person> population,
			final List<Map<Person, Boolean>> turnHistoric);
}
