package kr.co.ictedu.board.model;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;



public class BoardDAO {
	
	
	// 싱글턴 패턴과 커넥션풀을 적용해서
	// DAO의 연결부(생성자, getInstance()) 까지 작성해주세요.
	private DataSource ds;
	
	private static final int WRITE_SUCCESS = 1;
	private static final int WRITE_FAIL = 0;
	private static final int DELETE_SUCCESS = 1;
	private static final int DELETE_FAIL = 0;
	private static final int UPDATE_SUCCESS = 1;
	private static final int UPDATE_FAIL = 0;
	
	
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
		// Connection, PreparedStatement 객체 선언
		Connection con = null;
		PreparedStatement pstmt = null;
		
		int resultCode;
		
		// 구문 작성
		// bId는 auto_increment가 붙어있으므로 입력 안해도 됨
		// bName, bTitle, bContent는 폼에서 날려준걸 넣음
		// bDate는 자동으로 현재 서버시간을 입력함
		// bHit는 자동으로 0을 입력함
		String sql = "INSERT INTO jspboard (bname, btitle, bcontent, bdate, bhit) "
				+ "VALUES(?, ?, ?, now(), 0)";
		try {
			// 커넥션 생성 및 pstmt에 쿼리문 넣고 완성시켜서 실행까지 하고
			// close()로 메모리회수까지 해주새요.
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
	
	// 모든 게시글의 정보를 DB로부터 얻어올 메서드
	public List<BoardVO> getBoardList(){
		// 내부에서 사용할 변수 선언
		
		List<BoardVO> boardList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// SQL문 작성
		String sql = "SELECT * FROM jspboard ORDER BY bId DESC";
		
		try {
			// 커넥션 연걸 후 DB에 쿼리 쏴주시고
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			// boardList에 DB내 모든 글을 저장해주세요.
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
	
	// 글 하네에 대한 상세 정보를 가져오는 로직
	public BoardVO getBoardDetail(String bId) {
		// bId에 해당하는 글 정보를 가져와서 리턴하도록 로직을 작성해주세요.
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
		// 사용할 변수들 선언
		Connection con= null;
		PreparedStatement pstmt = null;
		int resultCode;
		
		// 커넥션 연결 및 쿼리문 실행	
		String sql = "DELETE FROM jspboard WHERE bid=?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, bId);
			
			pstmt.executeUpdate();
			resultCode = DELETE_SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			resultCode = DELETE_FAIL;
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

	// 글 수정로직
	public int updateBoard(BoardVO board) {
		// 변수 선언
		Connection con = null;
		PreparedStatement pstmt = null;
		int resultCode;
		
		String sql = "UPDATE jspboard SET bName=?, bTitle=?, bContent=?, "
				+ "bDate=?, bHit=? WHERE bId=?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, board.getbName());
			pstmt.setString(2, board.getbTitle());
			pstmt.setString(3,  board.getbContent());
			pstmt.setTimestamp(4,  board.getbDate());
			pstmt.setInt(5,  board.getbHit());
			pstmt.setInt(6, board.getbId());
			
			pstmt.executeUpdate();
			resultCode = UPDATE_SUCCESS;
	}catch(Exception e) {
			e.printStackTrace();
			resultCode = UPDATE_FAIL;
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
		return resultCode; 
	}//end updateBoard
	
	
	// 글 조회수를 상승시키는 메서드
	public void upHit(String bId) {
		// 필요 변수들을 생성해주세요.
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		// 특정 글의 조회수를 1 올리는 쿼리문
		String sql = "UPDATE jspboard SET bHit = bHit + 1 WHERE bId=?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, bId);
			
			pstmt.executeUpdate();
			
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if( con != null && !con.isClosed()) {
					con.close();
				}if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	} // end upHit
	
	// 페이지 번호에 맏는 게시물 가져오기
	public List<BoardVO> getPageList(int pageNum){
		// 내부에서 사용할 변수 선언
		List<BoardVO> boardList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		// 쿼리문(SELECT구문, 역순, 10개씩 pageNum에 맞춰서);=써 서 안된겁니다. 리미트구문에 = 안들어가요
		String sql="SELECT * FROM jspboard ORDER BY bid DESC LIMIT ?,10";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, pageNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO board = new BoardVO();
				
				board.setbId(rs.getInt("bId"));
				board.setbName(rs.getString("bName"));
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
				if(con != null && !con.isClosed()) {
					con.close();
				}
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if(rs != null && !rs.isClosed()) {
					rs.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return boardList;
		
	}// end getpageList
	public List<BoardVO> getBoardCount(int Count){
		List<BoardVO> boardCount = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		String sql="SELECT count(*) FROM jspboard";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(con != null && !con.isClosed()) {
					con.close();
				}
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return boardCount; 
		
	}
}	

		
		
		
	
	

