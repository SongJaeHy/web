package kr.co.ictedu.board.service;

import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ictedu.board.model.BoardDAO;
import kr.co.ictedu.board.model.BoardVO;

// IBoardService �����ϱ�
public class BoardUpdateService implements IBoardService{

	// �������̵��� �޼��� ���� �ۼ��ϱ�
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. �Ķ���� 6�� �޾ƿ���
		String strbId=request.getParameter("bId");
		int bId = Integer.parseInt(strbId);
		String bName=request.getParameter("bName");
		String bTitle=request.getParameter("title");
		String bContent=request.getParameter("content");
		// strŸ������ ���� Data�� Timestamp�� �ٲٱ�
		String strbDate = request.getParameter("bDate");
		Timestamp bDate = Timestamp.valueOf(strbDate);
		
		String strbHit = request.getParameter("bHit");
		int bHit = Integer.parseInt(strbHit);
		
		// 2. VO �����ؼ� ���ͷ� �����ϱ�
		BoardVO board =  new BoardVO();
		board.setbId(bId);
		board.setbName(bName);
		board.setbTitle(bTitle);
		board.setbContent(bContent);
		board.setbDate(bDate);
		board.setbHit(bHit);
		// 3.DAO ���� �� update���� ȣ��(update������ �����ۼ����ּ���.)
		BoardDAO dao = BoardDAO.getInstance();
		dao.updateBoard(board);
	
	}
}
