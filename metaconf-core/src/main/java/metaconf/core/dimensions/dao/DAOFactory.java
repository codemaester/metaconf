package metaconf.core.dimensions.dao;

public class DAOFactory {

	private static ScopeManager scopeManager = new ScopeManager();

	public static ScopeManager getScopeManager() {
		return scopeManager;
	}
}
