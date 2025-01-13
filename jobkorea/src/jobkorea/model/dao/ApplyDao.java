package jobkorea.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import jobkorea.model.dto.ApplyDto;
import jobkorea.model.dto.MemberDto;

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

	
	// [1-1] 입력값에 해당하는 카테고리 출력
	public ArrayList<HashMap<String, String>> pList(int choose) {
		ArrayList<HashMap<String, String>> pList = new ArrayList<>();
		
		try {
			String sql = "select p.pno , p.ptitle , p.pcontent ,p.phistory , p.pcount , p.psalary , p.pstart , p.pend , c.cname "
					   + "from post p join category c on p.cno = c.cno where p.cno = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, choose);
			
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
				
				HashMap<String, String> map = new HashMap<String, String>();
				
				map.put("공고번호", pno + "" );
				map.put("제목", ptitle );
				map.put("내용", pcontent );
				map.put("경력", phistory );
				map.put("인원", pcount );
				map.put("연봉", psalary);
				map.put("공고시작일",pstart );
				map.put("공고종료일", pend);
				map.put("카테고리명", cname);
			
				pList.add(map);
			}
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
		return pList;
	}
	
	// [1-2] 지원
	public boolean applyC(int choose , int loginMno) {
		
		try {
			String sql = "insert into apply(pno, mno) values (?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, choose);
			ps.setInt(2, loginMno);
			
			int count = ps.executeUpdate();
			if(count == 1) {
				return true;
			}
		}catch (Exception e) {	
			System.out.println(e);
		}
		
		return false;
	}
	
	// [2] 지원 현황 출력
	public ArrayList<HashMap<String, String>> applyR(int loginNo) {
		ArrayList<HashMap<String, String>> aList = new ArrayList<>();
		
		try {
			String sql = "select a.ano, p.ptitle , p.pend , a.apass , a.ano from apply a join post p on a.pno = p.pno  where a.mno = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, loginNo);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int ano = rs.getInt("ano");
				String ptitle = rs.getString("ptitle");
				String pend = rs.getString("pend");
				boolean apass = rs.getBoolean("apass");
				
				HashMap<String, String> map = new HashMap<String, String>();
				
				map.put("지원번호", ano + "");
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
	public boolean applyU(MemberDto memberDto, int loginMno) {
		try {
			String sql = "update member "
					+ "set mpwd = ? , mname = ?, mgender = ? , mdate = ? , maddr = ? where mno = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, memberDto.getMpwd());
			ps.setString(2, memberDto.getMname());
			ps.setBoolean(3, memberDto.isMgender());
			ps.setString(4, memberDto.getMdate());
			ps.setString(5, memberDto.getMaddr());
			ps.setInt(6, loginMno);
			
			int count = ps.executeUpdate();
			
			if(count == 1) {
				return true;
			}
		}catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	// [4] 지원 삭제 
	public boolean applyD(int choose) {
		try {
			String sql = "delete from apply where ano = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, choose);
			
			int count = ps.executeUpdate();
			
			if(count == 1) {
				return true;
			}
		}catch(SQLException e){
			System.out.println(e);
		}
		
		return false;
	}
	
	
	
	
	
	
	
	
		
	
}
