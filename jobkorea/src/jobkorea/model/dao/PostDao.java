package jobkorea.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
		System.out.println(">> DB 연동 성공");
		}catch (Exception e) {
		System.out.println(">> DB 연동 실패 "+ e);
		} // catch end
	} // f end
	public static PostDao getInstance() { return instance; }
	// - 싱글톤
	
	// 공고등록 SQL 처리 메소드
	public boolean pRegister( PostDto postDto ) {
		try {
			// SQL 작성
			String sql = "insert into post( cno , ptitle , pcontent , phistory , pcount , psalary , pend )"
					+ "values( '"+postDto.getCno()+"' , '"+postDto.getPtitle()+"' , '"+postDto.getPcontent()+"' , '"+postDto.getPhistory()+"' , '"+postDto.getPcount()+"' , '"+postDto.getPsalary()+"' , '"+postDto.getPend()+"' )";
			// DB와 연동된 곳에 SQL기재
			PreparedStatement ps = conn.prepareStatement(sql);
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
			String sql = "update post set ptitle ='?' , pcontent ='?' ,phistory ='?', pcount ='?' , psalary ='?' , pend ='?' where pno = ?";
			// DB와 연동된 곳에 SQL 기재
			PreparedStatement ps =  conn.prepareStatement(sql);
			// 기재된 SQL 에 매개 변수 값 대입
			ps.setString( 1 , postDto.getPtitle() );
			ps.setString( 2 , postDto.getPcontent() );
			ps.setString( 3 , postDto.getPcount() );
			ps.setString( 4 , postDto.getPsalary() );
			ps.setString( 5 , postDto.getPend() );
			ps.setInt( 6 , postDto.getPno() );
			// 기재된 SQL를 실행하고 결과 받기
			int count =  ps.executeUpdate();
			// 결과에 따른 처리 및 반환을 한다.
			if( count == 1 ) { return true; }
		}catch( SQLException e ) { System.out.println( e ); }
		return false;	
	} // f end
	
} // c end
