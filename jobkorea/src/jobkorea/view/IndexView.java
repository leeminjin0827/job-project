package jobkorea.view;

import java.util.Scanner;

import jobkorea.view.MainView;

public class IndexView {
	private IndexView() {}
	private static IndexView instance = new IndexView();
	public static IndexView getInstance () {
		return instance;
	}
	Scanner scan = new Scanner(System.in);
	
	// 메인
	public void index(int loginMno) { // 로그인된 회원벝호
		System.out.println(">> 1. 지원관리 2. 후기관리 3. 로그아웃");
		int choose = scan.nextInt();
		
		if(choose == 1) {
			ApplyView.getInstance().apply(loginMno);
		}else if(choose == 2) {
			
		}else if (choose == 3) {
			MainView.getInstance().eLogout();
		}
	}
}