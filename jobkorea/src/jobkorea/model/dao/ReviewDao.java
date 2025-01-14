package jobkorea.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import jobkorea.model.dto.ReviewDto;

public class ReviewDao {
	private Connection conn;
	   private String dburl = "jdbc:mysql://localhost:3306/jobkorea";
	   private String dbuser = "root";
	   private String dbpwd = "1234";
	
	// 싱글톤
	private static ReviewDao instance = new ReviewDao();
	private ReviewDao() {
		// 예외처리
		try {
		// 1) JDBC 클래스 드라이버 로드 : .Class.forName()
		Class.forName("com.mysql.cj.jdbc.Driver");
		// 2) 설정한 경로 / 계정 / 비밀번호로 DB 서버 연동 시도 후 결과(구현체) 반환  
		conn = DriverManager.getConnection(dburl,dbuser,dbpwd);
		System.out.println(">> DB 연동 성공");
		}catch (Exception e) {
		System.out.println(">> DB 연동 실패 "+ e);
		} // catch end
	}
	public static ReviewDao getInstance() { return instance; }
	
	
	
	
	// 후기 등록
	public boolean rWrite(ReviewDto reviewDto, int loginMno, int pno) {
		try {
			String sql = "insert into review(rrating, rcontent, mno, pno) values(?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, reviewDto.getRrating());
			ps.setString(2, reviewDto.getRcontent());
			ps.setInt(3, loginMno);
			ps.setInt(4, pno);
			
			int result = ps.executeUpdate();
			if(result == 1) {return true;}
		}catch(SQLException e) {System.out.println(e);}
		return false;		
	}
	
	
	// 합격한 기업리스트
	public ArrayList<HashMap<String, String>> rPassPrint(int mno) {
		ArrayList<HashMap<String, String>> passList = new ArrayList<HashMap<String, String>>();
		try {
			String sql = "select a.ano , p.ptitle , e.ename , p.pno from apply as a join post as p on a.pno = p.pno join member as m on a.mno = m.mno join enterprise e on p.eno = e.eno where a.apass = true and a.mno = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, mno);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int ano = rs.getInt("ano");
				String ptitle = rs.getString("ptitle");
				String ename = rs.getString("ename");
				int pno = rs.getInt("pno");
				
				HashMap<String, String> map = new HashMap<String, String>();
				
				map.put("지원번호", ano + "");
				map.put("공고명", ptitle);
				map.put("기업명", ename);
				map.put("공고번호", pno + "");

				passList.add(map);
			} // while end
			
		}catch(SQLException e) {System.out.println(e);}
		return passList;
	} // f rPassPrint end
	
	
	// 작성한 후기리스트 (기업후기 출력)
	public ArrayList<HashMap<String, String>> rPrintAll(int mno) {
		ArrayList<HashMap<String, String>> printList = new ArrayList<HashMap<String, String>>();
		
		try {
			String sql = "select r.rno, e.ename ,  r.rcontent , r.rrating , r.rdate from review r join post p on r.pno = p.pno join enterprise e on p.eno = e.eno join member m on r.mno = m.mno where r.mno = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, mno);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int rno = rs.getInt("rno");
				String ename = rs.getString("ename");
				int rrating = rs.getInt("rrating");
				String rcontent = rs.getString("rcontent");
				String rdate = rs.getString("rdate");
				
				HashMap<String, String> map = new HashMap<String, String>();
				
				map.put("후기번호", rno + "");
				map.put("기업명", ename);
				map.put("별점", rrating + "");
				map.put("후기내용", rcontent);
				map.put("작성일자", rdate); // now() 사용했는데 String으로 넣어도 되나요
				
				printList.add(map);
				
			} // while end
			
		}catch(SQLException e) {System.out.println(e);}
		return printList;
	} // f rPrintAll end
	
	
	// 후기 수정
	public boolean rUpdate(ReviewDto reviewDto, int rno) {
		try {
			String sql = "update review set rcontent = ?, rrating = ? where rno = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, reviewDto.getRcontent());
			ps.setInt(2, reviewDto.getRrating());
			ps.setInt(3, rno);
			int count = ps.executeUpdate();
			if(count == 1) {return true;}
		}catch(SQLException e) {System.out.println(e);}
		return false;
		
	}
	


	
} // class end













