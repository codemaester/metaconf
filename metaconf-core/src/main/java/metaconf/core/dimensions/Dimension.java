package metaconf.core.dimensions;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@XmlRootElement
@XmlType(factoryMethod="createDimension")
public class Dimension implements Comparable<Dimension> {
	private int priority;
	private String name;
	
	@Override
	public int compareTo(Dimension o) {
		if (o == null) {
			return -1;
		}
		return priority - o.priority;
	}
	
	public static Dimension createDimension() {
		return new Dimension(0,"");
	}
}