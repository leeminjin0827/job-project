package jobkorea.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jobkorea.model.dto.MemberDto;

public class IndexDao {
	private Connection conn;
	private String dburl = "jdbc:mysql://localhost:3306/jobkorea";
	private String dbuser = "root";
	private String dbpwd = "1234";
	
	// 싱글톤
	private IndexDao (){
		// 예외처리
		try {
			// 1) JDBC 클래스 드라이버 로드 : .Class.forName()
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2) 설정한 경로 / 계정 / 비밀번호로 DB 서버 연동 시도 후 결과(구현체) 반환  
			conn = DriverManager.getConnection(dburl,dbuser,dbpwd);
		}catch (Exception e) {
			System.out.println(">> DB 연동 실패 "+ e);
		}
	}
	private static IndexDao instance = new IndexDao();
	public static IndexDao getInstance() {
		return instance;
	}
	
	// 내정보
	public MemberDto myInfo(int loginMno) {
		try {
			String sql = "select mid, mpwd , mname , mgender, mdate, maddr from member where mno = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, loginMno);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				MemberDto memberDto = new MemberDto();
				memberDto.setMid(rs.getString("mid"));
				memberDto.setMpwd(rs.getString("mpwd"));
				memberDto.setMname(rs.getString("mname"));
				memberDto.setMgender(rs.getBoolean("mgender"));
				memberDto.setMdate(rs.getString("mdate"));
				memberDto.setMaddr(rs.getString("maddr"));
				
				return memberDto;
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
		
		return null;
	}
}
