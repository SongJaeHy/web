package kr.co.ictedu;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PatternServlet2
 */
@WebServlet("*.hello")
public class PatternServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatternServlet2() {
        super();
        System.out.println("확장자 패턴 생성");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("확장자 패턴 시작");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("확장자 패턴 소멸");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();	
		System.out.println("uri 패턴:" + uri);
		
		if(uri.equals("/MyFirstWeb/write.do")) {
			System.out.println("글쓰기 창으로 이동합니다.");
		}else if(uri.equals("/MyFirstWeb/update.do")) {
			System.out.println("글 수정 창으로 이동합니다.");
		}else if(uri.equals("/MyFirstWeb/delete.do")) {
			System.out.println("글 삭제 창으로 이동합니다.");
		}else if(uri.equals("/MyFirstWeb/select.do")) {
			System.out.println("글 조회 창으로 이동합니다.");
		}else {
			System.out.println("잘못된 패턴입니다. Funkyou");
		}
		// PatternServlet2의 패턴을 .do로 고쳐서 여기 옮겨주세요.
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
