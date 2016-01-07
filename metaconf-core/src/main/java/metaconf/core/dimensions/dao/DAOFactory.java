package metaconf.core.dimensions.dao;

public class DAOFactory {

	private static DimensionManager dimensionManager = new DimensionManager();

	public static DimensionManager getDimensionManager() {
		return dimensionManager;
	}
}
