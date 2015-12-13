package main;

import individu.Individu;
import individu.IndividuFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import stats.Stats;
import tournament.TournamentI;
import tournament.impl.TournamentLowestStrategyToHighestStrategy;
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

		System.out.println("Depart : " + Calendar.getInstance().getTime());

		final Bar bar = Bar.getInstance(argNbPlacesDispo);

		final List<Individu> population = IndividuFactory
				.generatePopulation(argTaillePopulation);

		final TournamentI tournament = new TournamentLowestStrategyToHighestStrategy();

		final List<Map<Individu, Boolean>> historiqueDesTours = new ArrayList<Map<Individu, Boolean>>();
		// LinkedHashMap preserve the order
		final Map<Individu, Boolean> infosCurTour = new LinkedHashMap<Individu, Boolean>();

		for (int curTour = 1; curTour <= argNbTour; curTour++) {
			bar.reset();

			System.out.println("\tTour " + curTour);

			for (final Individu personne : population) {
				final boolean estPartiAuBar = bar.choisiAllerAuBar(personne,
						historiqueDesTours);
				infosCurTour.put(personne, estPartiAuBar);

				System.out.println(personne);
			}

			historiqueDesTours.add(infosCurTour);

			if (curTour >= 5) {
				tournament.evolution(population, historiqueDesTours);
			}
		}

		afficherLesResultatsDuDernierTour(historiqueDesTours);

		afficherLesStatistiquesParIndividu(population, historiqueDesTours);

		Stats.logStat(population);
	}

	private static void afficherLesResultatsDuDernierTour(
			final List<Map<Individu, Boolean>> historiqueDesTours) {
		final Map<Individu, Boolean> resultatDernierTour = historiqueDesTours
				.get(historiqueDesTours.size() - 1);

		System.out.println("\tResultat du dernier tour");
		// We loop over all persons
		for (final Individu curIndividu : resultatDernierTour.keySet()) {
			final boolean estAllerAuBarACeTour = resultatDernierTour
					.get(curIndividu);

			System.out.println(curIndividu + ", " + estAllerAuBarACeTour);
		}

	}

	private static void afficherLesStatistiquesParIndividu(
			final List<Individu> population,
			final List<Map<Individu, Boolean>> historiqueDesTours) {

		System.out.println("\tStatistiques par individu");

		for (final Individu curIndividu : population) {
			int nbFoisAuBar = 0;
			for (final Map<Individu, Boolean> tour : historiqueDesTours) {
				final boolean estPartiAuBar = tour.get(curIndividu);
				if (estPartiAuBar) {
					nbFoisAuBar++;
				}
			}

			System.out.print(curIndividu.getId() + "("
					+ curIndividu.getStrategieName() + ")" + " : "
					+ nbFoisAuBar + " = " + curIndividu.getScore()
					+ " (gagne en moyenne ");
			System.out
					.format("%.3f ",
							(double) curIndividu.getScore()
									/ (historiqueDesTours.size() > 0 ? historiqueDesTours
											.size() : 1));
			System.out.println("points par tour)");
		}
	}

	public static void checkNbArgsOrThrowException(final String[] args)
			throws Exception {
		// TODO: mettre les types des arguments (int)
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
