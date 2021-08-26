package kr.co.ictedu;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Write1DAO {

private DataSource ds;
	
	// 메서드 결과에 따른 리턴값 상수로 표기
	private static final int ID_DELETE_SUCCESS = 1;
	private static final int ID_DELETE_FAIL = 0;
	
	private static final int ID_LOGIN_SUCCESS = 1;
	private static final int ID_LOGIN_FAIL = 0;
	
	private static final int ID_UPDATE_SUCCESS = 1;
	private static final int ID_UPDATE_FAIL = 0;
	
	


private Write1DAO() {
	// 일반 JDBC에서 활용하던 드라이버 설정코드
//	try {
//		Class.forName("com.mysql.cj.jdbc.Driver");
//	}catch(Exception e) {
//		e.printStackTrace();
//	}
	
	// 커넥션 풀에서 활용하는 드라이버 설정 코드
	// 역시 javax.nameing의 요소를 임포트합니다.
	try {
		Context ct = new InitialContext();
		ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
	} catch (Exception e) {
		e.printStackTrace();
	}
}

// 2. 외부에서 객체생성을 못 하기 때문에 자기 클래스 내부에서 그냥
// 스스로 자기 자신을 1개 생성합니다.
private static Write1DAO dao = new Write1DAO();


public static Write1DAO getInstance() {
	return dao;
}

public int joinWrite(Write1VO user) {
	// users_join.jsp애서 가져온 코드를 이곳에 붙여넣기를 합니다.
	// DB연동을 위한 Connector 설정
	// Connection 객체 생성
	Connection con = null;
	// 쿼리문 실행을 위한 preparedStatement 객체 생성
	PreparedStatement pstmt = null;
	
	
	try{
		
		// 접속 주소, 계정, 비밀번호를 이용해 접속계정
		// JDBC 기준 DB접속 코드
		//con = DriverManager.getConnection(URL, DBID, DBPW);
		
		// 커넥션 풀 기준 DB 접속 코드
		con = ds.getConnection();
		// 1. 쿼리문을 작성합니다.
		String sql = "INSERT INTO users VALUES(?, ?, ?, ?)";
		
		// 2. 만든 쿼리문의 ? 자리에 적용할 자바 변수를 집어넣습니다.
		pstmt = con.prepareStatement(sql);
		
		
		// 3. 만든 쿼리문 실행하기
		pstmt.executeUpdate();
		
		
	}catch(SQLException e){
		System.out.println("에러 : " + e);
	}finally{
		try{
			if(con!=null && !con.isClosed()){
				con.close();
			}// pstmt 닫기
			if(pstmt!=null && !pstmt.isClosed()) {
				pstmt.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	return 1;
} //end joinUser
}



