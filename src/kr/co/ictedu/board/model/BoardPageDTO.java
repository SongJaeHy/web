package kr.co.ictedu.board.model;

import java.util.List;

// VO�� DTO�� ũ�� ������ ���� �ʴ� ����鵵 �ְ� ������ �Ѵڰ� �ص�
// �����ϰ� ������ �ʴ� ���Դϴ�.
// ���� ���� ������ �ؾ� �Ѵٸ�, VO�� DB���� �޾ƿ� �����͸� �״�� �����ϴ� �ʿ�
// ������ ������ �ִٸ�, DTO�� �޾ƿ� �����͸� Ư���ϰ� �����ؼ� �����ϴ� �ʿ�
// ������ ������ �ִٰ� ���ŵ� �����մϴ�.
public class BoardPageDTO {
	private int total; // ��ü �� ����
	private int currentPage; // ���� ���� �ִ� ������
	private List<BoardVO> boardList; // ����¡�� �� ���
	private int totalPages; // ��ü ������ ����
	private int startPage; // ���� ������ ��ȣ
	private int endPage; // �� ������ ��ȣ
	
	
	// �����ڴ� ��ü �� ����, ���� ������, ����¡ �� �� ����� �޾Ƽ� ������
	// �ϸ��� ������ �����ϴ�.
	public BoardPageDTO(int total, 
		int currentPage,List<BoardVO> boardList) {
		this.total = total;
		this.currentPage = currentPage;
		this.boardList = boardList;
		
		// �Ʒ����� �� 3���� ���� �����ؼ� ������ ������ ä��ϴ�.
		// ���� ���� ��� ������ �� ��ư ��ü�� �ʿ����
		if(total == 0) {
			this.totalPages = 0;
			this.startPage = 0;
			this.endPage = 0;
		} else {
			// �Խñ� �� ������ �̿��� ��ü ������ �������� ���ϱ�
			this.totalPages = total/10;
			if(this.total % 10 >0) {
				// ���� �������� 10���� ���������� ������
				// �������� �������� �ϳ� �� �߰��ؾ� ��
				this.totalPages += 1;
			}
			
			// �h�� ���� �ִ� ������ �׷��� ���� ��ȣ ���ϱ�
			int modVal = this.currentPage % 10;
			this.startPage = this.currentPage / 10 * 10 + 1;
			
			if(modVal == 0) {
				this.startPage -= 10;
			}
			
			// �ش� ������ �׷��� �� ��ȣ ���ϱ�
			endPage = startPage + (10 -1);
			// �� ������ ���� ������� ������ ��ȣ��
			// totalPages�� �ʰ��ϴ� ����
			// totalPages�� ��� �����Ѵ�.
			if(endPage > totalPages) {
				endPage = totalPages;
			}
			
		}
	}// end constructor
	
	public int getTotal() {
		return total; // �� �� ����
	}
	
	public boolean hasNoBoard() {
		return total ==0; // �Խù� ǥ�Ⱑ �Ұ����Ҷ� true ����
	}
	
	public boolean hasBoard() {
		return total >0; // �Խù��� ������ true ����
	}
	
	public int getTotalPages() {
		return totalPages; // �������� �� ���� ����
	}
	
	public List<BoardVO> getBoardList()
	{
		return boardList; // �ش� ������ ǥ���� �� ��� ����
	}
	
	public int getStartPage() {
		return startPage; // �ش� ������ �׷��� ���۹�ȣ ����
	}
	
	public int getEndPage() {
		return endPage; // �ش� ������ �׷��� ����ȣ ����
	}

	@Override
	public String toString() {
		return "BoardPageDTO [total=" + total + ", currentPage=" + currentPage + ", boardList=" + boardList
				+ ", totalPages=" + totalPages + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}


}


