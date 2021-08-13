package kr.co.ictedu.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import kr.co.ictedu.board.model.BoardDAO;
import kr.co.ictedu.board.model.BoardVO;

public class BoardPagingService implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// page 파라미터에 있던 값을 가져옵니다.
		// hint : ?page=페이지번호
		// page파라미터가 없다면 strPage에 null이 저장됨
		String strPage = request.getParameter("page");
		// null이 저장된 상황에는 int로 바꿀줄 수 없음
		// 파라미터가 없을때 들어갈 기본 페이지느 1페이지임
		int page = 1;
		if(strPage != null) {
			page = Integer.parseInt(strPage);
		// DAO생성
		}
		BoardDAO dao = BoardDAO.getInstance();
		
		// 현재 보고 있는 페이지의 전체 글 가져오기
		// 페이지를 그냥 넘기지 않고, 시작번호를 계산해서 넘김
		List<BoardVO> boardList=dao.getPageList((page - 1) * 10);
		System.out.println("페이징 글들 : " + boardList);
		// 포워딩하기위헤 적재하기
		request.setAttribute("boardList", boardList);
		
	}
}
