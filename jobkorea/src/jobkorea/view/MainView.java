package jobkorea.view;

import java.util.ArrayList;




import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import jobkorea.controller.MainController;
import jobkorea.model.dto.PostDto;
import jobkorea.model.dto.EnterpriseDto;
import jobkorea.model.dto.MemberDto;
import jobkorea.model.dto.ReviewDto;
import jobkorea.model.dao.MainDao;

public class MainView {
	// 싱글톤 s
	// 생성자
	private MainView() {}
	// 객체 생성
	private static MainView instance = new MainView();
	public static MainView getInstance () {
		return instance;
	}
	// 싱글톤 e

	Scanner scan = new Scanner(System.in);

	// 1 : 회원 로그인 / 회원가입 메소드 연동 -> 성공 시 지원 / 후기 view 연동 
	// 2 : 기업 로그인 / 회원가입 메소드 연동 -> 성공 시 기업 view 연동 
	// 3 : 취업 연동
	public void run() {
		
		while(true) {
			
			System.out.println(">> 1.일반 2.기업 3.취업");
			int choose = scan.nextInt();

			if(choose == 1) {
			
				System.out.println(">> 1. 일반회원가입 2. 일반로그인 3. 메인페이지");
				int choose2 = scan.nextInt();
				
				if(choose2 == 1) {
					mSignUp();
				}else if(choose2 == 2) {
					int loginMno = mLogin();	
					if(loginMno > 0) { // 성공 시 지원 / 후기 View 연동
						IndexView.getInstance().index(loginMno);
					}
				}
				
			}else if(choose == 2) {
				System.out.println(">> 1. 기업회원가입 2. 기업 로그인 3. 메인페이지");
				
				int choose3 = scan.nextInt();
				
				if(choose3 == 1) {
					eSignUp();
				}else if(choose3 == 2) {
					 eLogin();	// 성공 시 기업 View 연동
				}
			}else if(choose == 3) {
				System.out.println(">> 1. 우수기업 2. 기업후기 3. 메인페이지");
				int choose4 = scan.nextInt();
				
				if(choose4 == 1) {
					bestList();
				}else if(choose4 == 2) {
					reviewList();
				}
			}
			
		}
		
		
	}
	// 회원가입 로그인 결과를 boolean 으로 반환받아 변수에 반환값을 저장 -> 성공 : 다음 view 연동 실패 : break;

	 // [1] 일반 회원가입 메소드 / 타입 boolean
	 public void mSignUp() {
		  System.out.println("===== 일반 회원가입 =====");
	      System.out.print("아이디 : ");		String mid = scan.next();
	      System.out.print("비밀번호 : ");		String mpwd = scan.next();
	      System.out.print("이름 : ");		String mname = scan.next();
	      System.out.print("성별(0.남/1.여) : ");		int gender = scan.nextInt();
	      

	      boolean mgender = false; // 0 = 남성(false) / 기본값 설정
	      
	      if(gender == 1) { // 
	    	  mgender = true; // 1 = 여성(true)
	    	  // System.out.println(mgender);
	      }else if(gender == 0 ) {
	    	  mgender = false;
	    	  // System.out.println(mgender);
	      }else {
		      /// 성별 입력 시 0 ,1 외 숫자 입력 시 남성 출력되는 거 해결하기 -> boolean 으로 설계해서 유효성검사 못할지도.....
	    	  System.out.println(">> 성별 입력 시 유효한 값으로 입력하세요.");
	      }

     
	      System.out.print("생년월일 : ");		String mdate = scan.next();
	      scan.nextLine();	 // !입력값에서 띄어쓰기가 안넘어가서 추가했어요!
	      System.out.print("주소 : ");		String maddr = scan.nextLine(); // next() -> nextLine() 으로 변경
	      
	      MemberDto memberDto = new MemberDto();
	      memberDto.setMid(mid);
	      memberDto.setMpwd(mpwd);
	      memberDto.setMname(mname);
	      memberDto.setMgender(mgender);
	      memberDto.setMdate(mdate);
	      memberDto.setMaddr(maddr);
	     	      
	      int result = MainController.getInstance().mSignUp(memberDto);

	      if(result == 0) {
	    	  System.out.println(">> 회원 회원가입 성공");
	      }else if(result == 1){
	    	  System.out.println(">> 아이디는 3 ~ 12 자리로 입력해주세요.");
	      }else if (result == 2) {
	    	  System.out.println(">> 비밀번호는 3 ~ 12 자리로 입력해주세요.");
    	  }

	   }
	 
	// [2] 회원 로그인 메소드 
    public int mLogin() {
		// 입력
		System.out.print("아이디 : ");	String mid = scan.next();
		System.out.print("비밀번호 : ");	String mpwd = scan.next();
		// 객체화
	    MemberDto memberDto = new MemberDto();
	    memberDto.setMid(mid);
	    memberDto.setMpwd(mpwd);
	    // 컨트롤러에게 전달하고 응답 받기
	    int result = MainController.getInstance().mLogin(memberDto);
	    // 응답에 따른 처리
	    if( result > 0 ) {
	    	System.out.println(">> 로그인 성공"); 
	    	return result;
	    }
	    else {
	    	System.out.println(">> 회원정보가 없습니다."); 
	    }
	    return 0;
	}
    
    // [3] 회원 로그아웃 메소드
    public void mLogout() {
    	int result = MainController.getInstance().mLogout();
    	
    	if(result == 0) {
    		System.out.println(">> 로그아웃 성공");
    		return;
    	}
    }
    
    
   
    
    
    // [1] 기업 회원가입 메소드
    public void eSignUp() {
		System.out.println("===== 기업 회원가입 =====");
		System.out.print("아이디 : ");		String eid = scan.next();
		System.out.print("비밀번호 : ");	String epwd = scan.next();
		System.out.print("기업명 : ");		String ename = scan.next();
		System.out.print("주소 : ");		String eaddr = scan.next();
		
		EnterpriseDto enterpriseDto = new EnterpriseDto();
		enterpriseDto.setEid(eid);
		enterpriseDto.setEpwd(epwd);
		enterpriseDto.setEname(ename);
		enterpriseDto.setEaddr(eaddr);
			   
		int result = MainController.getInstance().eSignUp(enterpriseDto);
		
		if(result == 0) {
			System.out.println(">> 기업 회원가입 성공");
		}else if(result == 1){
			System.out.println(">> 아이디는 3 ~ 12 자리로 입력해주세요.");
		}else if (result == 2) {
			System.out.println(">> 비밀번호는 3 ~ 12 자리로 입력해주세요.");
		} 
   }
    // [2] 기업 로그인 메소드
 	public void eLogin() {
 		System.out.print("아이디 : ");	String eid = scan.next();
 		System.out.print("비밀번호 : ");	String epwd = scan.next();
 		// 객체화
 	    EnterpriseDto enterpriseDto = new EnterpriseDto();
 	    enterpriseDto.setEid(eid);
 	    enterpriseDto.setEpwd(epwd);
 	    // 컨트롤러에게 전달하고 응답 받기
 	    boolean result = MainController.getInstance().eLogin(enterpriseDto);
 	    // 응답에 따른 처리
 	    if( result ) {
 	    	System.out.println(">> 로그인 성공");
 	    	// EnterpriscView 메인메뉴 메소드 호출
 	    	//EnterpriseView.getinstance().post();
 	    	}
 	    else { System.out.println(">> 회원정보가 없습니다."); }
 	}
 	
    // [3] 기업 로그아웃 메소드
    public void eLogout() {
       int result = MainController.getInstance().eLogout();
       
       if(result == 0) {
    	   System.out.println(">> 로그아웃 성공");
    	   return;
       }
    }
    
    
    
    // [1] 우수기업 R
    public void bestList() {
    	ArrayList<HashMap<String , String>> bList = MainController.getInstance().bestList();
    	
    	System.out.println("========= 우수기업리스트 =========");
    	System.out.println();
    	for(int i = 0; i < bList.size() ; i++) {
    		HashMap<String, String> bDto = bList.get(i);
    		System.out.println("기업명 : " + bDto.get("기업명") + "\t 별점 : " + bDto.get("별점"));
    	}
    	System.out.println();
	}
    
    // [2] 후기 R
    public void reviewList() {
    	
    	System.out.print(">> 후기를 볼 기업명 : ");
		String ename = scan.next();
    	ArrayList<HashMap<String , String>> rList = MainController.getInstance().reviewList(ename);
    	
    	boolean state = false;
    	System.out.println();
    	for(int i = 0; i < rList.size() ; i++) {
    		HashMap<String, String> rDto = rList.get(i);
    		System.out.println("기업명 : " +rDto.get("기업명") + "\t 후기 : " + rDto.get("후기") + "\t 별점 : " + rDto.get("별점"));
    		state = true;
    	}
    	
    	if(state == false) {
    		System.out.println(">> 해당 기업의 후기가 존재하지 않습니다.");
    	}
    	System.out.println();
    	
	}
    
}



