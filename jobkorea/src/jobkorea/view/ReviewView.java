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
         System.out.println("===== 후기 시작 페이지 =====");
         System.out.println("1.기업후기등록  2.기업후기출력  3.기업후기수정  4.기업후기삭제  5.돌아가기");
         int choose = scan.nextInt();
         if(choose == 1) {
               ArrayList<Integer> array = rPassPrint(loginMno);
               rWrite(loginMno, array);
            }
         else if(choose == 2) {rPrintAll(loginMno);}
         else if(choose == 3) {
            ArrayList<Integer> array = rPrintAll(loginMno);
            rUpdate(array);
         }
         else if(choose == 4) {
            ArrayList<Integer> array = rPrintAll(loginMno);
            rDelete(array);
         }
         else if(choose == 5) {
            break;
         }
         
      } // while end
   
   } // f run end
   
   
   
   // 후기 등록 페이지   
   public void rWrite(int loginMno, ArrayList<Integer> array) {
      System.out.println("===== 후기 등록 =====");
      System.out.print("작성할 공고번호 : "); int pno = scan.nextInt();
      System.out.print("별점 : ");      int rrating = scan.nextInt();
      scan.nextLine(); // 엔터를 기준으로 넘어가던 설정 끊어주기
      System.out.print("내용 : ");      String rcontent = scan.nextLine(); // 엔터가 아닌 한 줄이 하나의 데이터임
      
      ReviewDto reviewDto = new ReviewDto(rrating, rcontent);
      
      boolean result = ReviewController.getInstance().rWrite(reviewDto, loginMno , pno, array);

      
      if(result) {
         System.out.println("[후기 등록 완료]");
         return;
      }else {
         System.out.println("[후기 등록 실패]");
         return;
      } // if end
      
   } // f rWrite end
   
   
   // 로그인된 회원 이름 찾기
   public void findMname() {
      
   }
   
   
   
   // 합격한 기업리스트
   public ArrayList<Integer> rPassPrint(int mno) {
      System.out.println("===== 합격한 기업 리스트 =====");
      ArrayList<HashMap<String, String>> passList = ReviewController.getInstance().rPassPrint(mno);
      
      ArrayList<Integer> array = new ArrayList<Integer>();
      
      for(int index = 0; index <= passList.size()-1; index++) {
         HashMap<String, String> i = passList.get(index);
         System.out.printf(
                        "지원번호 : " + i.get("지원번호") +"\n"+
                        "공고번호 : " + i.get("공고번호") +"\n"+
                        "기업명 : "  + i.get("기업명") +"\n"+
                        "공고명 : " + i.get("공고명") +"\n"+
                        "------------------------\n"
                     );
         array.add(Integer.parseInt(i.get("공고번호")));
      } // for end
      
      return array;
   } // f rPassPrint end
   
   
   
   
   
   // 작성한 후기리스트 (기업후기 출력)
   public ArrayList<Integer> rPrintAll(int mno) {
      System.out.println("===== 작성한 후기 리스트 =====");
      ArrayList<HashMap<String, String>> printList = ReviewController.getInstance().rPrintAll(mno);
      
      ArrayList<Integer> array = new ArrayList<Integer>();
      
      for(int index = 0; index <= printList.size()-1; index++) {
         HashMap<String, String> i = printList.get(index);
         System.out.printf(
                        "후기번호 : " + i.get("후기번호") +"\n"+
                        "기업명 : " + i.get("기업명") +"\n"+
                        "별점 : " + i.get("별점") +"\n"+
                        "후기내용 : " + i.get("후기내용") +"\n"+
                        "작성일자 : " + i.get("작성일자") +"\n"+
                        "------------------------\n"
               );
         array.add(Integer.parseInt(i.get("후기번호")));
      } // for end
      return array;
   } // f rPrintAll end
   
   
   
   
   // 후기 수정 페이지
   public void rUpdate(ArrayList<Integer> array) {
      System.out.println("===== 후기 수정 =====");
      System.out.print("수정할 후기 번호 : "); int rno = scan.nextInt();
      System.out.print("별점 : ");      int rrating = scan.nextInt();
      scan.nextLine();
      System.out.print("후기내용 : ");   String rcontent = scan.nextLine();
      
      ReviewDto reviewDto = new ReviewDto(rrating, rcontent);
      
      boolean result = ReviewController.getInstance().rUpdate(reviewDto, rno, array);
      
      if(result) {
         System.out.println("[후기 수정 완료]");
      }
      else {
         System.out.println("[후기 수정 실패]");
      } // if end
      
   } // f rUpdate end
   
   
   
   
   // 후기 삭제 페이지
   public void rDelete(ArrayList<Integer> array) {
      System.out.println("===== 후기 삭제 =====");
      System.out.print("삭제할 후기 번호 : ");   int rno = scan.nextInt();
      ReviewDto reviewDto = new ReviewDto();
      reviewDto.setRno(rno);
      boolean result = ReviewController.getInstance().rDelete(reviewDto, array);
      
      if(result) {System.out.println("[삭제 성공]");}
      else {System.out.println("[삭제 실패]");
      } // if end
   } // rDelete end
   
   

   
   
   
} // class end
