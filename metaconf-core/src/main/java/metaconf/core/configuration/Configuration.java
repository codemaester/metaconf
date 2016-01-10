package metaconf.core.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
public class Configuration {
	private List<ConfigurationNodeLink> configurationValues = new ArrayList<>();
}
