package metaconf.core.values;

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
@XmlRootElement(name="bool")
public class BooleanConfigurationValue extends ConfigurationValue {

	@XmlAttribute
	private boolean value;
	
	@Override
	public Boolean getValue() {
		return value;
	}
}
