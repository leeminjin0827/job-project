package jobkorea.model.dto;

import java.util.Scanner;

import jobkorea.view.MainView;
import jobkorea.view.ReviewView;

public class IndexView {
	private IndexView() {}
	private static IndexView instance = new IndexView();
	public static IndexView getInstance () {
		return instance;
	}
	Scanner scan = new Scanner(System.in);
	
	// 메인
	public void index(int loginMno) {
		System.out.println(">> 1. 지원관리 2. 후기관리 3. 로그아웃");
		int choose = scan.nextInt();
		
		if(choose == 1) {
			//ApplyView.getInstance().apply();
		}else if(choose == 2) {
			ReviewView.getInstance().run(loginMno);
		}else if (choose == 3) {
			MainView.getInstance().eLogout();
		}
	}
}
