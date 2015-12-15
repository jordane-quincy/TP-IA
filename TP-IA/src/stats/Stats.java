package stats;

import individu.Individu;

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
	 *            a List of {@link Individu}
	 */
	public static void logStat(final List<Individu> population) {
		final Map<String, StrategyScore> mapStrategieScoreTotal = new LinkedHashMap<String, StrategyScore>();

		for (final Individu i : population) {
			// strategyName will be used as a key
			final String strategyName = i.getStrategieName();

			// if it's the first person with this strategy : init
			if (!mapStrategieScoreTotal.containsKey(strategyName)) {
				mapStrategieScoreTotal.put(strategyName, new StrategyScore());
			}
			final StrategyScore strategyScore = mapStrategieScoreTotal
					.get(strategyName);

			strategyScore.incrementStrategyScore(i.getScore());

			mapStrategieScoreTotal.put(strategyName, strategyScore);
		}

		// After computation, write statistics in a CSV file.
		writeCsvLog(mapStrategieScoreTotal);
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
				final int pointsForStrategy = strategyScore.getScoreTotal();
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
