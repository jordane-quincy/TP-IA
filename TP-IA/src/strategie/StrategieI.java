package strategie;

import individu.Individu;

import java.util.List;
import java.util.Map;

public interface StrategieI {

	boolean allerAuBar(List<Map<Individu, Boolean>> historiqueDesTours);
}
