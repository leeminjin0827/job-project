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
	
	
	
	
	
	public boolean rWrite(ReviewDto reviewDto, String rname, int loginMno) {
		try {
			String sql = "insert into review( rcontent, rrating, mno) values(?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, reviewDto.getRrating());
			ps.setString(2, reviewDto.getRcontent());
			ps.setInt(3, loginMno);
			
			int result = ps.executeUpdate();
			if(result == 1) {return true;}
		}catch(SQLException e) {System.out.println(e);}
		return false;		
	}
	
	
	
	public ArrayList<HashMap<String, String>> rPassPrint(int mno) {
		ArrayList<HashMap<String, String>> passList = new ArrayList<HashMap<String, String>>();
		try {
			String sql = "select a.ano, p.ptitle, e.ename from apply as a join post as p on a.pno = p.pno join member as m on a.mno = m.mno join enterprise as e on p.eno = e.eno where a.apass = true and a.mno = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, mno);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int ano = rs.getInt("ano");
				String ptitle = rs.getString("ptitle");
				String ename = rs.getString("ename");
				
				HashMap<String, String> map = new HashMap<String, String>();
				
				map.put("지원번호", ano + "");
				map.put("공고명", ptitle);
				map.put("기업명", ename);

				passList.add(map);
			}
			
		}catch(SQLException e) {System.out.println(e);}
		return passList;
	}
	
	
	
	public ArrayList<HashMap<String, String>> rPrintAll(int mno) {
		ArrayList<HashMap<String, String>> printList = new ArrayList<HashMap<String, String>>();
		
		try {
			String sql = "select r.rno, e.ename, r.rrating, r.rcontent, r.rdate from review as r join enterprise as e on r.eno = e.eno join member as m on r.mno = m.mno where r.mno = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, mno);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int rno = rs.getInt("rno");
				
			}
			
		}catch(SQLException e) {System.out.println(e);}
		
	}
	
	


	
}







