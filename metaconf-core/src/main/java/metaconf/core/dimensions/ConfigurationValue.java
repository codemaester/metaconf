package metaconf.core.dimensions;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.Singular;
import metaconf.core.dimensions.dao.DAOFactory;

@Data
@XmlRootElement
public class ConfigurationValue {
	@Singular
	private SortedSet<DimensionValue> dimensions = new TreeSet<>();
	private Object value;
	
	public ConfigurationValue() {
		DAOFactory.getDimensionManager().getAllDimensions().forEach(d -> {
			dimensions.add(DimensionValue.builder().name(d.getName()).value(null).build());
		});
	}
	
	public void setDimensionValue(Dimension dimension, String value) {
		dimensions.forEach(dval -> {
			if (dval.getDimension().equals(dimension)) {
				dval.setValue(value);
			}
		});
	}
}
