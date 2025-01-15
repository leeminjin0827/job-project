package jobkorea.controller;

import java.util.ArrayList;

import java.util.HashMap;

import jobkorea.model.dao.MainDao;
import jobkorea.model.dto.PostDto;
import jobkorea.model.dto.PostDto;
import jobkorea.model.dto.EnterpriseDto;
import jobkorea.model.dto.MemberDto;
import jobkorea.model.dto.ReviewDto;

public class MainController {
	// 싱글톤
	// 생성자
	private MainController() {}
	// 객체생성
	private static MainController instance = new MainController();
	// 메소드
	public static MainController getInstance() {
		return instance;
	}
	// 싱글톤 e
	
	private int loginMno = 0; // 로그인한 회원의 번호 저장하는 변수
 	private int loginEno = 0;
 	public int getLoginMno() { return loginMno; }
 	public int getLoginEno() { return loginEno; }
	

	// [1] 일반 회원가입 메소드
 	public boolean mSignUp(MemberDto memberDto) {
 		// 유효성 검사
 		if(memberDto.getMid().length() < 3 && memberDto.getMid().length() > 12 ) {
 			return false;
 		}
 		if(memberDto.getMpwd().length() < 3 && memberDto.getMpwd().length() > 20) {
 			return false;
 		}
	      boolean result = MainDao.getinstance().mSignUp(memberDto);
	      return result;
	}
	
    // * 로그인 번호 저장
 	
 	// [2] 일반 회원 로그인 
 	public int mLogin( MemberDto memberDto ) { // memberDto 를 매개변수로 가져옴
 		int result = MainDao.getinstance().mLogin(memberDto); // Dao에서 정보가 있으면 회원번호를 반환 없으면 0반환
 		if( result > 0 ) { // 로그인 성공 시 true , 실패 시 false 반환
 			loginMno = result; // 성공 시 회원번호를 loginMno에 저장
 			return loginMno; 
 		}else {
 			return 0; 
 		} // if end
 	} // f end
 	
    // [3] 회원 로그아웃 메소드
    public int mLogout() {
    	loginMno = 0;
    	
    	return loginMno;
    }
    
    ///////
    
    // [1] 기업 회원가입 메소드
    public boolean eSignUp(EnterpriseDto enterpriseDto) {
    	// 유효성 검사
    	if(enterpriseDto.getEid().length() < 3 && enterpriseDto.getEid().length() > 12 ) {
    		return false;
    	}
    	if(enterpriseDto.getEpwd().length() < 3&& enterpriseDto.getEpwd().length() > 12) {
    		return false;
    	}
	      boolean result = MainDao.getinstance().eSignUp(enterpriseDto);
	      return result;
	}
    
    // [2] 기업 회원 로그인 
 	public boolean eLogin( EnterpriseDto enterpriseDto ) { // memberDto 를 매개변수로 가져옴
 		int result = MainDao.getinstance().eLogin(enterpriseDto); // Dao에서 정보가 있으면 회원번호를 반환 없으면 0반환
 		if( result > 0 ) { // 로그인 성공 시 true , 실패 시 false 반환
 			loginMno = result; // 성공 시 회원번호를 loginMno에 저장
 			return true; 
 		}else {
 			return false; 
 		} // if end
 	} // f end
 	
    // [3] 기업 로그아웃 메소드
    public int eLogout() {
    	loginEno = 0;
    	
    	return loginEno;
    }
    
    ////////
    
    // [1] 우수기업 R
    public ArrayList<HashMap<String, String>> bestList() {
    	ArrayList<HashMap<String, String>> result = MainDao.getinstance().bestList();
	
    	return result;
    }
    // [2] 후기 R
    public ArrayList<HashMap<String, String>> reviewList(String ename) {
    	ArrayList<HashMap<String, String>> result = MainDao.getinstance().reviewList(ename);
    	
    	return result;
	}
}
