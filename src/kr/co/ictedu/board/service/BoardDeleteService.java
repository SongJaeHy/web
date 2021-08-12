package kr.co.ictedu.board.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ictedu.board.model.BoardDAO;

public class BoardDeleteService implements IBoardService {

	// execute 메서드 오버라이딩
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
			// bId 파라미터 받기(디테일 페이지에서 post방식으로 날려준) 받기
			String bId = request.getParameter("bId");

			// DAO생성
			BoardDAO dao = BoardDAO.getInstance();
			// delete로직에 bId 넣어서 실행
			dao.deleteBoard(bId);
		}
	}
}
