package kr.co.ictedu.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ictedu.board.model.BoardDAO;
import kr.co.ictedu.board.model.BoardVO;

public class BoardDetailService implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// url�� ����� �� ��ȣ�� getParameter�� �̿��� ����ϴ�.
		String bId = request.getParameter("bId");
		// DAO�� �����մϴ�.
		BoardDAO dao = BoardDAO.getInstance();
		
		// DAO�� �� ��ȣ�� �Ѱܼ� detail������ �����͸� ���ɴϴ�.
		BoardVO board = dao.getBoardDetail(bId);
		
		// �������� ���� setAttribute()�� �����͸� �Ǿ��ݴϴ�.
		request.setAttribute("board", board);
	}

}
