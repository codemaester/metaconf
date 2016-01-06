package metaconf.core.dimensions;

import java.util.ArrayList;
import java.util.List;

public class DimensionManager {

	private List<Dimension> dimensions = new ArrayList<>();
	
	public DimensionManager() {
		dimensions.add(Dimension.builder()
				.priority(1).name("dimension_1").build());
		dimensions.add(Dimension.builder()
				.priority(5).name("dimension_2").build());
		dimensions.add(Dimension.builder()
				.priority(10).name("dimension_3").build());
	}
	
	public List<Dimension> getAllDimensions() {
		return dimensions;
	}
	
}
