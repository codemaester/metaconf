package metaconf.core.configuration;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Configuration {
	private List<ConfigurationOptions> configurationValues = new ArrayList<>();
}
