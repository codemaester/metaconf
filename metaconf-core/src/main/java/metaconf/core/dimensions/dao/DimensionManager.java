package metaconf.core.dimensions.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import metaconf.core.dimensions.Dimension;

public class DimensionManager {

	private Map<String,Dimension> dimensions = new HashMap<>();
	
	public DimensionManager() {
		
		
		addDimension(Dimension.builder()
				.priority(1).name("dimension_1").build());
		addDimension(Dimension.builder()
				.priority(5).name("dimension_2").build());
		addDimension(Dimension.builder()
				.priority(10).name("dimension_3").build());
	}
	
	
	private void addDimension(Dimension dimension) {
		dimensions.put(dimension.getName(), dimension);
	}
	
	public Collection<Dimension> getAllDimensions() {
		return dimensions.values();
	}
	
	public Dimension getByName(String name) {
		return dimensions.get(name);
	}
	
}