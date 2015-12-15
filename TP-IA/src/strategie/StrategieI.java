package strategie;

import individu.Individu;

import java.util.List;
import java.util.Map;

/**
 * An interface to hide different implementations of {@link Individu} strategy.
 * 
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public interface StrategieI {

	/**
	 * Determine if he/she want to go to the bar.
	 * 
	 * @param moi
	 * @param historiqueDesTours
	 * @return
	 */
	boolean allerAuBar(Individu moi,
			List<Map<Individu, Boolean>> historiqueDesTours);
}
