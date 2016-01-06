package metaconf.core.dimensions;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class Dimension implements Comparable<Dimension> {
	private int priority;
	private String name;
	
	@Override
	public int compareTo(Dimension o) {
		return priority - o.priority;
	}
}