package kr.co.ictedu.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import kr.co.ictedu.board.model.BoardDAO;
import kr.co.ictedu.board.model.BoardVO;

public class BoardPagingService implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// page �Ķ���Ϳ� �ִ� ���� �����ɴϴ�.
		// hint : ?page=��������ȣ
		// page�Ķ���Ͱ� ���ٸ� strPage�� null�� �����
		String strPage = request.getParameter("page");
		// null�� ����� ��Ȳ���� int�� �ٲ��� �� ����
		// �Ķ���Ͱ� ������ �� �⺻ �������� 1��������
		int page = 1;
		if(strPage != null) {
			page = Integer.parseInt(strPage);
		// DAO����
		}
		BoardDAO dao = BoardDAO.getInstance();
		
		// ���� ���� �ִ� �������� ��ü �� ��������
		// �������� �׳� �ѱ��� �ʰ�, ���۹�ȣ�� ����ؼ� �ѱ�
		List<BoardVO> boardList=dao.getPageList((page - 1) * 10);
		System.out.println("����¡ �۵� : " + boardList);
		// �������ϱ����� �����ϱ�
		request.setAttribute("boardList", boardList);
		
	}
}
