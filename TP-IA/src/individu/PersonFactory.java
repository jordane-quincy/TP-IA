package individu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import strategy.StrategieI;
import strategy.impl.Casanier;
import strategy.impl.CycliqueTendanceCasanier;
import strategy.impl.CycliqueTendanceFetard;
import strategy.impl.Fetard;
import strategy.impl.Lunatique;
import strategy.impl.MajorMou;
import strategy.impl.Mefiant;
import strategy.impl.Pavlovien;
import strategy.impl.Sondeur;

/**
 * PersonFactory class, following the Factory design pattern.
 *
 * @author DURIEZ Jean-Baptiste and QUINCY Jordane
 */
public final class PersonFactory {

	/**
	 * Return a generated population of {@link Person}.
	 *
	 * @param inputPopulationSize
	 *            the size of the population needed.
	 * @return the list of {@link Person} generated.
	 */
	public static List<Person> generatePopulation(
			final int inputPopulationSize) {

		final List<StrategieI> lstStrategyImpl = getLstStrategyImpl();

		final List<Person> population = new ArrayList<Person>();
		for (int i = 0; i < inputPopulationSize; i++) {
			final int random = ThreadLocalRandom.current().nextInt(0,
					lstStrategyImpl.size());
			final StrategieI strategy = lstStrategyImpl.get(random);
			final Person currentPerson = new Person(i, strategy);

			population.add(currentPerson);
		}

		return population;
	}

	/**
	 * Get a list of all classes which implement the interface
	 * {@link StrategieI}
	 *
	 * @return list of {@link StrategieI}
	 */
	private static List<StrategieI> getLstStrategyImpl() {
		final List<StrategieI> lst = new ArrayList<StrategieI>();
		lst.add(new Casanier());
		lst.add(new CycliqueTendanceFetard());
		lst.add(new CycliqueTendanceCasanier());
		lst.add(new Fetard());
		lst.add(new Lunatique());
		lst.add(new MajorMou());
		lst.add(new Mefiant());
		lst.add(new Pavlovien());
		lst.add(new Sondeur());
		return lst;
	}

}