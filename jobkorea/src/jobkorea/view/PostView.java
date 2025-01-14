package jobkorea.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import jobkorea.controller.ListController;
import jobkorea.controller.PostController;
import jobkorea.model.dao.ApplyDao;
import jobkorea.model.dao.ListDao;
import jobkorea.model.dto.PostDto;

public class PostView {
	
	// + 싱글톤
	private static PostView instance = new PostView();
	private PostView() {}
	public static PostView getInstance() { return instance; }
	// - 싱글톤
	Scanner scan = new Scanner( System.in );
	
	// 0. (로그인 했을때) 메인 메뉴 메소드 // 로그아웃시 탈출
	public void pIndex( int loginEno ) {
		while( true ) {
			System.out.println("1.공고등록 2.공고수정 3.공고삭제 4.지원현황 5.로그아웃");
			int choose = scan.nextInt();
			if( choose == 1 ) {  // 1
				ListView.getInstance().cList(); // 카테고리 리스트 출력
				pRegister( loginEno ); }
			else if( choose == 2 ) { // 2
				ListView.getInstance().pList( loginEno ); // 공고리스트 출력하는 페이지
				pUpdate(); }
			else if( choose == 3 ) { // 3
				System.out.println("1.삭제 2.취소");
				int choose1 = scan.nextInt();
				if( choose1 == 1 ) {
					ListView.getInstance().pList( loginEno );
					pDelete(); }
				else if( choose == 2 ) { break; }
			}
			else if( choose == 4 ) { // 4
				
			}
			else if( choose == 5 ) { // 5
				
			}
		} // w end
	} // f end
	
	// 1. 공고등록 메소드
	public void pRegister( int loginEno) {
	    System.out.println("\n[해당하는 카테고리의 번호를 입력하세요.]");	int cno = scan.nextInt();
	    System.out.println("제목 : ");								String ptitle = scan.next();
	    System.out.println("내용 : ");								String pcontent = scan.next();
	    System.out.println("경력 : ");								String phistory = scan.next();
	    System.out.println("모집인원 : ");							String pcount = scan.next();
	    System.out.println("연봉 : ");								String psalary = scan.next();
	    System.out.println("공고마감일 : ");							String pend = scan.next();
	    PostDto postDto = new PostDto( cno , ptitle , pcontent , phistory , pcount , psalary , pend );
	    boolean result = PostController.getInstance().pRegister( postDto , loginEno );
	    if( result ) { System.out.println("[공고등록 성공]"); }
	    else { System.out.println("[공고등록 실패]"); }
	} // f end
	
	// 2. 공고수정 메소드
	public void pUpdate() {
		System.out.println("\n[수정할 공고의 번호를 입력하세요.]");	int pno = scan.nextInt();
	    System.out.println("제목 : ");							String ptitle = scan.next();
	    System.out.println("내용 : ");							String pcontent = scan.next();
	    System.out.println("경력 : ");							String phistory = scan.next();
	    System.out.println("모집인원 : ");						String pcount = scan.next();
	    System.out.println("연봉 : ");							String psalary = scan.next();
	    System.out.println("공고마감일 : ");						String pend = scan.next();
	    PostDto postDto = new PostDto();
	    postDto.setPno(pno); postDto.setPtitle(ptitle); postDto.setPcontent(pcontent); postDto.setPhistory(phistory);
	    postDto.setPcount(pcount); postDto.setPsalary(psalary); postDto.setPend(pend);
	    boolean result = PostController.getInstance().pUpdate( postDto );
	    if( result ) { System.out.println("[수정 완료]"); }
	    else { System.out.println("[수정 실패]"); }
	} // f end
	
	// 3. 공고삭제 메소드
	public void pDelete( ) {
		System.out.println("[삭제할 공고 번호를 입력하세요]");
		int pno = scan.nextInt();
		boolean result = PostController.getInstance().pDelete( pno );
		if( result ) { System.out.println("삭제 성공"); }
		else { System.out.println("삭제 실패"); }
	} // f end
	
	// 4. 지원현황 메소드
	public void pCheck() {
		
	} // f end
	
	// 5. 로그아웃 메소드
	public void pLogout() {
		
	} // f end
	 
	
	
} // c end