package kr.co.ictedu;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Write1DAO {

private DataSource ds;
	
	// �޼��� ����� ���� ���ϰ� ����� ǥ��
	private static final int ID_DELETE_SUCCESS = 1;
	private static final int ID_DELETE_FAIL = 0;
	
	private static final int ID_LOGIN_SUCCESS = 1;
	private static final int ID_LOGIN_FAIL = 0;
	
	private static final int ID_UPDATE_SUCCESS = 1;
	private static final int ID_UPDATE_FAIL = 0;
	
	


private Write1DAO() {
	// �Ϲ� JDBC���� Ȱ���ϴ� ����̹� �����ڵ�
//	try {
//		Class.forName("com.mysql.cj.jdbc.Driver");
//	}catch(Exception e) {
//		e.printStackTrace();
//	}
	
	// Ŀ�ؼ� Ǯ���� Ȱ���ϴ� ����̹� ���� �ڵ�
	// ���� javax.nameing�� ��Ҹ� ����Ʈ�մϴ�.
	try {
		Context ct = new InitialContext();
		ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
	} catch (Exception e) {
		e.printStackTrace();
	}
}

// 2. �ܺο��� ��ü������ �� �ϱ� ������ �ڱ� Ŭ���� ���ο��� �׳�
// ������ �ڱ� �ڽ��� 1�� �����մϴ�.
private static Write1DAO dao = new Write1DAO();


public static Write1DAO getInstance() {
	return dao;
}

public int joinWrite(Write1VO user) {
	// users_join.jsp�ּ� ������ �ڵ带 �̰��� �ٿ��ֱ⸦ �մϴ�.
	// DB������ ���� Connector ����
	// Connection ��ü ����
	Connection con = null;
	// ������ ������ ���� preparedStatement ��ü ����
	PreparedStatement pstmt = null;
	
	
	try{
		
		// ���� �ּ�, ����, ��й�ȣ�� �̿��� ���Ӱ���
		// JDBC ���� DB���� �ڵ�
		//con = DriverManager.getConnection(URL, DBID, DBPW);
		
		// Ŀ�ؼ� Ǯ ���� DB ���� �ڵ�
		con = ds.getConnection();
		// 1. �������� �ۼ��մϴ�.
		String sql = "INSERT INTO users VALUES(?, ?, ?, ?)";
		
		// 2. ���� �������� ? �ڸ��� ������ �ڹ� ������ ����ֽ��ϴ�.
		pstmt = con.prepareStatement(sql);
		
		
		// 3. ���� ������ �����ϱ�
		pstmt.executeUpdate();
		
		
	}catch(SQLException e){
		System.out.println("���� : " + e);
	}finally{
		try{
			if(con!=null && !con.isClosed()){
				con.close();
			}// pstmt �ݱ�
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



