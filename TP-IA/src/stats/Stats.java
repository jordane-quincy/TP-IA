package stats;

import individu.Individu;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Stats {

	public final static String CSV_SEPARATOR = ",";

	public static void logStat(final List<Individu> population) {

		final Map<String, Integer> mapStrategieScore = new LinkedHashMap<String, Integer>();
		for (final Individu i : population) {
			final String strategyName = i.getStrategieName();
			if (!mapStrategieScore.containsKey(strategyName)) {
				mapStrategieScore.put(strategyName, 0);
			}
			int curPointsForStrategy = mapStrategieScore.get(strategyName);
			curPointsForStrategy = curPointsForStrategy + i.getScore();
			mapStrategieScore.put(strategyName, curPointsForStrategy);

		}
		writeCsvLog(mapStrategieScore);
	}

	private static void writeCsvLog(final Map<String, Integer> mapStrategieScore) {

		try {
			final FileWriter writer = new FileWriter("IA_csvLog.csv", false);

			for (final String strategyName : mapStrategieScore.keySet()) {
				final int pointsForStrategy = mapStrategieScore
						.get(strategyName);
				writer.append(strategyName).append(CSV_SEPARATOR)
						.append(String.valueOf(pointsForStrategy)).append("\n");
			}

			writer.flush();
			writer.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
