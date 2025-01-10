package jobkorea.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import jobkorea.controller.ListController;

public class ListDao {
	private Connection conn;
	private String dburl = "jdbc:mysql://localhost:3306/jobkorea";
	private String dbuser = "root";
	private String dbpwd = "1234";
	
	// 싱글톤
	private ListDao (){
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
	private static ListDao instance = new ListDao();
	public static ListDao getInstance() {
		return instance;
	}
	
	
	// [1] 카테고리 리스트 출력
	public ArrayList<HashMap<String, String>> cList() {
		ArrayList<HashMap<String, String>> cList = new ArrayList<HashMap<String,String>>();
		try {
			String sql = "select * from category order by cno asc";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String cno = rs.getString("cno");
				String cname = rs.getString("cname");
				
				HashMap<String, String> map = new HashMap<String, String>();
				
				map.put("번호", cno);
				map.put("카테고리명", cname);
				
				cList.add(map);
			}
			
		}catch (SQLException e) {
			// TODO: handle exception
		}
		
		return cList;
	}
	// [2] 공고 리스트 출력
	public ArrayList<HashMap<String, String>> pList() {
		
	}
}
