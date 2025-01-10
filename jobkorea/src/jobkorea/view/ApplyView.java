package jobkorea.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import jobkorea.controller.ApplyController;
import jobkorea.model.dto.ApplyDto;
import jobkorea.model.dto.MemberDto;

public class ApplyView {
	// 싱글톤 s
	// 생성자
	private ApplyView() {}
	// 객체생성
	private static ApplyView instance = new ApplyView();
	// 메소드
	public static ApplyView getInstance() {
		return instance;
	}
	// 싱글톤 e
	
	Scanner scan = new Scanner(System.in);
	
	// 메인 
	public void apply(int loginMno) {
		while(true) {
			System.out.println(">> 1. 지원하기 2. 지원현황 3. 정보수정 4. 지원삭제 5. 로그아웃");
			int choose = scan.nextInt();
			
			if(choose == 1) {
				ListView.getInstance().cList(); // 카테고리 리스트 자동 출력
				applyC(loginMno);
			}else if(choose == 2) {
				applyR(loginMno);	// 지원리스트 자동 출력
			}else if(choose == 3) {
				applyU(loginMno);
			}else if(choose == 4) {
				applyR(loginMno);	// 지원리스트 자동 출력
				applyD();
			}else if(choose == 5) {
				MainView.getInstance().mLogout();
				break;
			}
		}
	}

	// [1] 지원
	public void applyC(int loginMno) {
		System.out.println(">> 지원할 카테고리 번호 : ");
		int choose = scan.nextInt();
		
		ArrayList<HashMap<String, String>> pList = ApplyController.getInstance().pList(choose);
		
		for(int i = 0; i < pList.size(); i++) {
			HashMap<String, String> pDto = pList.get(i);
			System.out.println(pDto);
			System.out.println();	
		}
		
		System.out.print(">> 지원할 공고 번호 : ");
		int choose2 = scan.nextInt();
		
		boolean result = ApplyController.getInstance().applyC(choose2 , loginMno);
		
		// 해당 기업의 지원리스트 회원번호 + 하기 
		
		if(result == true) {
			System.out.println(">> 지원 완료");

		}else {
			System.out.println(">> 지원 실패");
		}
	}
	
	// [2] 지원 현황 출력
	public void applyR(int loginMno) {
		ArrayList<HashMap<String, String>> aList = ApplyController.getInstance().applyR(loginMno);
		
		System.out.println("번호 \t 카테고리명");
		System.out.println();
		for(int i = 0; i < aList.size(); i++) {
			HashMap<String, String> aDto = aList.get(i);
			System.out.println(aDto);
			System.out.println();
		}
	}
	
	// [3] 정보 수정 -> 비밀번호 / 이름 / 성별 / 생년월일 / 주소
	public void applyU(int loginMno) {
		boolean mgender = false; // 샘플 값
		System.out.print(">> 수정할 비밀번호 : ");
		String mpwd = scan.next();
		System.out.print(">> 수정할 이름 : ");
		String mname = scan.next();
		System.out.print(">> 수정할 성별 (0 : 남성 / 1 : 여성) : ");
		int gender = scan.nextInt();
		if(gender == 0 ) {
			mgender = false;
		}else if (gender == 1)  {
			 mgender = true;
		}
		System.out.print(">> 수정할 생년월일 : ");
		String mdate = scan.next();
		System.out.print(">> 수정할 주소 : ");
		String maddr = scan.next();
		
		MemberDto memberDto = new MemberDto(mpwd,mname,mgender,mdate,maddr);
		
		boolean result = ApplyController.getInstance().applyU(memberDto,loginMno);
		
		if(result == true) {
			System.out.println(">> 정보 수정 완료");
		}else {
			System.out.println(">> 정보 수정 실패");
		}
	}
	
	// [4] 지원 삭제 
	public void applyD() {
		System.out.print(">> 취소할 지원번호 : ");
		int choose = scan.nextInt();
		
		boolean result = ApplyController.getInstance().applyD(choose);
		
		if(result == true) {
			System.out.println(">> 지원 취소 완료");
		}else {
			System.out.println(">> 지원 취소 실패");
		}
	}
	
	
	
	
	
	
}
