package jobkorea.controller;

import jobkorea.model.dao.IndexDao;
import jobkorea.model.dto.MemberDto;

public class IndexController {
	// 싱글톤
	private IndexController() {}
	private static IndexController instance = new IndexController();
	public static IndexController getInstance() {
		return instance;
	}
	
	// 내정보 
	
	public MemberDto myInfo(int loginMno) {
		MemberDto memberDto = IndexDao.getInstance().myInfo(loginMno);
		
		return memberDto;
	}
}
