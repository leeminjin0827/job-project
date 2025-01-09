package jobkorea.view;

import java.util.Scanner;

public class EnterpriseView {
	
	// + 싱글톤
	private static EnterpriseView instance = new EnterpriseView();
	private EnterpriseView() {}
	public static EnterpriseView getinstance() { return instance; }
	// - 싱글톤
	Scanner scan = new Scanner( System.in );
	
	// 0. (로그인 했을때) 메인 메뉴 메소드 // 로그아웃시 탈출
	public void Pindex() {
		while( true ) {
			System.out.println("1.공고등록 2.공고수정 3.공고삭제 4.지원현황 5.로그아웃");
			int choose = scan.nextInt();
			if( choose == 1 ) { }
			else if( choose == 2 ) { }
			else if( choose == 3 ) { }
			else if( choose == 4 ) { }
			else if( choose == 5 ) { }
		} // w end
	} // f end
	
	// 1. 공고등록 메소드
	
	// 2. 공고수정 메소드
	
	// 3. 공고삭제 메소드
	
	// 4. 지원현황 메소드
	
	// 5. 로그아웃 메소드
	
	
	
	
} // c end
