package metaconf.core.dimensions;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@XmlRootElement
@XmlType(factoryMethod="createDimensionValue")
public class DimensionValue implements Comparable<DimensionValue>{
	private String name;
	private String value;
	@XmlTransient
	private Dimension dimension;
	
	public static DimensionValue createDimensionValue() {
		return new DimensionValue("","", Dimension.createDimension());
	}

	@Override
	public int compareTo(DimensionValue o) {
		if (o == null) {
			return -1;
		}
		return dimension.compareTo(o.dimension);
	}
}
