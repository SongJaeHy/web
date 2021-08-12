package kr.co.ictedu.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ictedu.user.model.UsersDAO;
import kr.co.ictedu.user.model.UsersVO;

public class UserJoinService implements IUserService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String uId = request.getParameter("uid");
		String uPw = request.getParameter("upw");
		String uName = request.getParameter("uname");
		String uEmail = request.getParameter("uemail");
		
		UsersVO user = new UsersVO(uId, uPw, uName, uEmail);
	
		UsersDAO dao = UsersDAO.getInstance();
		dao.joinUsers(user);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
