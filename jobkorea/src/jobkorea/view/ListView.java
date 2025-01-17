package jobkorea.view;


import java.util.ArrayList;

import java.util.HashMap;

import jobkorea.controller.ListController;
import jobkorea.model.dto.ApplyDto;

public class ListView {
	// 싱글톤
	private ListView (){}
	private static ListView instance = new ListView();
	public static ListView getInstance() {
		return instance;
	}
	

	// [1] 카테고리 리스트 출력
	public void cList() {
		ArrayList<HashMap<String , String>> cList = ListController.getInstance().cList();
		
		System.out.println("번호 \t 카테고리명");
		System.out.println();
		for(int i = 0; i < cList.size(); i++) {
			HashMap<String, String> cDto = cList.get(i);
			System.out.println("카테고리번호 : " + cDto.get("번호") + "\t 카테고리명 : " + cDto.get("카테고리명"));
		}
		System.out.println();
	}
	// [2] 공고 리스트 출력
	public ArrayList<Integer> pList( int loginEno ) {	
		ArrayList<HashMap<String , String>> pList = ListController.getInstance().pList( loginEno );
		// 로그인된 기업번호 매개변수로 controller , dao 에 전달해 주세용. -> 로그인된 기업 공고만 나와야되니까!
		ArrayList<Integer> array = new ArrayList<Integer>();
		
		System.out.println("====== 공고 리스트 ======");
		for(int i = 0; i < pList.size(); i++) {
			HashMap<String, String> pDto = pList.get(i);
			array.add(Integer.parseInt(pDto.get("공고번호")));
			System.out.println("\n공고번호 : " + pDto.get("공고번호") 
			  +" \n공고명 : " + pDto.get("제목") +"\n내용 : " + pDto.get("내용")
			  +" \n경력 : " + pDto.get("경력") +"\n모집인원 : " + pDto.get("인원")
			  +" \n연봉 : " + pDto.get("연봉") +"\n공고시작일 : " + pDto.get("공고시작일")
			  +" \n공고종료일 : " + pDto.get("공고종료일") +"\n카테고리 : " + pDto.get("카테고리명") + "\n기업명 : " + pDto.get("기업명"));
			System.out.println("======================");
		}
		return array;
	}
	
	// [3] 지원 리스트 출력
	public ArrayList<Integer> aList( int loginEno ) {
		ArrayList<HashMap<String,String>> aList = ListController.getInstance().aList( loginEno);
		ArrayList<Integer> array = new ArrayList<Integer>();
		
		System.out.println("====== 지원 리스트 ======");
		for( int i = 0; i <= aList.size() -1 ; i++) {
			HashMap<String,String> map = aList.get(i);
			array.add(Integer.parseInt(map.get("지원번호")));
			System.out.printf("지원번호 : " + map.get("지원번호") + "\n공고제목 : " + map.get("공고번호")
					+ "\n회원이름 : " +map.get("회원번호")  + "\n합격여부 : " + map.get("apass")+" \n");
			System.out.println("======================");
		}
		return array;
	}
	

}
