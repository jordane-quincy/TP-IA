package main;

import individu.Individu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import strategie.StrategieI;
import strategie.impl.Casanier;
import strategie.impl.Fetard;
import bar.Bar;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class Main {

	public static void main(final String[] args) throws Exception {
		checkNbArgsOrThrowException(args);

		int argNbPlacesDispo = 0;
		int argNbTour = 0;
		int argTaillePopulation = 0;

		try {
			argNbPlacesDispo = Integer.parseInt(args[0]);
			argNbTour = Integer.parseInt(args[1]);
			argTaillePopulation = Integer.parseInt(args[2]);
		} catch (final NumberFormatException nfe) {
			throw new Exception("Arguments non valides: " + nfe.getMessage());
		}

		final List<StrategieI> lstStrategieImpl = getLstStrategieImpl();

		final Bar bar = Bar.getInstance(argNbPlacesDispo); // new
															// Bar(argNbPlacesDispo);

		final List<Individu> population = new ArrayList<Individu>();
		for (int i = 0; i < argTaillePopulation; i++) {
			final StrategieI strategie = lstStrategieImpl.get(i
					% lstStrategieImpl.size());
			final Individu curPerson = new Individu(i, strategie);

			population.add(curPerson);
		}

		final List<Map<Individu, Boolean>> historiqueDesTours = new ArrayList<Map<Individu, Boolean>>();
		final Map<Individu, Boolean> infosCurTour = new HashMap<Individu, Boolean>();

		for (int curTour = 1; curTour <= argNbTour; curTour++) {
			bar.reset();

			for (final Individu personne : population) {
				final boolean estAllerAuBar = bar.choisiAllerAuBar(personne,
						historiqueDesTours);
				infosCurTour.put(personne, estAllerAuBar);
			}

			historiqueDesTours.add(infosCurTour);
		}

		afficherLesResultats(historiqueDesTours);
	}

	/**
	 * Get a list of all classes which implement the interface
	 * {@link StrategieI}
	 * 
	 * @return list of StrategieI
	 */
	private static List<StrategieI> getLstStrategieImpl() {
		final List<StrategieI> lst = new ArrayList<StrategieI>();
		lst.add(new Fetard());
		lst.add(new Casanier());
		return lst;
	}

	private static void afficherLesResultats(
			final List<Map<Individu, Boolean>> historiqueDesTours) {
		int numeroTour = 0;
		// We loop over all turn
		for (final Map<Individu, Boolean> curTour : historiqueDesTours) {
			numeroTour++;
			System.out.println("\tTour n°" + numeroTour);

			// We loop over all turn persons
			for (final Individu curIndividu : curTour.keySet()) {
				final boolean estAllerAuBarACeTour = curTour.get(curIndividu);

				System.out.println("Individu n°" + curIndividu.getId() + "("
						+ curIndividu.getStrategieName() + ") : "
						+ estAllerAuBarACeTour);
			}
		}
	}

	public static void checkNbArgsOrThrowException(final String[] args)
			throws Exception {
		// TODO: mettre les types des argumenst (int)
		final StringBuilder msgErreur = new StringBuilder();
		if (args == null || args.length != 3) {
			msgErreur.append("Nombre d'arguments invalide.").append("\n");
			msgErreur.append("Format obligatoire :").append("\n");
			msgErreur.append("	nbPlacesDispo ").append("\n");
			msgErreur.append("	nbTour ").append("\n");
			msgErreur.append("	taillePopulation ");

			throw new Exception(msgErreur.toString());
		}
	}

}
