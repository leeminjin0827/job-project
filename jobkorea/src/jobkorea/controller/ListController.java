package jobkorea.controller;


import java.util.ArrayList;
import java.util.HashMap;

import jobkorea.model.dao.ListDao;
import jobkorea.model.dto.ApplyDto;
import jobkorea.view.ListView;

public class ListController {
	// 싱글톤
	private ListController (){}
	private static ListController instance = new ListController();
	public static ListController getInstance() {
		return instance;
	}
	
	// [1] 카테고리 리스트 출력
	public ArrayList<HashMap<String , String>> cList() {
		ArrayList<HashMap<String , String>> cList = ListDao.getInstance().cList();
		
		return cList;
	}
	// [2] 공고 리스트 출력
	public ArrayList<HashMap<String, String>> pList( int loginEno ) {
		ArrayList<HashMap<String , String>> pList = ListDao.getInstance().pList( loginEno );
		
		return pList;
	}
	
	// [3] 지원 리스트 출력
	public ArrayList<HashMap<String,String>> aList( int loginEno ){
		return ListDao.getInstance().aList(loginEno);
	}
}


