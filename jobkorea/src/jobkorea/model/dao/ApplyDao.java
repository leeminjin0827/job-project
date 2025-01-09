package jobkorea.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import jobkorea.model.dto.ApplyDto;

import java.sql.DriverManager;
import java.sql.DriverManager;
import java.sql.DriverManager;

public class ApplyDao {
	private Connection conn;
	private String dburl = "jdbc:mysql://localhost:3306/jobkorea";
	private String dbuser = "root";
	private String dbpwd = "1234";
	
	// 싱글톤 s
	// 생성자
	private ApplyDao () {
		// 예외처리
		try {
			// 1) JDBC 클래스 드라이버 로드 : .Class.forName()
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2) 설정한 경로 / 계정 / 비밀번호로 DB 서버 연동 시도 후 결과(구현체) 반환  
			conn = DriverManager.getConnection(dburl,dbuser,dbpwd);
			System.out.println(">> DB 연동 성공");
		}catch (Exception e) {
			System.out.println(">> DB 연동 실패 "+ e);
		}
	}
	// 객체생성
	private static ApplyDao instance = new ApplyDao();
	// 메소드
	public static ApplyDao getInstance() {
		return instance;
	}
	// 싱글톤 e

	// [1] 지원
	public void applyC() {
			
	}
	// [2] 지원 현황 출력
	public ArrayList<HashMap<String, String>> applyR(int loginNo) {
		ArrayList<HashMap<String, String>> aList = new ArrayList<>();
		
		try {
			String sql = "select p.ptitle , p.pend , a.apass , a.ano from apply a join post p on a.pno = p.pno  where a.mno = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, loginNo);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String ptitle = rs.getString("ptitle");
				String pend = rs.getString("pend");
				boolean apass = rs.getBoolean("apass");
				
				HashMap<String, String> map = new HashMap<String, String>();
				
				map.put("공고명", ptitle);
				map.put("공고종료일", pend);
				map.put("합격여부", apass + "");	// boolean 타입의 apass 를 map 에 넣기 위해 String 타입으로 변환함
				
				aList.add(map);
			}
			
			
		}catch (SQLException e) {
			System.out.println(e);
		}
		
		return aList;
	}
	// [3] 정보 수정 -> 비밀번호 / 이름 / 성별 / 생년월일 / 주소
	public void applyU() {
			
	}
	// [4] 지원 삭제 
	public void applyD() {
			
	}
		
	// [1] 카테고리 리스트 출력
	public void cList() {
			
	}
	// [2] 공고 리스트 출력
	public void pList() {
			
	}
}
