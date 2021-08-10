package kr.co.ictedu.board.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ictedu.board.model.BoardDAO;
import kr.co.ictedu.board.model.BoardVO;

public class BoardListService implements IBoardService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// �������ڸ��� �ٷ� ��ü �����͸� ������ ��
		// �ٷ� DAO���� ������.
		BoardDAO dao = BoardDAO.getInstance();
		// ��ü ����Ʈ ��������
		List<BoardVO> boardList = dao.getBoardList();
		
		// �޾ƿ� ����Ʈ�� .jsp�� �����ϱ�.
		// request�� �����͸� �Ǿ���� �մϴ�.
		// request.setAttribute("��Ī", ������);
		request.setAttribute("boardList", boardList);
		
	}
	
}
