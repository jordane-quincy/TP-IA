package strategie;

import individu.Individu;

import java.util.List;
import java.util.Map;

/**
 * An interface to hide different implementations.
 * 
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public interface StrategieI {

	boolean allerAuBar(Individu moi,
			List<Map<Individu, Boolean>> historiqueDesTours);
}
