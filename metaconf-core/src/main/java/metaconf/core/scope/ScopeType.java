package metaconf.core.scope;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@XmlRootElement
@XmlType(factoryMethod = "createScopeType")
public class ScopeType implements Comparable<ScopeType> {
	private int priority;
	private String name;

	@Override
	public int compareTo(ScopeType o) {
		if (o == null) {
			return -1;
		}
		return priority - o.priority;
	}

	public static ScopeType createScopeType() {
		return new ScopeType(0, "");
	}
}