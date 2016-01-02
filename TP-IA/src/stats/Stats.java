package stats;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import person.Person;
import strategy.StrategieI;

public class Stats {

	/**
	 * Constants for the CSV filename path.
	 */
	public final static String CSV_FILENAME_PATH = "IA_csvLog.csv";
	/**
	 * Constants for the CSV field separator
	 */
	public final static String CSV_SEPARATOR = ";";

	/**
	 * Compute the statistics about {@link StrategieI} for the population.
	 * 
	 * @param tournamentMode
	 * 
	 * @param population
	 *            a List of {@link Person}
	 * @param nbTurn
	 */
	public static void logStat(final String tournamentMode,
			final List<Person> population, final int nbTurn) {
		final Map<String, StrategyScore> mapTotalScoreStrategy = new LinkedHashMap<String, StrategyScore>();

		for (final Person i : population) {
			// strategyName will be used as a key
			final String strategyName = i.getStrategieName();

			// if it's the first person with this strategy : init
			if (!mapTotalScoreStrategy.containsKey(strategyName)) {
				mapTotalScoreStrategy.put(strategyName, new StrategyScore());
			}
			final StrategyScore strategyScore = mapTotalScoreStrategy
					.get(strategyName);

			strategyScore.incrementStrategyScore(i.getScore());

			mapTotalScoreStrategy.put(strategyName, strategyScore);
		}

		// After computation, write statistics in a CSV file.
		writeCsvLog(tournamentMode, mapTotalScoreStrategy, nbTurn);
	}

	/**
	 * Write in a file
	 * 
	 * @param tournamentMode
	 * 
	 * @param mapStrategieScore
	 *            the Map of {@link StrategieI} statistics
	 * @param nbTurn
	 */
	private static void writeCsvLog(final String tournamentMode,
			final Map<String, StrategyScore> mapStrategieScore, final int nbTurn) {

		try {
			final File csvFile = new File(CSV_FILENAME_PATH);
			// false arg for FileWriter == don't append content, override the
			// file each time
			final FileWriter writer = new FileWriter(csvFile, true);

			System.out.println("\t\tStats will be logged in the file : \n"
					+ csvFile.getAbsolutePath());

			// check if file is empty ( < 2 for utf-8)
			if (isEmpty(csvFile)) {
				// csv header
				writer.append("tournamentMode").append(CSV_SEPARATOR)
						.append("strategyName").append(CSV_SEPARATOR)
						.append("pointsForStrategy").append(CSV_SEPARATOR)
						.append("nbPointByPersonByStrategy")
						.append(CSV_SEPARATOR)
						.append("nbPointByPersonByStrategyByTurn").append("\n");
			}

			for (final String strategyName : mapStrategieScore.keySet()) {
				// Compute stats
				final StrategyScore strategyScore = mapStrategieScore
						.get(strategyName);
				final int pointsForStrategy = strategyScore.getTotalScore();
				final int nbPersonForStrategy = strategyScore.getNbPerson() > 0 ? strategyScore
						.getNbPerson() : 1; // avoid div by zero
				final double nbPointByPersonByStrategy = (double) pointsForStrategy
						/ nbPersonForStrategy;
				final double nbPointByPersonByStrategyByTurn = nbPointByPersonByStrategy
						/ nbTurn;

				// write stats
				writer.append(tournamentMode)
						.append(CSV_SEPARATOR)
						.append(strategyName)
						.append(CSV_SEPARATOR)
						.append(String.valueOf(pointsForStrategy))
						.append(CSV_SEPARATOR)
						.append(String.valueOf(nbPersonForStrategy))
						.append(CSV_SEPARATOR)
						.append(String.valueOf(nbPointByPersonByStrategy))
						.append(CSV_SEPARATOR)
						.append(String.valueOf(nbPointByPersonByStrategyByTurn))
						.append("\n");
			}

			// to separate next run
			writer.append("\n");

			writer.flush();
			writer.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check if file is empty.
	 * 
	 * @param csvFile
	 * @return true if the file is empty
	 */
	private static boolean isEmpty(final File csvFile) {
		// < 2 for utf-8
		return csvFile.length() < 2;
	}
}
