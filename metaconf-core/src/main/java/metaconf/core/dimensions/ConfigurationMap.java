package metaconf.core.dimensions;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import metaconf.core.model.DataNode;

@Data
public class ConfigurationMap {
	private Map<DataNode,Map<ConfigurationVector,Object>> values = new HashMap<>();
	
	
}
