package jobkorea.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import jobkorea.controller.ApplyController;
import jobkorea.model.dto.ApplyDto;

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
	public void apply() {
		System.out.println(">> 1. 지원하기 2. 지원현황 3. 정보수정 4. 지원삭제 5. 로그아웃");
		int choose = scan.nextInt();
		
		if(choose == 1) {
			cList();	// 카테고리 리스트 자동 출력
			applyC();
		}else if(choose == 2) {
			applyR();	// 지원리스트 자동 출력
		}else if(choose == 3) {
			applyU();
		}else if(choose == 4) {
			applyR();	// 지원리스트 자동 출력
			applyD();
		}else if(choose == 5) {
			MainView.getInstance().mLogout();
		}
	}
	int loginNo = 1;
	// [1] 지원
	public void applyC() {
		
	}
	// [2] 지원 현황 출력
	public void applyR() {
		ArrayList<HashMap<String, String>> aList = ApplyController.getInstance().applyR(loginNo);
		
		System.out.println("번호 \t 카테고리명");
		System.out.println();
		for(int i = 0; i < aList.size(); i++) {
			HashMap<String, String> aDto = aList.get(i);
			System.out.println(aDto);
		}
	}
	// [3] 정보 수정 -> 비밀번호 / 이름 / 성별 / 생년월일 / 주소
	public void applyU() {
		
	}
	// [4] 지원 삭제 
	public void applyD() {
		
	}
	
	// [1] 카테고리 리스트 출력
	public void cList() {
		
	}
	// [2] 공고 리스트 출력
	public void pList() {
		
	}
	
	
}
