package kr.co.ictedu.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ictedu.board.model.BoardDAO;

public class BoardDeleteService implements IBoardService {
	
	// execute �޼��� �������̵�
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// bId �Ķ���� �ޱ�(������ ���������� post������� ������) �ޱ�
		String bId = request.getParameter("bId");
		// DAO����
		BoardDAO dao = BoardDAO.getInstance();
		// delete������ bId �־ ����
		dao.deleteBoard(bId);
	}

}
