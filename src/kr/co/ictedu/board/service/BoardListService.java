package kr.co.ictedu.board.service;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ictedu.board.model.BoardDAO;
import kr.co.ictedu.board.model.BoardVO;

public class BoardListService implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = null;
		session = request.getSession();
		String idSession = (String) session.getAttribute("i_s");

		if (idSession == null) {
			try {
				// ���� ���ο��� �������� ��Ű��
				// �����̷�Ʈ�� �ƴϱ� ������ �����
				String ui = "/users/user_login_form.jsp";
				RequestDispatcher dp = request.getRequestDispatcher(ui);
				dp.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

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
}
