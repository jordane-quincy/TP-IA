package individu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import strategie.StrategieI;
import strategie.impl.Casanier;
import strategie.impl.CycliqueTendanceCasanier;
import strategie.impl.CycliqueTendanceFetard;
import strategie.impl.Fetard;
import strategie.impl.Lunatique;
import strategie.impl.MajorMou;
import strategie.impl.Pavlovien;
import strategie.impl.Sondeur;

/**
 * IndividuFactory class, following the Factory design pattern.
 * 
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public final class IndividuFactory {

	/**
	 * Return a generated population of {@link Individu}.
	 * 
	 * @param argTaillePopulation
	 *            the size of the population needed.
	 * @return the list of {@link Individu} generated.
	 */
	public static List<Individu> generatePopulation(
			final int argTaillePopulation) {

		final List<StrategieI> lstStrategieImpl = getLstStrategieImpl();

		final List<Individu> population = new ArrayList<Individu>();
		for (int i = 0; i < argTaillePopulation; i++) {
			final int random = ThreadLocalRandom.current().nextInt(0,
					lstStrategieImpl.size());
			final StrategieI strategie = lstStrategieImpl.get(random);
			final Individu curPerson = new Individu(i, strategie);

			population.add(curPerson);
		}

		return population;
	}

	/**
	 * Get a list of all classes which implement the interface
	 * {@link StrategieI}
	 * 
	 * @return list of {@link StrategieI}
	 */
	private static List<StrategieI> getLstStrategieImpl() {
		final List<StrategieI> lst = new ArrayList<StrategieI>();
		lst.add(new Fetard());
		lst.add(new Casanier());
		lst.add(new CycliqueTendanceFetard());
		lst.add(new CycliqueTendanceCasanier());
		lst.add(new MajorMou());
		lst.add(new Pavlovien());
		lst.add(new Lunatique());
		lst.add(new Sondeur());
		return lst;
	}

}
