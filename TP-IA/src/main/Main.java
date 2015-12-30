package main;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import person.Person;
import person.PersonFactory;
import stats.Stats;
import tournament.TournamentI;
import tournament.impl.TournamentLowestStrategyToHighestStrategy;
import tournament.impl.TournamentPersonWithLowestScoreWillBeKilled;
import bar.Bar;

/**
 * @author DURIEZ Jean-Baptiste and QUINCY Jordane
 */
public class Main {

	// Constants
	final static String MODE_ELIMINATION = "ELIMINATION";
	final static String MODE_COPIE = "COPIE";

	public static void main(final String[] args) throws Exception {
		checkNbInputsOrThrowException(args);

		int inputNbPlTurn = 0;
		int inputPopulationSize = 0;
		int nbPlacesAvailable = 0;
		TournamentI tournament = null;

		try {
			inputNbPlTurn = Integer.parseInt(args[0]);
			inputPopulationSize = Integer.parseInt(args[1]);
			// the bar accept 60% of the population
			nbPlacesAvailable = Math.round(0.6f * inputPopulationSize);
		} catch (final NumberFormatException nfe) {
			throw new Exception("Arguments non valides : " + nfe.getMessage());
		}
		String inputMode = null;
		if (args.length > 2 && args[2] != null) {
			inputMode = args[2].toUpperCase();
			if (MODE_ELIMINATION.equals(inputMode)) {
				tournament = new TournamentPersonWithLowestScoreWillBeKilled();
			} else if (MODE_COPIE.equals(inputMode)) {
				tournament = new TournamentLowestStrategyToHighestStrategy();
			} else {
				throw new Exception("mode saisie invalide. Mode possible : \""
						+ MODE_ELIMINATION + "\"(par defaut) ou \""
						+ MODE_COPIE + "\"");
			}
		} else {
			// if no mode param, default :
			inputMode = MODE_ELIMINATION;
			tournament = new TournamentPersonWithLowestScoreWillBeKilled();
		}

		final Bar bar = Bar.getInstance(nbPlacesAvailable);

		List<Person> population = PersonFactory
				.generatePopulation(inputPopulationSize);

		final List<Map<Person, Boolean>> turnHistoric = new ArrayList<Map<Person, Boolean>>();
		// LinkedHashMap preserve the order
		final Map<Person, Boolean> informationCurrentTurn = new LinkedHashMap<Person, Boolean>();

		for (int curTour = 1; curTour <= inputNbPlTurn; curTour++) {
			bar.reset();
			informationCurrentTurn.clear();

			System.out.println("\tTour " + curTour);

			for (final Person person : population) {
				final boolean wentToTheBar = bar.choseGoToBar(person,
						turnHistoric);
				informationCurrentTurn.put(person, wentToTheBar);

				System.out.println(person);
			}

			turnHistoric.add(informationCurrentTurn);

			for (final Person i : informationCurrentTurn.keySet()) {
				final boolean goToTheBar = informationCurrentTurn.get(i);
				i.mettreAJourLeScore(goToTheBar, bar.isFullBar());
			}

			if (curTour >= 5) {
				population = tournament.evolution(population, turnHistoric);
			}
		}

		displayLastTurnResult(turnHistoric);

		displayStatsPerPerson(population, turnHistoric);

		Stats.logStat(inputMode, population);
	}

	private static void displayLastTurnResult(
			final List<Map<Person, Boolean>> turnHistorique) {
		final Map<Person, Boolean> lastTurnResult = turnHistorique
				.get(turnHistorique.size() - 1);

		System.out.println("\tResultat final");
		// We loop over all persons
		for (final Person currentPerson : lastTurnResult.keySet()) {
			final boolean wentToTheBarThisTurn = lastTurnResult
					.get(currentPerson);

			System.out.println(currentPerson + ", " + wentToTheBarThisTurn);
		}

	}

	private static void displayStatsPerPerson(final List<Person> population,
			final List<Map<Person, Boolean>> turnHistorique) {

		System.out.println("\tStatistiques par individu");

		for (final Person currentPerson : population) {
			int nbOfTimeToTheBar = 0;
			for (final Map<Person, Boolean> tour : turnHistorique) {
				final boolean wentToTheBar = tour.get(currentPerson);
				if (wentToTheBar) {
					nbOfTimeToTheBar++;
				}
			}

			System.out.print(currentPerson.getId() + "("
					+ currentPerson.getStrategieName() + ")" + " : "
					+ nbOfTimeToTheBar + " = " + currentPerson.getScore()
					+ " (gagne en moyenne ");
			System.out.format("%.3f ", (double) currentPerson.getScore()
					/ (turnHistorique.size() > 0 ? turnHistorique.size() : 1));
			System.out.println("points par tour)");
		}
	}

	public static void checkNbInputsOrThrowException(final String[] args)
			throws Exception {
		final StringBuilder errorMessage = new StringBuilder();
		if (args == null || args.length < 2) {
			errorMessage.append("Nombre d'arguments invalide.").append("\n");
			errorMessage.append("Format obligatoire :").append("\n");
			errorMessage.append(
					"	nbTour(int) taillePopulation(int) [modeTournoi(String) : \""
							+ MODE_ELIMINATION + "\"(par defaut) ou \""
							+ MODE_COPIE + "\" ]").append("\n");

			throw new Exception(errorMessage.toString());
		}
	}
}
