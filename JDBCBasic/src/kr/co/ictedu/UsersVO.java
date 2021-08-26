package kr.co.ictedu;

public class UsersVO {
	/*
	 * VO / DTO
	 * - VOŬ������ �������� DB���� ������ �ۼ����� ����
	 * �ڹ� Ŭ���� �����Դϴ�.
	 * 
	 * - VOŬ������ ������ ���� DB���̺� �÷� ������ŭ
	 * 1:1�� �ڷ���, �̸� ���� ��Ī�Ǵ� ����������� �����մϴ�.
	 * 
	 * - VOŬ���� ���� �������� private ���� �����ڸ� �ɾ
	 * ������ ������ �����մϴ�.
	 */
	private String uid;
	private String upw;
	private String uname;
	private String email;
	
	
	
	// ALT + Shift + S Ȥ�� ��Ŭ�� -> source�� �ִ� �޴� Ȱ��
	// VOŬ������ �⺻�����ڿ� ��� ��������� �ʱ�ȭ�ϴ� �����ڸ� ����
	public UsersVO() {
		//�⺻�����ڴ� ���� �ۼ�
	}
	
	// Generate Constructor Using Fields...
	public UsersVO(String uid, String upw, String uname, String email) {
		super();
		this.uid = uid;
		this.upw = upw;
		this.uname = uname;
		this.email = email;
	}
	// Getter�� Setter�� ����
	// Generate Getters and Setters -> Select ALL
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUpw() {
		return upw;
	}
	public void setUpw(String upw) {
		this.upw = upw;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	// Generate toSting
	@Override
	public String toString() {
		return "UsersVO [uid=" + uid + ", upw=" + upw + ", uname=" + uname + ", email=" + email + "]";
	}
	
	
}
