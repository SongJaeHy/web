package kr.co.ictedu.board.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ictedu.board.model.BoardDAO;
import kr.co.ictedu.board.model.BoardVO;

public class BoardDetailService implements IBoardService {

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
			// url�� ����� �� ��ȣ�� getParameter�� �̿��� ����ϴ�.
			String bId = request.getParameter("bId");
			// DAO�� �����մϴ�.
			BoardDAO dao = BoardDAO.getInstance();

			// ���� ��ȸ���� �ø��� �����;� �ֽ� ��ȸ���� ǥ���� �� ����.
			dao.upHit(bId);

			// DAO�� �� ��ȣ�� �Ѱܼ� detail������ �����͸� ���ɴϴ�.
			BoardVO board = dao.getBoardDetail(bId);

			// �������� ���� setAttribute()�� �����͸� �Ǿ��ݴϴ�.
			request.setAttribute("board", board);

		}
	}
}
