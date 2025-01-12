package jobkorea.view;

import java.util.ArrayList;
import java.util.HashMap;

import jobkorea.controller.ListController;

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
			HashMap<String, String> aDto = cList.get(i);
			System.out.println(aDto);
		}
	}
	// [2] 공고 리스트 출력
	public void pList() {
		ArrayList<HashMap<String , String>> pList = ListController.getInstance().pList();
	}
} // c end