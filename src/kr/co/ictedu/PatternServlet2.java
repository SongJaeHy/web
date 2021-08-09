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
        System.out.println("Ȯ���� ���� ����");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Ȯ���� ���� ����");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("Ȯ���� ���� �Ҹ�");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();	
		System.out.println("uri ����:" + uri);
		
		if(uri.equals("/MyFirstWeb/write.do")) {
			System.out.println("�۾��� â���� �̵��մϴ�.");
		}else if(uri.equals("/MyFirstWeb/update.do")) {
			System.out.println("�� ���� â���� �̵��մϴ�.");
		}else if(uri.equals("/MyFirstWeb/delete.do")) {
			System.out.println("�� ���� â���� �̵��մϴ�.");
		}else if(uri.equals("/MyFirstWeb/select.do")) {
			System.out.println("�� ��ȸ â���� �̵��մϴ�.");
		}else {
			System.out.println("�߸��� �����Դϴ�. Funkyou");
		}
		// PatternServlet2�� ������ .do�� ���ļ� ���� �Ű��ּ���.
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
