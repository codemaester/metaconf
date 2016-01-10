package metaconf.core.configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import lombok.Data;
import metaconf.core.model.DataNode;
import metaconf.core.scope.ScopedValue;
import metaconf.core.values.ConfigurationValue;

@XmlRootElement
@Data
public class ConfigurationNodeLink {
	@XmlTransient
	private DataNode dataNode;
	private Long nodeId;
	private List<ScopedValue> scopedValues;
	
	public ConfigurationValue getForContext(Context context) {
		
		Map<Long,List<ScopedValue>> valuesByScore = scopedValues.stream().collect(
			Collectors.<ScopedValue,Long>groupingBy(scopedVal -> {
				return scopedVal.calclulateMatchScore(context);}));
			
		ScopedValue largestScoredScopedValue = valuesByScore.entrySet()
				.stream().reduce(null, (a, b) -> {
					if (a == null) {
						return b;
					} else if (b == null) {
						return a;
					} else {
						return a.getKey() > b.getKey() ? a : b;
					}
				}).getValue().get(0);
		
		return largestScoredScopedValue.getValue();
	}
}
