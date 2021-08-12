package kr.co.ictedu.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ictedu.user.model.UsersDAO;
import kr.co.ictedu.user.model.UsersVO;

public class UserLoginService implements IUserService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		// 폼에서 날린 데이터 받아주세요
		
		String uId = request.getParameter("uId");
		String uPw = request.getParameter("uPw");
		// 세션 쓰는 법
		HttpSession session = null;
		session = request.getSession();
		// VO 생성 및 데이터 입력
		UsersVO user = new UsersVO();
		user.setUid(uId);
		user.setUpw(uPw);
		
		// 그거 넣어서 dao호출해주세요
		UsersDAO dao = UsersDAO.getInstance();
		
		int resultCode = dao.userLogin(user);
		
		if(resultCode == 1) {
			session.setAttribute("i_s", uId);
			session.setAttribute("p_s", uPw);
			session.setAttribute("login", "success");
		}else if(resultCode==0) {
			session.setAttribute("login", "fail");
		}
		// 통과시 세션발급을 해주세요.(지금 주석만 달아주세요)
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
