package metaconf.core.dimensions;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import lombok.Builder;
import lombok.Data;
import metaconf.core.dimensions.dao.DAOFactory;

@Data
@XmlRootElement
@XmlType(factoryMethod = "createDimensionValue")
public class DimensionValue implements Comparable<DimensionValue> {
	private String name;
	private String value;
	@XmlTransient
	private Dimension dimension;

	public static DimensionValue createDimensionValue() {
		return build("", "");
	}

	@Builder
	private static DimensionValue build(String name, String value) {
		DimensionValue dimensionValue = new DimensionValue();
		dimensionValue.setName(name);
		dimensionValue.setValue(value);
		return dimensionValue;
	}
	
	
	public void setName(String name) {
		dimension = DAOFactory.getDimensionManager().getByName(name);
	}

	@Override
	public int compareTo(DimensionValue o) {
		if (o == null) {
			return -1;
		}
		return dimension.compareTo(o.dimension);
	}
}
