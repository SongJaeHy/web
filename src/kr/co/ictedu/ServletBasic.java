package kr.co.ictedu;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletBasic
 */
// WebServlet("/�ּ�") �� �ּҷ� ���ӽ� ������ �۵���
@WebServlet("/apple")
// HttpServlet�� ��ӹ��� Ŭ���� ���ο����� Session, request, response����
// ��ü�� �ڹ� �ڵ� ������ ����� �� ����
public class ServletBasic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBasic() {
        super();
        System.out.println("���� ��ü ���� �Ϸ�!");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    // HttpServel ��ü�� ��ӹ��� Ŭ���� ����� �ؼ�
    // ���δ�� ���� ��� �޼��尡 �ּ� ���ӱ⸶�� ȣ������� �ʽ��ϴ�.
    // �Ʒ��� ���� ��ӹ��� �޼��带 �������̵� �ϴ� ��츸
    // ȣ���� �Ǵ� ���� �������ּ���.
	public void init(ServletConfig config) throws ServletException {
		// ��û�� �������� ó���� ������ ������ �ۼ��ϴ� �κ�
		// init()�޼���� ���� ��ü ������ 1�� �ڵ�ȣ��˴ϴ�.
		System.out.println("init�޼��� ȣ��!!");
		
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// �Ҹ��� �޼���, ���� ��ü�� �޸𸮿��� ������ �� ȣ��(�Ҹ�� 1ȸ��)
		System.out.println("destroy�޼��� ȣ��");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Http Get ��û�� ���������� �۵��ϴ� �޼���
		System.out.println("doGet �޼��� ȣ���!!!!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Http Post ��û�� ���������� �۵��ϴ� �޼���
		// .jsp���Ͽ� ���� �����, post ��û�� �ϵ��� ó���մϴ�.
		System.out.println("doPost �޼��� ȣ���!!!");
	}

}
