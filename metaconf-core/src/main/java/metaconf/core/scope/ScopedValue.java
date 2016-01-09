package metaconf.core.scope;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.Singular;
import metaconf.core.configuration.BooleanConfigurationValue;
import metaconf.core.configuration.ConfigurationValue;
import metaconf.core.configuration.IntegerConfigurationValue;
import metaconf.core.configuration.StringConfigurationValue;
import metaconf.core.dimensions.dao.DAOFactory;

@Data
@XmlRootElement
public class ScopedValue {
	@Singular
	private SortedSet<Scope> scopes = new TreeSet<>();
	@XmlElementRefs({ 
		@XmlElementRef(name = "int", type = IntegerConfigurationValue.class),
		@XmlElementRef(name = "string", type = StringConfigurationValue.class),
		@XmlElementRef(name = "bool", type = BooleanConfigurationValue.class)})
	private ConfigurationValue value;
	
	public ScopedValue() {
		DAOFactory.getScopeManager().getAllScopes().forEach(d -> {
			scopes.add(Scope.builder().name(d.getName()).value(null).build());
		});
	}
	
	public void setScopeValue(String scopeTypeName, String scopeValue) {
		scopes.forEach(dval -> {
			if (dval.getScopeType().getName().equals(scopeTypeName)) {
				dval.setValue(scopeValue);
			}
		});
	}
	
	
}
