package jobkorea.controller;

import java.util.ArrayList;
import java.util.HashMap;

import jobkorea.model.dao.ListDao;
import jobkorea.model.dto.ApplyDto;
import jobkorea.model.dto.PostDto;

public class ListController {
	// + 싱글톤
	private static ListController instance = new ListController();
	private ListController() {}
	public static ListController getInstance() { return instance; }
	// - 싱글톤
	
	// 샘플로 제작
	
	// 1-1 전체 카테고리 컨트롤러 메소드
	public ArrayList<HashMap<String , String>> cList() {
		System.out.println("[전체 카테고리 리스트]");
		ArrayList<HashMap<String , String>> cList = ListDao.getInstance().cList();
		
		return cList;
	} // f end
	
	// 1-2 개별 카테고리 컨트롤러 메소드
	public void eCategory() {
		
	} //f end
	
	// 2-1 전체 공고리스트 컨트롤러 메소드
	public ArrayList<HashMap<String , String>> pList() {
		ArrayList<HashMap<String , String>> pList = ListDao.getInstance().pList();
		
		return pList;
	} // f end
	
	// 2-2 개별 공고리스트 컨트롤러 메소드
	public PostDto PfindById( int pno ) {
		
	} // f end
	
	// 3-1 전체 지원리스트 컨트롤러 메소드
	public ArrayList<ApplyDto> AfindAll(){
		
	} // f end
	
	// 3-2 개별 지원리스트 컨트롤러 메소드
	public ApplyDto AfindById( int ano ) {
		
	} // f end
	
} // c end
