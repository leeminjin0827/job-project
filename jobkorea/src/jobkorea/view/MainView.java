package jobkorea.view;

import java.util.Scanner;

import jobkorea.controller.MainController;
//import jobkorea.Controller.MainController;
import jobkorea.model.dto.EnterpriseDto;
import jobkorea.model.dto.MemberDto;

public class MainView {
	// + 싱글톤
	private static MainView instance = new MainView();
	private MainView() {}
	public static MainView getInstance() { return instance; }
	// - 싱글톤
	Scanner scan = new Scanner( System.in );
	
	// 1 : 회원 로그인 / 회원가입 메소드 연동 -> 성공시 지원 / 후기 view 연동 
	// 2 : 기업 로그인 / 회원가입 메소드 연동 -> 성공 시 기업 view 연동 
	// 3 : 취업 연동
	
	public void run() {
		while(true) {
	      System.out.println("1.일반 2.기업");
	      int choose = scan.nextInt();
	      if( choose == 1 ) { 
	    	  System.out.println("1.일반 회원가입 2.일반 로그인 3.로그아웃");
	    	  int choose1 = scan.nextInt();
	    	  if( choose1 == 1 ) { mSignUp(); }
	    	  else if( choose1 == 2 ) { mLogin(); }
	    	  else if( choose1 == 3 ) { mLogout(); }
	      } // if end
	      else if( choose == 2 ) {
	    	  System.out.println("1.기업 회원가입 2.기업 로그인 3.로그아웃");
	    	  int choose1 = scan.nextInt();
	    	  if( choose1 == 1 ) { eSignUp(); }
	    	  else if( choose1 == 2 ) { 
	    		  int loginEno = eLogin();
	    		  if( 0 < loginEno ) { // loginEno(기업회원번호)가 0보다 크면
	    			  PostView.getInstance().pIndex(loginEno); // 공고 관련페이지로 이동
	    		  } // if end
	    	  } // else if end
	    	  else if( choose1 == 3 ) { eLogout(); }
	      } // if end 
		} // w end
	} // v end
	// [1] 회원 회원가입 메소드
	public void mSignUp() {
	         
	}
	   
	// [2] 회원 로그인 메소드
	public void mLogin() {
		// 입력
		System.out.print("아이디 : ");	String mid = scan.next();
		System.out.print("비밀번호 : ");	String mpwd = scan.next();
		// 객체화
	    MemberDto memberDto = new MemberDto();
	    memberDto.setMid(mid);
	    memberDto.setMpwd(mpwd);
	    // 컨트롤러에게 전달하고 응답 받기
	    boolean result = MainController.getInstance().mLogin(memberDto);
	    // 응답에 따른 처리
	    if( result ) {System.out.println("[로그인 성공]"); }
	    else { System.out.println("[회원정보가 없습니다.]"); }
	}
	
	// [3] 회원 로그아웃 메소드
	public void mLogout() {
	         
	}
	   
	// [1] 기업 회원가입 메소드
	public void eSignUp() {
	         
	}
	   
	// [2] 기업 로그인 메소드
	public int eLogin() {
		System.out.print("아이디 : ");	String eid = scan.next();
		System.out.print("비밀번호 : ");	String epwd = scan.next();
		// 객체화
	    EnterpriseDto enterpriseDto = new EnterpriseDto();
	    enterpriseDto.setEid(eid);
	    enterpriseDto.setEpwd(epwd);
	    // 컨트롤러에게 전달하고 응답 받기
	    int result = MainController.getInstance().eLogin(enterpriseDto);
	    // 응답에 따른 처리
	    if( 0 < result ) {
	    	System.out.println("[로그인 성공]");
	    	}
	    else { System.out.println("[회원정보가 없습니다.]"); }
	    return result;
	} // f end
	   
	// [3] 기업 로그아웃 메소드
	public void eLogout() {
	         
	}
	   
} // c end
