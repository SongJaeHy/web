package kr.co.ictedu.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// ��Ʈ�ѷ����ٰ� ���������� ��� �ϳ��ϳ��� ���� �ڵ带 �ۼ��Ҽ��� ������
// �׷��� �Ǹ� ����� ���������� �ڵ������ ������ ��������� ������ �ֽ��ϴ�.
// ���� ����� �ϳ��ϳ� �ٽ� Service��� ������ ������
// �������ݴϴ�.
public interface IBoardService {

	// ���� �������̽� ������ ���� �޼��� �ϳ��� ����ϴ�.
	void execute(HttpServletRequest request,HttpServletResponse response);
		
}
