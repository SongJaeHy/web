package kr.co.ictedu.board.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ictedu.board.model.BoardDAO;

public class BoardDeleteService implements IBoardService {

	// execute �޼��� �������̵�
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = null;
		session = request.getSession();
		String idSession = (String) session.getAttribute("i_s");

		if (idSession == null) {
			try {
				String ui = "/users/user_login_form.jsp";
				RequestDispatcher dp = request.getRequestDispatcher(ui);
				dp.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// bId �Ķ���� �ޱ�(������ ���������� post������� ������) �ޱ�
			String bId = request.getParameter("bId");

			// DAO����
			BoardDAO dao = BoardDAO.getInstance();
			// delete������ bId �־ ����
			dao.deleteBoard(bId);
		}
	}
}
