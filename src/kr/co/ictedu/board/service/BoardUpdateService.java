package kr.co.ictedu.board.service;

import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ictedu.board.model.BoardDAO;
import kr.co.ictedu.board.model.BoardVO;

// IBoardService 구현하기
public class BoardUpdateService implements IBoardService{

	// 오버라이딩된 메서드 내부 작성하기
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. 파라미터 6개 받아오기
		String strbId=request.getParameter("bId");
		int bId = Integer.parseInt(strbId);
		String bName=request.getParameter("bName");
		String bTitle=request.getParameter("title");
		String bContent=request.getParameter("content");
		// str타입으로 들어온 Data를 Timestamp로 바꾸기
		String strbDate = request.getParameter("bDate");
		Timestamp bDate = Timestamp.valueOf(strbDate);
		
		String strbHit = request.getParameter("bHit");
		int bHit = Integer.parseInt(strbHit);
		
		// 2. VO 생성해서 세터로 저장하기
		BoardVO board =  new BoardVO();
		board.setbId(bId);
		board.setbName(bName);
		board.setbTitle(bTitle);
		board.setbContent(bContent);
		board.setbDate(bDate);
		board.setbHit(bHit);
		// 3.DAO 생성 및 update로직 호출(update로직은 직접작성해주세요.)
		BoardDAO dao = BoardDAO.getInstance();
		dao.updateBoard(board);
	
	}
}
