package kr.co.ictedu.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ictedu.board.model.BoardDAO;

public class BoardDetailService implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// url�� ����� �� ��ȣ�� getParameter�� �̿��� ����ϴ�.
		String bId = request.getParameter("bId");
		// DAO�� �����մϴ�.
		BoardDAO dao = BoardDAO.getInstance();
		
		// DAO�� �� ��ȣ�� �Ѱܼ� detail������ �����͸� ���ɴϴ�.
		
		// �������� ���� setAttribute()�� �����͸� �Ǿ��ݴϴ�.
		
	}

}
