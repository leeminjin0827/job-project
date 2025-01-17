package jobkorea.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jobkorea.model.dto.EnterpriseDto;
import jobkorea.model.dto.PostDto;

public class PostDao {

	private Connection conn;
	   private String dburl = "jdbc:mysql://localhost:3306/jobkorea";
	   private String dbuser = "root";
	   private String dbpwd = "1234";
	   
	// + 싱글톤
	private static PostDao instance = new PostDao();
	private PostDao() {
		// 예외처리
		try {
		// 1) JDBC 클래스 드라이버 로드 : .Class.forName()
		Class.forName("com.mysql.cj.jdbc.Driver");
		// 2) 설정한 경로 / 계정 / 비밀번호로 DB 서버 연동 시도 후 결과(구현체) 반환  
		conn = DriverManager.getConnection(dburl,dbuser,dbpwd);
		
		}catch (Exception e) {
		System.out.println(">> DB 연동 실패 "+ e);
		} // catch end
	} // f end
	public static PostDao getInstance() { return instance; }
	// - 싱글톤
	
	// 공고등록 SQL 처리 메소드
	public boolean pRegister( PostDto postDto , int loginEno ) { // join / select
		try {
			// SQL 작성
			String sql = "insert into post( cno , ptitle , pcontent , phistory , pcount , psalary , pend , eno )"
					+ "values(?,?,?,?,?,?,?,?)"; 
			// DB와 연동된 곳에 SQL기재
			PreparedStatement ps = conn.prepareStatement(sql);
			// 기재된 SQL 에 매개 변수 값 대입
			ps.setInt( 1, postDto.getCno() );
			ps.setString( 2, postDto.getPtitle() );
			ps.setString( 3, postDto.getPcontent() );
			ps.setString( 4, postDto.getPhistory() );
			ps.setString( 5, postDto.getPcount() );
			ps.setString( 6, postDto.getPsalary() );
			ps.setString( 7, postDto.getPend() );
			ps.setInt( 8 , loginEno );
			// 기재된 SQL를 실행하고 결과 받기
			int count = ps.executeUpdate();
			// 결과에 따른 처리 및 반환을 한다.
			if( count == 1) { return true; }
		}catch( SQLException e ) { System.out.println( e ); }
		return false;
	} // f end
	
	// 공고수정 SQL 처리 메소드
	public boolean pUpdate( PostDto postDto ) { 
		try {
			// SQL 작성
			String sql = "update post set ptitle = ? , pcontent = ? ,phistory = ? , pcount = ? , psalary = ? , pend = ? where pno = ?";
			// DB와 연동된 곳에 SQL 기재
			PreparedStatement ps =  conn.prepareStatement(sql);
			// 기재된 SQL 에 매개 변수 값 대입
			ps.setString( 1 , postDto.getPtitle() );
			ps.setString( 2 , postDto.getPcontent() );
			ps.setString( 3 , postDto.getPhistory() );
			ps.setString( 4 , postDto.getPcount() );
			ps.setString( 5 , postDto.getPsalary() );
			ps.setString( 6 , postDto.getPend() );
			ps.setInt( 7 , postDto.getPno() );
			// 기재된 SQL를 실행하고 결과 받기
			int count =  ps.executeUpdate();
			// 결과에 따른 처리 및 반환을 한다.
			if( count == 1 ) { return true; }
		}catch( SQLException e ) { System.out.println( e ); }
		return false;	
	} // f end
	
	// 공고삭제 SQL 처리 메소드
	public boolean pDelete( int pno ) {
		try {
			// SQL 작성
			String sql = "delete from post where pno = ?";
			// DB와 연동된 곳에 SQL 기재
			PreparedStatement ps = conn.prepareStatement(sql);
			// 기재된 SQL 에 매개 변수 값 대입
			ps.setInt( 1 , pno );
			// 기재된 SQL를 실행하고 결과 받기
			int count = ps.executeUpdate();
			if( count == 1 ) { return true; }
		}catch( SQLException e ) { System.out.println( e ); }
		return false;
	} // f end
	
	// 지원관리 SQL 처리 메소드
	public boolean pCheck( int ano ) {
		try {
			String sql = "update apply set apass = not apass where ano = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ano);
			int count = ps.executeUpdate();
			if( count == 1 ) { return true; }
		}catch( SQLException e ) { System.out.println( e ); }
		return false;
		
	}
	
	// 내정보보기 SQL 처리 메소드
	public EnterpriseDto pMyinfo( int loginEno ) {
		try {
			String sql = "select * from enterprise where eno = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, loginEno);
			ResultSet rs = ps.executeQuery();
			if( rs.next() ) {
				EnterpriseDto enterpriseDto = new EnterpriseDto();
				enterpriseDto.setEid(rs.getString("eid") );
				enterpriseDto.setEname(rs.getString("ename") );
				enterpriseDto.setEaddr(rs.getString("eaddr"));
				return enterpriseDto;
			}
		}catch( SQLException e ) { System.out.println( e ); }
		return null;
	}
	
	
	
} // c end
