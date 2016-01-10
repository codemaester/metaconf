package metaconf.core.scope;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import metaconf.core.configuration.Context;
import metaconf.core.values.BooleanConfigurationValue;
import metaconf.core.values.ConfigurationValue;
import metaconf.core.values.IntegerConfigurationValue;
import metaconf.core.values.StringConfigurationValue;

@XmlRootElement
@EqualsAndHashCode(callSuper=true)
@ToString
public class ScopedValue extends Context {
	@XmlElementRefs({ 
		@XmlElementRef(name = "int", type = IntegerConfigurationValue.class),
		@XmlElementRef(name = "string", type = StringConfigurationValue.class),
		@XmlElementRef(name = "bool", type = BooleanConfigurationValue.class)})
	private ConfigurationValue value;
	

	public ConfigurationValue getValue() {
		return value;
	}

	public void setValue(ConfigurationValue value) {
		this.value = value;
	}
	
}
