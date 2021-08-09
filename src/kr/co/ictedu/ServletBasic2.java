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
        System.out.println("���� ����");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("���� ����");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("���� �Ҹ�");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("post������θ� ������ �� �ֽ��ϴ�.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet, doPost �ø���� �ش� �޼��� ���ο���
		// �Ķ���ͷ� ������ request, response ���尴ü�� �⺻����
		// �� �� �ְ�, �߰��� �����̳� ��Ű���� �����ؼ� �� ���� �ֽ��ϴ�.
		
		// ������ �C���� ������ UTF-8�� ���ڵ�
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// request.getPaeameter�� �̿��� ������ ���� ������ �ޱ�
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		// ������ ������ ������ �ֿܼ� ���
		System.out.println("�̸� : " + name);
		System.out.println("��� : " + password);
		System.out.println("funk");
	}

}
