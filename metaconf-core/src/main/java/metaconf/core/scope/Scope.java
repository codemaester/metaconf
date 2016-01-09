package metaconf.core.scope;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import metaconf.core.dao.DAOFactory;

@EqualsAndHashCode
@ToString
@XmlRootElement
@XmlType(factoryMethod = "createScope")
public class Scope implements Comparable<Scope> {
	private String name;
	private String value;
	@XmlTransient
	private ScopeType scopeType;

	public static Scope createScope() {
		return build("", "");
	}

	@Builder
	private static Scope build(String name, String value) {
		Scope scope = new Scope();
		scope.setName(name);
		scope.setValue(value);
		return scope;
	}
	
	@Override
	public int compareTo(Scope o) {
		if (o == null) {
			return -1;
		}
		return scopeType.compareTo(o.scopeType);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
		scopeType = DAOFactory.getScopeManager().getByName(name);
	}
	
	public ScopeType getScopeType() {
		return scopeType;
	}
}
