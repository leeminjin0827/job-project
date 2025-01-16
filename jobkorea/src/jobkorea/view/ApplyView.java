package jobkorea.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import jobkorea.controller.ApplyController;
import jobkorea.model.dto.ApplyDto;
import jobkorea.model.dto.MemberDto;

public class ApplyView {
	// 싱글톤 s
	// 생성자
	private ApplyView() {}
	// 객체생성
	private static ApplyView instance = new ApplyView();
	// 메소드
	public static ApplyView getInstance() {
		return instance;
	}
	// 싱글톤 e
	
	Scanner scan = new Scanner(System.in);
	
	// 메인 
	public void apply(int loginMno) {
		while(true) {
			System.out.println(">> 1. 지원하기 2. 지원현황 3. 정보수정 4. 지원삭제 5. 돌아가기");
			int choose = scan.nextInt();
			
			if(choose == 1) {
				ListView.getInstance().cList(); // 카테고리 리스트 자동 출력
				applyC(loginMno);
			}else if(choose == 2) {
				applyR(loginMno);	// 지원리스트 자동 출력
			}else if(choose == 3) {
				applyU(loginMno);
			}else if(choose == 4) {
				ArrayList<Integer> ano = applyR(loginMno);	// 지원리스트 자동 출력
				applyD(ano);
			}else if(choose == 5) {
				break;
			}
		}
	}

	// [1] 지원
	public void applyC(int loginMno) {
		System.out.println(">> 지원할 카테고리 번호 : ");
		int choose = scan.nextInt();
		
		ArrayList<HashMap<String, String>> pList = ApplyController.getInstance().pList(choose);
		ArrayList<Integer> pno = new ArrayList<Integer>(); ////// 공고번호 여러 개 담는 거 해야돼에에에에
		
		for(int i = 0; i < pList.size(); i++) {
			HashMap<String, String> pDto = pList.get(i);
			System.out.println("공고번호 : " + pDto.get("공고번호") 
							  +" \t 공고명 : " + pDto.get("제목") +" 내용 : " + pDto.get("내용")
							  +" 경력 : " + pDto.get("경력") +" 모집인원 : " + pDto.get("인원")
							  +" 연봉 : " + pDto.get("연봉") +" 공고시작일 : " + pDto.get("공고시작일")
							  +" 공고종료일 : " + pDto.get("공고종료일") +" 카테고리 : " + pDto.get("카테고리명"));	
		
			try {
				pno.add(Integer.parseInt(pDto.get("공고번호")));
			} catch (NumberFormatException e) {
			    System.out.println("공고 번호 형 변환 오류 " + e);
			}
		}
		System.out.println();

		
		System.out.print(">> 지원할 공고 번호 : ");
		int choose2 = scan.nextInt();
		
		boolean result = ApplyController.getInstance().applyC(choose , choose2 , loginMno, pno );
		
		// console 창에 뜬 공고에만 지원가능 해야함 !!! //////////
		if(result == true) {
			System.out.println(">> 지원 완료");

		}else {
			System.out.println(">> 해당 공고번호가 존재하지 않습니다.");
		}
	}
	
	// [2] 지원 현황 출력
	public ArrayList<Integer> applyR(int loginMno) {
		ArrayList<HashMap<String, String>> aList = ApplyController.getInstance().applyR(loginMno);
		
		// 유효성 검사를 위한 ArrayList
		ArrayList<Integer> ano = new ArrayList<Integer>();
		
		System.out.println();
		for(int i = 0; i < aList.size(); i++) {
			HashMap<String, String> aDto = aList.get(i);
			System.out.println("지원번호 : "+ aDto.get("지원번호") +" \t 공고명 : " + aDto.get("공고명") 
								+ " 공고종료일 : " + aDto.get("공고종료일") + " 합격여부 : " + aDto.get("합격여부"));
			
			// Integer 타입에 담기 위해 형 변환 -> 변환 오류 시 예외처리
			try {
				ano.add(Integer.parseInt(aDto.get("지원번호")));
			}catch (NumberFormatException e) {
				System.out.println("지원번호 형변환 오류 " + e);
			}
		}
		System.out.println();
		
		// 비교를 위해 지원번호를 담은 배열 반환
		return ano;
	}
	
	// [3] 정보 수정 -> 비밀번호 / 이름 / 성별 / 생년월일 / 주소
	public void applyU(int loginMno) {
		boolean mgender = false; // 샘플 값
		System.out.print(">> 수정할 비밀번호 : ");
		String mpwd = scan.next();
		System.out.print(">> 수정할 이름 : ");
		String mname = scan.next();
		System.out.print(">> 수정할 성별 (0 : 남성 / 1 : 여성) : ");
		int gender = scan.nextInt();
		if(gender == 0 ) {
			mgender = false;
		}else if (gender == 1)  {
			 mgender = true;
		}

		System.out.print(">> 수정할 생년월일 : ");
		String mdate = scan.next();
		scan.nextLine();
		System.out.print(">> 수정할 주소 : ");
		String maddr = scan.nextLine();
		
		MemberDto memberDto = new MemberDto(mpwd,mname,mgender,mdate,maddr);
		
		boolean result = ApplyController.getInstance().applyU(memberDto,loginMno);
		
		if(result == true) {
			System.out.println(">> 정보 수정 완료");
		}else {
			System.out.println(">> 정보 수정 실패");
		}
	}
	
	// [4] 지원 삭제 
		// 유효성 검사를 위해 지원번호를 매개 변수로 받음
	public void applyD(ArrayList<Integer> ano) {
		System.out.print(">> 취소할 지원번호 : ");
		int choose = scan.nextInt();
		
		boolean result = ApplyController.getInstance().applyD(choose, ano);
		
		if(result == true) {
			System.out.println(">> 지원 취소 완료");
		}else {
			System.out.println(">> 해당 지원번호가 존재하지 않습니다.");
		}
	}
	
	
	
	//// + 이미 지원한 공고 중복 지원 불가 기능
	//// + 내정보보기 기능 - 회원가입메소드 취합 시 연동하기
	//// + 돌아가기 기능
	
	
	
}
