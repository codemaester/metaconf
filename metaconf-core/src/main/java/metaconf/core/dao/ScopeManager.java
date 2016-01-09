package metaconf.core.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import metaconf.core.scope.ScopeType;

public class ScopeManager {

	private Map<String,ScopeType> scopes = new HashMap<>();
	
	public ScopeManager() {
		addScopeType(ScopeType.builder()
				.priority(1).name("dimension_1").build());
		addScopeType(ScopeType.builder()
				.priority(5).name("dimension_2").build());
		addScopeType(ScopeType.builder()
				.priority(10).name("dimension_3").build());
	}	
	
	private void addScopeType(ScopeType dimension) {
		scopes.put(dimension.getName(), dimension);
	}
	
	public Collection<ScopeType> getAllScopes() {
		return scopes.values();
	}
	
	public ScopeType getByName(String name) {
		return scopes.get(name);
	}
	
}