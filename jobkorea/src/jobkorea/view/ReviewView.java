package jobkorea.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import jobkorea.controller.ReviewController;
import jobkorea.model.dto.ReviewDto;

public class ReviewView {
	// 싱글톤
	private static ReviewView instance = new ReviewView();
	private ReviewView() {}
	public static ReviewView getInstance() { return instance; }
	
	Scanner scan = new Scanner(System.in);
	
	// 후기 시작 페이지
	public void run(int loginMno) {
		while(true) {
			System.out.println("===== 후기페이지 =====");
			System.out.println("1.기업후기등록  2.기업후기출력  3.기업후기수정  4.기업후기삭제  5.돌아가기");
			int choose = scan.nextInt();
			if(choose == 1) {
					rPassPrint(loginMno);
					rWrite(loginMno);
				}
			else if(choose == 2) {rPrintAll(loginMno);}
			else if(choose == 3) {
				rPrintAll(loginMno);
				rUpdate();
			}
			else if(choose == 4) {
				rPrintAll(loginMno);
				rDelete();
			}
			else if(choose == 5) {
				break;
			}
			
		} // while end
	
	} // f run end
	
	
	
	// 후기 등록 페이지
	
	public void rWrite(int loginMno) {
		System.out.print("작성할 공고번호 : "); int pno = scan.nextInt();
		System.out.print("별점 : ");		int rrating = scan.nextInt();
		System.out.print("내용 : ");		String rcontent = scan.next();
		
		ReviewDto reviewDto = new ReviewDto(rrating, rcontent);
		
		boolean result = ReviewController.getInstance().rWrite(reviewDto, loginMno , pno);
		
		if(result) {
			System.out.println("[후기 등록 완료]");
			return;
		}else {
			System.out.println("[후기 등록 실패]");
			return;
		}
		
	}
	
	
	// 합격한 기업리스트
	public void rPassPrint(int mno) {
		ArrayList<HashMap<String, String>> passList = ReviewController.getInstance().rPassPrint(mno);
		for(int index = 0; index <= passList.size()-1; index++) {
			HashMap<String, String> i = passList.get(index);
			System.out.printf("");
			System.out.printf(
								"지원번호 : " + i.get("지원번호") +"\n"+
								"공고번호 : " + i.get("공고번호") +"\n"+
								"기업명 : "  + i.get("기업명") +"\n"+
								"공고명 : " + i.get("공고명") + "\n"+
								"=====================\n"
							);
		} // for end
	} // f rPassPrint end
	
	
	
	
	
	// 작성한 후기리스트 (기업후기 출력)
	public void rPrintAll(int mno) {
		ArrayList<HashMap<String, String>> printList = ReviewController.getInstance().rPrintAll(mno);
		for(int index = 0; index <= printList.size()-1; index++) {
			HashMap<String, String> i = printList.get(index);
			System.out.printf(
								"후기번호 : " + i.get("후기번호") +
								"기업명 : " + i.get("기업명") +
								"별점 : " + i.get("별점") +
								"후기내용 : " + i.get("후기내용") + 
								"작성일자 : " + i.get("작성일자") // ??
					);
		} // for end
	} // f rPrintAll end
	
	
	
	
	// 후기 수정 페이지
	public void rUpdate() {
		System.out.print("수정할 후기 번호 : "); int rno = scan.nextInt();
		System.out.print("별점 : ");		int rrating = scan.nextInt();
		System.out.print("후기내용 : ");	String rcontent = scan.next();
		
		ReviewDto reviewDto = new ReviewDto(rrating, rcontent);
		
		boolean result = ReviewController.getInstance().rUpdate(reviewDto, rno);
		
		if(result) {
			System.out.println("[후기 수정 완료]");
		}
		else {
			System.out.println("[후기 수정 실패]");
		}
		
	}
	
	
	
	
	// 후기 삭제 페이지
	public void rDelete() {
		
	}
	
	
	
	// 로그아웃 
	
	
	
	
	
	
	
	
	
	
} // class end











































