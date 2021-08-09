package kr.co.ictedu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PatternServlet
 */
// *.do�� ���� ������ .do�� ������ �����ּҸ� ��� ��ƿɴϴ�.
// Ȯ���� ������ /�� ���� �ۼ��մϴ�.
@WebServlet("*.do")
public class PatternServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatternServlet() {
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
		doRequest(request, response);
}
	



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doRequest(request, response);
	}
	
	
	// ���� ��û �޼���(get, post) ��� ���� ó���ϰ� ����� �ʹٸ�
	// �޼��� �ϳ��� �� ���� ��û�Ѵ�.
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doget�� �ִ� ��� �ڵ带 �����ɳ���.
		// Ȯ���� ���Ͽ��� Ȯ���ڸ� ������ �ּҰ��� �������� ���ؼ�
		// �Ʒ� �ڵ带 ����մϴ�.
		String uri = request.getRequestURI();	
		System.out.println("uri ����:" + uri);
		
		// �ܼ��� �ƴ� ����ڰ� Ȯ���� �� �ֵ��� .jsp ȭ�鿡
		// ������ ��� �ڷḦ ��� out.print(); ����� ����
		// ���� �غ�
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// jsp�������� html�������� �̷��� ������ �˷��ִ� �ڵ�
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		if(uri.equals("/MyFirstWeb/join.do")) {
			System.out.println("ȸ������ ��û Ȯ��");
		} else if(uri.equals("/MyFirstWeb/login.do")) {
			System.out.println("�α��� ��û Ȯ��");
		} else if(uri.equals("/MyFirstWeb/update.do")) {
			System.out.println("���� ��û Ȯ��");
		} else if(uri.equals("/MyFirstWeb/delete.do")) {
			System.out.println("Ż�� ��û Ȯ��");
		} 
		// PatternServlet2�� ������ .do�� ���ļ� ���� �Ű��ּ���.
		else if(uri.equals("/MyFirstWeb/write.do")) {
			System.out.println("�۾��� â���� �̵��մϴ�.");
		}else if(uri.equals("/MyFirstWeb/update.do")) {
			System.out.println("�� ���� â���� �̵��մϴ�.");
		}else if(uri.equals("/MyFirstWeb/delete.do")) {
			System.out.println("�� ���� â���� �̵��մϴ�.");
		}else if(uri.equals("/MyFirstWeb/select.do")) {
			System.out.println("�� ��ȸ â���� �̵��մϴ�.");
		}else {
			out.print("�߸��� �����Դϴ�. Funkyou");
		}
	}
	
	
}