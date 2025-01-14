package jobkorea.model.dao;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import jobkorea.model.dto.PostDto;
import jobkorea.model.dto.EnterpriseDto;
import jobkorea.model.dto.MemberDto;
import jobkorea.model.dto.ReviewDto;

import jobkorea.controller.MainController;


public class MainDao {
	
	private Connection conn;
	   private String dburl = "jdbc:mysql://localhost:3306/jobkorea";
	   private String dbuser = "root";
	   private String dbpwd = "1234";
	   
	// + 싱글톤
	private static MainDao instance = new MainDao();
	private MainDao() {
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
	} // f end
	public static MainDao getinstance() { return instance; }
	// - 싱글톤
	
	// [1] 일반 회원가입 SQL 처리 메소드 
		public boolean mSignUp(MemberDto memberDto) {
			try {
				// SQL 작성
				
				String sql = "insert into member(mid, mpwd, mname, mgender, mdate, maddr) values(?, ?, ?, ?, ?, ?)";

				// DB와 연동된 곳에 SQL 기재
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ps.setString(1, memberDto.getMid());
				ps.setString(2, memberDto.getMpwd());
				ps.setString(3, memberDto.getMname());
				ps.setBoolean(4, memberDto.isMgender());
				ps.setString(5, memberDto.getMdate());
				ps.setString(6, memberDto.getMaddr());
						
				// 기재된 SQL을 실행하고 결과 받기
				int count = ps.executeUpdate();
				
				// 결과에 따른 처리 및 반환
				if(count == 1) {return true;}
			}catch(SQLException e) {System.out.println(e);}
			return false;
			
		} // f mSignUp end

    
		// 2. 일반 회원 SQL 처리 메소드
		public int mLogin( MemberDto memberDto ) {
			try {
				// [1] SQL 작성
				String sql = "select mno from member where mid = ? and mpwd = ?";
				// [2] DB와 연동된 곳에 SQL 기재
				PreparedStatement ps = conn.prepareStatement(sql);
				// [*] 기재된 SQL 에 매개변수 값 대입
				ps.setString(1, memberDto.getMid() );
				ps.setString(2, memberDto.getMpwd());
				// [3] 기재된 SQL 실행하고 결과 받기
				ResultSet rs = ps.executeQuery();
				// [4] 결과에 따른 처리 및 반환
				if( rs.next() ) {
					int mno = rs.getInt("mno");
					return mno;
				} // if end
			} // try end
			catch( SQLException e ) { System.out.println( e ); }
			return 0;
		} // f end
 	
    // [3] 회원 로그아웃 메소드
    public void mLogout() {
    	
    }
    
    
    
    // [1] 기업 회원가입 메소드
	public boolean eSignUp(EnterpriseDto enterpriseDto) {
		try {
			// SQL 작성
			
			String sql = "insert into enterprise(eid, epwd, ename, eaddr) values(?, ?, ?, ?)";

			// DB와 연동된 곳에 SQL 기재
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, enterpriseDto.getEid());
			ps.setString(2, enterpriseDto.getEpwd());
			ps.setString(3, enterpriseDto.getEname());
			ps.setString(4, enterpriseDto.getEaddr());
					
			// 기재된 SQL을 실행하고 결과 받기
			int count = ps.executeUpdate();
			
			// 결과에 따른 처리 및 반환
			if(count == 1) {return true;}
		}catch(SQLException e) {System.out.println(e);}
		return false;
	}
	
	// 2 기업 회원 SQL 처리 메소드
	public int eLogin( EnterpriseDto enterpriseDto ) {
		try {
			// [1] SQL 작성
			String sql = "select eno from enterprise where eid = ? and epwd = ?";
			// [2] DB와 연동된 곳에 SQL 기재
			PreparedStatement ps = conn.prepareStatement(sql);
			// [*] 기재된 SQL 에 매개변수 값 대입
			ps.setString(1, enterpriseDto.getEid() );
			ps.setString(2, enterpriseDto.getEpwd());
			// [3] 기재된 SQL 실행하고 결과 받기
			ResultSet rs = ps.executeQuery();
			// [4] 결과에 따른 처리 및 반환
			if( rs.next() ) {
				int eno = rs.getInt("eno");
				return eno;
			} // if end
		} // try end
		catch( SQLException e ) { System.out.println( e ); }
		return 0;
	} // f end
 	
 	
    // [3] 기업 로그아웃 메소드
    public void eLogout() {
       
    }
    
    
    // [1] 우수기업 R
    public ArrayList<HashMap<String, String>>  bestList() {
    	ArrayList<HashMap<String, String>> bList = new ArrayList<>();
    	try {
    		String sql = "select e.ename , avg(r.rrating) as ravg from review r left join post p on  r.pno = p.pno join enterprise e on p.eno = e.eno group by e.ename order by ravg desc";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			String list = rs.getString("ename");
    			String rating = rs.getString("ravg");
    			
    			HashMap<String, String> map = new HashMap<String, String>();
    			
    			map.put("기업명", list);
    			map.put("별점", rating);
    			
    			bList.add(map);
    		}
    	}catch (Exception e) {
    		System.out.println(e);
		}
    	
    	return bList;
    }
    // [2] 후기 R
    public ArrayList<HashMap<String, String>>  reviewList(String ename) {
    	ArrayList<HashMap<String, String>> rList = new ArrayList<>();
		try {
			String sql = "select e.ename,  r.rcontent, r.rrating from review r join post p on r.pno = p.pno  join enterprise e on p.eno = e.eno  where e.ename = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ename);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String name = rs.getString("ename");
				String rcontent = rs.getString("rcontent");
				String rrating = rs.getString("rrating");
				HashMap<String, String> map = new HashMap<String, String>();
				
				map.put("기업명", name);
				map.put("후기", rcontent);
				map.put("별점", rrating);
				
				rList.add(map);
			}
			
		}catch (SQLException e) {
			System.out.println(e);
		}
		
		return rList;
	}
}