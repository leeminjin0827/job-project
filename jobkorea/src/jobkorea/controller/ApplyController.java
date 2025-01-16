package jobkorea.controller;

import java.util.ArrayList;
import java.util.HashMap;

import jobkorea.model.dao.ApplyDao;
import jobkorea.model.dto.ApplyDto;
import jobkorea.model.dto.MemberDto;

public class ApplyController {
	// 싱글톤 s
	// 생성자
	private ApplyController() {}
	// 객체생성
	private static ApplyController instance = new ApplyController();
	// 메소드
	public static ApplyController getInstance() {
		return instance;
	}
	// 싱글톤 e

	
	
	// [1-1] 입력 카테고리의 공고 출력
	public ArrayList<HashMap<String, String>> pList(int choose) {
		ArrayList<HashMap<String, String>> pList = ApplyDao.getInstance().pList(choose);
		
		return pList;
	}
	
	// [1] 지원
	public boolean applyC(int choose , int choose2, int loginMno, ArrayList<Integer> pno) {
		
		boolean state = false;
		for(int i = 0; i < pno.size(); i++) {
			int j = pno.get(i);
			
			if(j == choose2) {
				state = true;
				break;
			}
		}
		if(state == false) {
			return false;
		}
		boolean result = ApplyDao.getInstance().applyC(choose, choose2, loginMno);
		return result;
	}
	
	
	// [2] 지원 현황 출력
	public ArrayList<HashMap<String, String>> applyR(int loginNo) {
		ArrayList<HashMap<String, String>> result = ApplyDao.getInstance().applyR(loginNo);
		
		return result;
	}
	// [3] 정보 수정 -> 비밀번호 / 이름 / 성별 / 생년월일 / 주소
	public boolean applyU(MemberDto memberDto,  int loginMno) {
		boolean result = ApplyDao.getInstance().applyU(memberDto,loginMno);
		
		return result;
	}
	// [4] 지원 삭제 
	public boolean applyD(int choose , ArrayList<Integer> ano) {
		// 유효성 검사의 상태를 저장하기 위한 임시 변수
		boolean state = false;
		
		for(int i = 0; i < ano.size(); i++) {
			int j = ano.get(i);
			if(j == choose) {
				state = true;
			}
		}
		
		if(state == false) {
			return false;
		}
		boolean result = ApplyDao.getInstance().applyD(choose);
		
		return result;
	}
		
	
}
