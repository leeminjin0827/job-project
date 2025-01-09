package jobkorea.model.dao;

public class ListDao {
	
	// + 싱글톤
	private static ListDao instance = new ListDao();
	private ListDao() {}
	public static ListDao getinstance () { return instance; }
	// - 싱글톤
	
} // f end
