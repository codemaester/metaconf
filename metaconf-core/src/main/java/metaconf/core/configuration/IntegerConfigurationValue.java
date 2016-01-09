package metaconf.core.configuration;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="int")
public class IntegerConfigurationValue extends ConfigurationValue {

	@XmlAttribute
	private long value;
	
	@Override
	public Long getValue() {
		return value;
	}
}
