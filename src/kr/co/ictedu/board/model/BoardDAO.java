package kr.co.ictedu.board.model;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class BoardDAO {
	// �̱��� ���ϰ� Ŀ�ؼ�Ǯ�� �����ؼ�
	// DAO�� �����(������, getInstance()) ���� �ۼ����ּ���.
	private DataSource ds;
	private BoardDAO() {
		try {
			Context ct = new InitialContext();
			ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static BoardDAO dao = new BoardDAO();
	
	public static BoardDAO getInstance(){
		return dao;
	}
}