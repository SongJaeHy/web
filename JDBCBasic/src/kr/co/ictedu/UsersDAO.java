package kr.co.ictedu;

import java.sql.*;
import java.util.ArrayList;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//DAOŬ������ DB������ ������ ó���մϴ�. 
public class UsersDAO {
	
	// DB�ּ� ���̵� �н����� �̸� ����
	
	// �Ϲ� DAO Ȱ��� ����ϴ� �͵�
//	private static final String URL = "jdbc:mysql://localhost/ict03";
//	private static final String DBID = "root";
//	private static final String DBPW = "mysql";
	
	// Ŀ�ؼ� Ǯ ���� �� ����ϴ� ��
	// javax.sql�� DataSource�� ����Ʈ���ּ���.
	private DataSource ds;
	
	// �޼��� ����� ���� ���ϰ� ����� ǥ��
	private static final int ID_DELETE_SUCCESS = 1;
	private static final int ID_DELETE_FAIL = 0;
	
	private static final int ID_LOGIN_SUCCESS = 1;
	private static final int ID_LOGIN_FAIL = 0;
	
	private static final int ID_UPDATE_SUCCESS = 1;
	private static final int ID_UPDATE_FAIL = 0;
	
	
	
	/*
	 * DAO Ŭ������ �ϳ��� ��ü�����ε� DB������ ������ �� �ֱ� ������
	 * �޸� ���� �������� Ŭ������ ��ü�� �� 1���� �����ϵ���
	 * �̱��� ������ �����Ͽ� Ŭ������ �������մϴ�.
	 */
	
	// ��Ŭ�� ���� Ŭ���� ������ ���
	// 1. �ܺο��� ��ü�� new Ű����� ����� �� �� ������ �����ڿ�
	// private�� �ٿ��ݴϴ�.
	private UsersDAO() {
		// �Ϲ� JDBC���� Ȱ���ϴ� ����̹� �����ڵ�
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
		
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
	private static UsersDAO dao = new UsersDAO();
	
	// 3. �ܺο��� ��ü ������ �ʿ��� �� public �������� ó����
	// getter�� �̿��� 2���� ���� ��ü�� ���ϸ� ���ݴϴ�.
	// �̷��� UsersDAO�� ������ �����̱� ������ �ּҰ��� ���޵˴ϴ�.
	public static UsersDAO getInstance() {
		return dao;
	}
	
	
	// ȸ�������� ó���ϴ� �޼��� ����
	// DB�� ���� ��, Ȥ�� DB���� ��µǾ� ������ ������
	// ��ΰ� UsersVO ���� �ڷ������� ��� �� �����Ƿ�
	// ��ټ� ������� ����VO�� �Ű��� �����մϴ�.
	public int joinUsers(UsersVO user) {
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
			pstmt.setString(1, user.getUid());
			pstmt.setString(2, user.getUpw());
			pstmt.setString(3, user.getUname());
			pstmt.setString(4, user.getEmail());
			
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
	
	
	// usersDelete
	// ����
	// ���� ��ټ� DAO�� UsersVO �ϳ��� ��� ó���� �ذ��� �� �ֽ��ϴ�.
	// �ٸ� ���������� ������ ���� ��й�ȣ�� ���� DB�� ����Ǿ��ִ� ��й�ȣ��
	// ���ؾ� �ϱ� ������ ������ ���� ��й�ȣ�� �߰��� �Է¹޽��ϴ�.
	public int usersDelete(UsersVO user, String dpw) {
		Connection con = null;
		// ������ ������ ���� preparedStatement ��ü ����
		PreparedStatement pstmt = null;
		
		try{
			// UsersVO�� �Էµ� ��й�ȣ�� ������ ���� dpw�� ��
			if(user.getUpw().equals(dpw)) {
			// ���� �ּ�, ����, ��й�ȣ�� �̿��� ���Ӱ���
			//con = DriverManager.getConnection(URL, DBID, DBPW);
			
			con = ds.getConnection();
			// 1. DELETE �������� �ۼ��մϴ�.
			String sql = "DELETE FROM users WHERE uid=?";
			
			// 2. ���� �������� ? �ڸ��� ������ �ڹ� ������ ����ֽ��ϴ�.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUid());
			
			// 3. ���� ������ �����ϱ�
			pstmt.executeUpdate();
			
			// DAO���������� session, response �� ���� ��ü�� ����
			// ó���� �� �� �������, ��� ������ ����
				return ID_DELETE_SUCCESS;
			} else {
				// ��й�ȣ Ʋ���� �Է��� ����
				return ID_DELETE_FAIL;
			}
		} catch(SQLException e){
			System.out.println("���� : " + e);
		} finally {
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
		
		// ��� try�� ������ ������ ó���� �� �Ǿ ������� �ڵ尡
		// ���� �ߴٴ� ��ü�� �̹� ���� ������ �����Ǿ��ٴ� �̾߱��̹Ƿ�
		// 0�� ����
		return ID_DELETE_FAIL;
	} //end usersDelete
	
	// �α���
	public int userLogin(UsersVO user) {
		Connection con = null;
		// ������ ������ ���� preparedStatement ��ü ����
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		try{
			// ���� �ּ�, ����, ��й�ȣ�� �̿��� ���Ӱ���
			//con = DriverManager.getConnection(URL, DBID, DBPW);
			
			con = ds.getConnection();
			// 1. SELECT�������� �ۼ��մϴ�.
			String sql = "SELECT * FROM users WHERE uid=?";
					
			// 2. ���� �������� ? �ڸ��� ������ �ڹ� ������ ����ֽ��ϴ�.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUid());
				
			// 3. ���� ������ �����ϱ�
			rs = pstmt.executeQuery();
					
			if(rs.next()){
				String dbId = rs.getString("uid");
				String dbPw = rs.getString("upw");
				  		
				if(user.getUid().equals(dbId) && user.getUpw().equals(dbPw)){
					return ID_LOGIN_SUCCESS;
				 }else {
				  	return ID_LOGIN_FAIL;
				  }
			}else {
				return ID_LOGIN_FAIL;
			}
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
		return ID_LOGIN_FAIL;
	} //end userLogin
	
	// getUserInfo �޼���
	// ���� ������ ����ϱ� ���� ������ Ÿ�� ���̵��� ������ ������ ����
	// ����ϴ� �޼���� ���̵�, ��й�ȣ, �̸�, �̸�����, UsersVO�� �־
	// �����մϴ�.
	public UsersVO getUserInfo(UsersVO user) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// ����ִ� UsersVO�� ���� ����
		UsersVO resultData = new UsersVO();
		
		try{
			
			// ���� �ּ�, ����, ��й�ȣ�� �̿��� ���Ӱ���
			con = ds.getConnection();
			
			// 1. �������� �ۼ��մϴ�.
			String sql = "SELECT * FROM users WHERE uid=?";
			
			// 2. ���� �������� ? �ڸ��� ������ �ڹ� ������ ����ֽ��ϴ�.
			// DB���� �� �Է��� user�� getUid()�� �̿��� ��ȸ���� �ۼ�
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUid());
			
			
			// 3. ���� ������ �����ϱ�
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// ���� ResultSet�� ����ִ� �ڷḦ �ٽ� �� ������
				// ���� ������ UsersVO�� ������ �Է����� ��
				resultData.setUid(rs.getString("uid"));
				resultData.setUpw(rs.getString("upw"));
				resultData.setUname(rs.getString("uname"));
				resultData.setEmail(rs.getString("email"));
			}
			
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
		// ResultSet�� �ִ� �ڷḦ �Է¹��� UsersVO�� ����
		return resultData;
	} //end getUserInfo
	
	// �����ϴ� ����
	public int userUpdate(UsersVO user) {
		Connection con = null;
		// ������ ������ ���� preparedStatement ��ü ����
		PreparedStatement pstmt = null;
		
		
		try{
			
			// ���� �ּ�, ����, ��й�ȣ�� �̿��� ���Ӱ���
			con = ds.getConnection();
			
			// 1. �������� �ۼ��մϴ�.
			String sql = "UPDATE users SET upw=?, uname=?, email=? WHERE uid=?";
			
			// 2. ���� �������� ? �ڸ��� ������ �ڹ� ������ ����ֽ��ϴ�.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUpw());
			pstmt.setString(2, user.getUname());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getUid());
			
			// 3. ���� ������ �����ϱ�
			pstmt.executeUpdate();
			
			return ID_UPDATE_SUCCESS;
			
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
		return ID_UPDATE_FAIL;
	}// end userUpdate;
	
	// ��ü �����͸� �� �������� getAllUser()
	// �Ķ���ʹ� �ʿ� �����ϴ�.(���� ���� ��ü ���� ����� ������)
	// UserVO 1���� SELECT������ row �� ���� �ǹ��մϴ�.
	// ��ü �����ʹ� ȸ�� ���� ��Ȳ�� ���� �������̹Ƿ�
	// ���̸� ���س��� ������ ¥�� �� �˴ϴ�.
	// ���� ���̸� ���������� ������ �� �ִ� ArrayList�� UsersVO�� ����
	// ��ȸ ����� �� ���� �������� ������ �� �ֵ��� �մϴ�.
	public ArrayList<UsersVO> getAllUser() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// ����ִ� ArrayList<UsersVO>�� ���� ����
		ArrayList<UsersVO> userList = new ArrayList<>();
		
		// DB��������� ����־��ּ���.
		try{
			
			// ���� �ּ�, ����, ��й�ȣ�� �̿��� ���Ӱ���
			con = ds.getConnection();
			
			// 1. �������� �ۼ��մϴ�.
			String sql = "SELECT * FROM users";
			
			// 2. ���� �������� ? �ڸ��� ������ �ڹ� ������ ����ֽ��ϴ�.
			pstmt = con.prepareStatement(sql);

			// 3. ���� ������ �����ϱ�
			rs = pstmt.executeQuery();
			
			// row ������ŭ �ݺ��մϴ�.
			while(rs.next()) {// 1���� -> 2���� -> 3����
				// ArrayList�� �־��� �� UsersVO����
				UsersVO user = new UsersVO();
				// ResultSet�� �� �÷��� ���� �����ϴ�.
				String uid = rs.getString("uid");
				String upw = rs.getString("upw");
				String uname = rs.getString("uname");
				String email = rs.getString("email");
				// UsersVO�� setter�� �����մϴ�.
				user.setUid(uid);
				user.setUpw(upw);
				user.setUname(uname);
				user.setEmail(email);
				// ArrayList�� �� UsersVO�� �����մϴ�.
				userList.add(user);
			}
			
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
		// ���̺� �ִ� ��� �ڷḦ ������ �ִ� userList�� ����
		return userList;
	}// getAllUser end
}	
		
	
	
	


	

	
	

