package jobkorea.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import jobkorea.controller.ListController;
import jobkorea.model.dao.ApplyDao;
import jobkorea.model.dao.ListDao;

public class EnterpriseView {
	
	// + 싱글톤
	private static EnterpriseView instance = new EnterpriseView();
	private EnterpriseView() {}
	public static EnterpriseView getInstance() { return instance; }
	// - 싱글톤
	Scanner scan = new Scanner( System.in );
	
	// 0. (로그인 했을때) 메인 메뉴 메소드 // 로그아웃시 탈출
	public void pIndex() {
		while( true ) {
			System.out.println("1.공고등록 2.공고수정 3.공고삭제 4.지원현황 5.로그아웃");
			int choose = scan.nextInt();
			if( choose == 1 ) { pRegister(); }
			else if( choose == 2 ) { }
			else if( choose == 3 ) { }
			else if( choose == 4 ) { }
			else if( choose == 5 ) { }
		} // w end
	} // f end
	
	// 1. 공고등록 메소드
	public void pRegister() {
		ListView.getInstance().cList();
	    System.out.println("\n해당하는 카테고리의 번호를 입력하세요.");
	    int cno = scan.nextInt();
	    ArrayList<HashMap<String, String>> result = ApplyDao.getInstance().cList();
	} // f end
	
	// 2. 공고수정 메소드
	public void pUpdate() {
		
	} // f end
	
	// 3. 공고삭제 메소드
	public void pDelete() {
		
	} // f end
	
	// 4. 지원현황 메소드
	public void pCheck() {
		
	} // f end
	
	// 5. 로그아웃 메소드
	public void pLogout() {
		
	} // f end
	 
	
	
} // c end
