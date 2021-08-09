package kr.co.ictedu;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletBasic3
 */
@WebServlet("/mango")
public class ServletBasic2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBasic2() {
        super();
        System.out.println("망고 생성");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("망고 시작");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("망고 소멸");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("post방식으로만 접속할 수 있습니다.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet, doPost 시리즈는 해당 메서드 내부에서
		// 파라미터로 선언한 request, response 내장객체를 기본으로
		// 쓸 수 있고, 추가로 세션이나 쿠키들을 선언해서 쓸 수도 있습니다.
		
		// 폼에서 낧려준 데이터 UTF-8로 인코딩
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// request.getPaeameter를 이용해 폼에서 날린 데이터 받기
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		// 폼에서 날려준 데이터 콘솔에 찍기
		System.out.println("이름 : " + name);
		System.out.println("비번 : " + password);
		System.out.println("funk");
	}

}
