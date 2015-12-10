package main;

import individu.Individu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import strategie.StrategieI;
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
			System.err.println("Arguments non valides: " + nfe.getMessage());
		}

		final Bar bar = new Bar(argNbPlacesDispo);

		final List<Individu> population = new ArrayList<Individu>();
		for (int i = 0; i < argTaillePopulation; i++) {
			final StrategieI strategie = new Fetard();
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

	private static void afficherLesResultats(
			final List<Map<Individu, Boolean>> historiqueDesTours) {
		int numeroTour = 0;
		for (final Map<Individu, Boolean> curTour : historiqueDesTours) {
			numeroTour++;
			System.out.println("\tTour n°" + numeroTour);
			for (final Individu curIndividu : curTour.keySet()) {
				final boolean estAllerAuBarACeTour = curTour.get(curIndividu);

				System.out.println("Individu n°" + curIndividu.getId() + " : "
						+ estAllerAuBarACeTour);
			}
		}
	}

	public static void checkNbArgsOrThrowException(final String[] args)
			throws Exception {
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
