package stats;

import individu.Person;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import strategie.StrategieI;

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
	 * @param population
	 *            a List of {@link Person}
	 */
	public static void logStat(final List<Person> population) {
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
		writeCsvLog(mapTotalScoreStrategy);
	}

	/**
	 * Write in a file
	 *
	 * @param mapStrategieScore
	 *            the Map of {@link StrategieI} statistics
	 */
	private static void writeCsvLog(
			final Map<String, StrategyScore> mapStrategieScore) {

		try {
			final File csvFile = new File(CSV_FILENAME_PATH);
			// false arg for FileWriter == don't append content, override the
			// file each time
			final FileWriter writer = new FileWriter(csvFile, false);

			System.out.println("\t\tStats will be logged in the file : \n"
					+ csvFile.getAbsolutePath());

			// csv header
			writer.append("strategyName").append(CSV_SEPARATOR)
					.append("pointsForStrategy").append(CSV_SEPARATOR)
					.append("nbPersonForStrategy").append(CSV_SEPARATOR)
					.append("ratio").append("\n");

			for (final String strategyName : mapStrategieScore.keySet()) {
				// Compute stats
				final StrategyScore strategyScore = mapStrategieScore
						.get(strategyName);
				final int pointsForStrategy = strategyScore.getTotalScore();
				final int nbPersonForStrategy = strategyScore.getNbPerson() > 0 ? strategyScore
						.getNbPerson() : 1; // avoid div by zero
				final double ratioPointByPersonByStrategy = (double) pointsForStrategy
						/ nbPersonForStrategy;

				// write stats
				writer.append(strategyName).append(CSV_SEPARATOR)
						.append(String.valueOf(pointsForStrategy))
						.append(CSV_SEPARATOR)
						.append(String.valueOf(nbPersonForStrategy))
						.append(CSV_SEPARATOR)
						.append(String.valueOf(ratioPointByPersonByStrategy))
						.append("\n");
			}

			writer.flush();
			writer.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
