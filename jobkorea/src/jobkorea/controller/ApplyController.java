package jobkorea.controller;

import java.util.ArrayList;
import java.util.HashMap;

import jobkorea.model.dao.ApplyDao;
import jobkorea.model.dto.ApplyDto;

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

	// [1] 지원
	public void applyC() {
			
	}
	// [2] 지원 현황 출력
	public ArrayList<HashMap<String, String>> applyR(int loginNo) {
		ArrayList<HashMap<String, String>> result = ApplyDao.getInstance().applyR(loginNo);
		
		return result;
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
