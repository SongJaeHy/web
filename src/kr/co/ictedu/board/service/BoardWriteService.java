package kr.co.ictedu.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ictedu.board.model.BoardDAO;
import kr.co.ictedu.board.model.BoardVO;

// ����Ŭ�����̱� ������ implements�� �մϴ�
public class BoardWriteService implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// getParameter�� ������ �� �����͸� ������ �� �ּ���.
		try{request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String bWriter = request.getParameter("writer");
		String bTitle = request.getParameter("title");
		String bContent = request.getParameter("content");
		// dao����
		BoardDAO dao = BoardDAO.getInstance();
		// VO����
		BoardVO board = new BoardVO();
		board.setbName(bWriter);
		board.setbTitle(bTitle);
		board.setbContent(bContent);
		int resultCode = dao.write(board);
		if(resultCode ==1) {
			System.out.println("DB�� ���� �� �ԷµǾ����ϴ�.");
		}else if(resultCode ==0) {
			System.out.println("���� �߻����� ���� �Էµ��� �ʾҽ��ϴ�.");
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
}
}

