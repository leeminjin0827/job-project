package jobkorea.controller;

public class EnterpriseController {
	// + 싱글톤
	private static EnterpriseController instance = new EnterpriseController();
	private EnterpriseController() {}
	public static EnterpriseController getinstance() { return instance; }
	// - 싱글톤
}
