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
		
		// ������ ���� ������ �޾��ּ���
		
		String uId = request.getParameter("uId");
		String uPw = request.getParameter("uPw");
		// ���� ���� ��
		HttpSession session = null;
		session = request.getSession();
		// VO ���� �� ������ �Է�
		UsersVO user = new UsersVO();
		user.setUid(uId);
		user.setUpw(uPw);
		
		// �װ� �־ daoȣ�����ּ���
		UsersDAO dao = UsersDAO.getInstance();
		
		int resultCode = dao.userLogin(user);
		
		if(resultCode == 1) {
			session.setAttribute("i_s", uId);
			session.setAttribute("p_s", uPw);
			session.setAttribute("login", "success");
		}else if(resultCode==0) {
			session.setAttribute("login", "fail");
		}
		// ����� ���ǹ߱��� ���ּ���.(���� �ּ��� �޾��ּ���)
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
