package jobkorea.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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
			} // w end
		}catch (SQLException e) { System.out.println( e ); }
		return cList;
	} // f end
	
	// [2] 공고 리스트 출력
	public ArrayList<HashMap<String, String>> pList( int loginEno ) { // 로그인된 기업번호 매개변수로 받아주세요
		ArrayList<HashMap<String, String>> pList = new ArrayList<HashMap<String,String>>();
		try {
			String sql = "select p.pno , p.ptitle, p.pcontent , p.phistory, p.pcount , p.psalary, p.pstart, p.pend , c.cname , e.ename "
					+ "	from post p join category c on p.cno = c.cno join enterprise e on p.eno = e.eno where p.eno = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, loginEno); // 매개변수로 전달받은 기업번호 넣어주세요.
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int pno = rs.getInt("pno");
				String ptitle = rs.getString("ptitle");
				String pcontent = rs.getString("pcontent");
				String phistory = rs.getString("phistory");
				String pcount = rs.getString("pcount");
				String psalary = rs.getString("psalary");
				String pstart = rs.getString("pstart");
				String pend = rs.getString("pend");
				String cname = rs.getString("cname");
				String ename = rs.getString("ename");
				
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("공고번호", pno + "");
				map.put("제목", ptitle );
				map.put("내용", pcontent );
				map.put("경력", phistory );
				map.put("인원", pcount );
				map.put("연봉", psalary);
				map.put("공고시작일",pstart );
				map.put("공고종료일", pend);
				map.put("카테고리명", cname);
				map.put("기업명", ename);
				
				pList.add(map);
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
		
		return pList;
	}
	
} // f end
