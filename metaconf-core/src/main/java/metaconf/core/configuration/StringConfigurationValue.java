package metaconf.core.configuration;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="string")
public class StringConfigurationValue extends ConfigurationValue {

	private String value;
	
	@Override
	public String getValue() {
		return value;
	}
}
