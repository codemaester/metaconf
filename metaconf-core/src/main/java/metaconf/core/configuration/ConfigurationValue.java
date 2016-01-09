package metaconf.core.configuration;

public abstract class ConfigurationValue {
	public abstract Object getValue();

	public static ConfigurationValue newStringValue(String value) {
		return new StringConfigurationValue(value);
	}
	
	public static ConfigurationValue newIntegerValue(int value) {
		return new IntegerConfigurationValue(value);
	}
	
	public static ConfigurationValue newBooleanValue(boolean value) {
		return new BooleanConfigurationValue(value);
	}
}
