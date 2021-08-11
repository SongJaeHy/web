package kr.co.ictedu.board.model;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;



public class BoardDAO {
	
	
	// �̱��� ���ϰ� Ŀ�ؼ�Ǯ�� �����ؼ�
	// DAO�� �����(������, getInstance()) ���� �ۼ����ּ���.
	private DataSource ds;
	
	private static final int WRITE_SUCCESS = 1;
	private static final int WRITE_FAIL = 0;
	
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
		if(dao == null) {
			dao = new BoardDAO();
		}
		return dao;
	}
	
	
	public int write(BoardVO board) {
		// Connection, PreparedStatement ��ü ����
		Connection con = null;
		PreparedStatement pstmt = null;
		
		int resultCode;
		
		// ���� �ۼ�
		// bId�� auto_increment�� �پ������Ƿ� �Է� ���ص� ��
		// bName, bTitle, bContent�� ������ �����ذ� ����
		// bDate�� �ڵ����� ���� �����ð��� �Է���
		// bHit�� �ڵ����� 0�� �Է���
		String sql = "INSERT INTO jspboard (bname, btitle, bcontent, bdate, bhit) "
				+ "VALUES(?, ?, ?, now(), 0)";
		try {
			// Ŀ�ؼ� ���� �� pstmt�� ������ �ְ� �ϼ����Ѽ� ������� �ϰ�
			// close()�� �޸�ȸ������ ���ֻ���.
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getbName());
			pstmt.setString(2, board.getbTitle());
			pstmt.setString(3, board.getbContent());
			
			
			pstmt.executeUpdate();
			resultCode = WRITE_SUCCESS;
		}catch(Exception e) {
			e.printStackTrace();
			resultCode = WRITE_FAIL;
		}finally {
			try {
				if(con!=null && !con.isClosed()) {
					con.close();
				}
				if(pstmt!=null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return resultCode;
	} // write end
	
	// ��� �Խñ��� ������ DB�κ��� ���� �޼���
	public List<BoardVO> getBoardList(){
		// ���ο��� ����� ���� ����
		
		List<BoardVO> boardList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// SQL�� �ۼ�
		String sql = "SELECT * FROM jspboard ORDER BY bId DESC";
		
		try {
			// Ŀ�ؼ� ���� �� DB�� ���� ���ֽð�
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			// boardList�� DB�� ��� ���� �������ּ���.
			while(rs.next()) {
				BoardVO board = new BoardVO();
				
				board.setbId(rs.getInt("bid"));
				board.setbName(rs.getString("bname"));
				board.setbTitle(rs.getString("btitle"));
				board.setbContent(rs.getString("bcontent"));
				board.setbDate(rs.getTimestamp("bdate"));
				board.setbHit(rs.getInt("bhit"));
				
				boardList.add(board);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null && !con.isClosed()) {
					con.close();
				}if(pstmt != null&&!pstmt.isClosed()) {
					pstmt.close();
				}if(rs != null&&!rs.isClosed()) {
					rs.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return boardList;
	}// end getBoardList
	
	// �� �ϳ׿� ���� �� ������ �������� ����
	public BoardVO getBoardDetail(String bId) {
		// bId�� �ش��ϴ� �� ������ �����ͼ� �����ϵ��� ������ �ۼ����ּ���.
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO board = new BoardVO();
		
		String sql = "SELECT * FROM jspboard WHERE bid=?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, bId);
			rs = pstmt.executeQuery();
		
		if(rs.next()) {
			board.setbId(rs.getInt("bId"));
			board.setbName(rs.getString("bname"));
			board.setbTitle(rs.getString("btitle"));
			board.setbContent(rs.getString("bcontent"));
			board.setbDate(rs.getTimestamp("bdate"));
			board.setbHit(rs.getInt("bhit"));
		};
		
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(con!=null && !con.isClosed()) {
				con.close();
			}
			if(pstmt!=null && !pstmt.isClosed()) {
				pstmt.close();
			}
			if(rs!=null && !rs.isClosed()) {
				rs.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
		return board;
		
	}// end getBoardDetail
	public int deleteBoard(String bId) {
		// ����� ������ ����
		Connection con= null;
		PreparedStatement pstmt = null;
		int resultCode;
		
		// Ŀ�ؼ� ���� �� ������ ����	
		String sql = "DELETE FROM jspboard WHERE bid=?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, bId);
			
			pstmt.executeUpdate();
			resultCode = 1;
		}catch(Exception e){
			e.printStackTrace();
			resultCode = 0;
		}finally {
			try {
				if(con != null && !con.isClosed()) {
					con.close();
				}if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return resultCode;
	} //end deleteBoard

	// ��������
	public int updateBoard(BoardVO board) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE jspboard SET bname=? btitle=? bcontent=? WHERE bid=?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, board.getbName());
			pstmt.setString(2, board.getbTitle());
			pstmt.setString(3,  board.getbContent());
			pstmt.setInt(4, board.getbId());
			
			pstmt.executeUpdate();
		
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(con !=null && !con.isClosed()) {
				con.close();
			}if(pstmt != null&& !pstmt.isClosed()) {
				con.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
		return 0; //end updateBoard
	}
}	

		
		
		
	
	

