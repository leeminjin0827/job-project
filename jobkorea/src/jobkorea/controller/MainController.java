package jobkorea.controller;

import jobkorea.model.dao.MainDao;
import jobkorea.model.dto.EnterpriseDto;
import jobkorea.model.dto.MemberDto;

public class MainController {
	
	// + 싱글톤
	private static MainController instance = new MainController();
	private MainController() {}
	public static MainController getInstance() { return instance; }
	// - 싱글톤
	
	
	// * 일반 로그인 번호 저장
	private int loginMno = 0; // 로그인한 회원의 번호 저장하는 변수
	public int getLoginMno() { return loginMno; }
	// * 기업 로그인 번호 저장
	private int loginEno = 0; // 로그인한 회원의 번호 저장하는 변수
	public int getLoginEno() { return loginEno; }
	
	// 2. 일반 회원 로그인 컨트롤러 메소드
	public boolean mLogin( MemberDto memberDto ) { // memberDto 를 매개변수로 가져옴
		int result = MainDao.getInstance().mLogin( memberDto ); // Dao에서 정보가 있으면 회원번호를 반환 없으면 0반환
		if( result > 0 ) { // 로그인 성공 시 true , 실패 시 false 반환
			loginMno = result; // 성공 시 회원번호를 loginMno에 저장
			return true; 
		}else {
			return false; 
		} // if end
	} // f end
	
	// 2. 기업 회원 로그인 컨트롤러 메소드
	public int eLogin( EnterpriseDto enterpriseDto ) { // memberDto 를 매개변수로 가져옴
		int result = MainDao.getInstance().eLogin( enterpriseDto ); // Dao에서 정보가 있으면 회원번호를 반환 없으면 0반환
		if( result > 0 ) { // 로그인 성공 시 true , 실패 시 false 반환
			loginEno = result; // 성공 시 회원번호를 loginEno에 저장
			return result;
		}return 0;
	} // f end
	
} // c end
