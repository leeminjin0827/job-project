package jobkorea.view;

import java.util.Scanner;

import jobkorea.controller.IndexController;
import jobkorea.model.dto.MemberDto;
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
		while(true) {
			System.out.println(">> 1. 지원관리 2. 후기관리 3. 내정보 4. 로그아웃");
			int choose = scan.nextInt();
			
			if(choose == 1) {
				ApplyView.getInstance().apply(loginMno);
			}else if(choose == 2) {
				
			}else if (choose == 3) {
				myInfo(loginMno);
			}else if(choose == 4) {
				MainView.getInstance().eLogout();
				break;
			}
		}
	}
	
	
	 // 회원 내정보보기
    public void myInfo(int loginMno) {
    	MemberDto memberDto = IndexController.getInstance().myInfo(loginMno);
		
		String gender = "";
		if(memberDto.isMgender() == false) {
			gender = "남성";
		}else if(memberDto.isMgender() == true) {
			gender = "여성";
		}
		
		System.out.println("아이디 : " + memberDto.getMid() + " \n비밀번호 : " + memberDto.getMpwd()
						 + "\n이름 : " + memberDto.getMname() + "\n성별 : " + gender + "\n생년월일 : " + memberDto.getMdate()
						 + "\n주소 : " + memberDto.getMaddr());
	}
}
