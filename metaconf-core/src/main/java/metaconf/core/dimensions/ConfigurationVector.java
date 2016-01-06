package metaconf.core.dimensions;

import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

import lombok.Data;
import lombok.Singular;

@Data
public class ConfigurationVector {
	@Singular
	private SortedMap<Dimension,Optional<String>> dimensions = new TreeMap<>();

	public ConfigurationVector() {
		new DimensionManager().getAllDimensions().forEach(d -> {
			dimensions.put(d, Optional.empty());
		});
	}	
}
