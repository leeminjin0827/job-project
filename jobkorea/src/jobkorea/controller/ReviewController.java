package jobkorea.controller;

import java.util.ArrayList;
import java.util.HashMap;

import jobkorea.model.dao.ReviewDao;
import jobkorea.model.dto.ReviewDto;

public class ReviewController {
	// 싱글톤
	private static ReviewController instance = new ReviewController();
	private ReviewController() {}
	public static ReviewController getInstance() { return instance; }
	
	
	public boolean rWrite(ReviewDto reviewDto, String rname, int loginMno) {
		boolean result = ReviewDao.getInstance().rWrite(reviewDto, rname, loginMno);
		return result;

	}
	
	
	public ArrayList<HashMap<String, String>> rPassPrint(int mno) {
		ArrayList<HashMap<String, String>> passList = ReviewDao.getInstance().rPassPrint(mno);
		return passList;
		
	}
	
	
	
	public ArrayList<HashMap<String, String>> rPrintAll(int mno) {
		ArrayList<HashMap<String, String>> printList = ReviewDao.getInstance().rPrintAll(mno);
	}
	
	

	
}
