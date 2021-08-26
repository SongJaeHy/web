package kr.co.ictedu;

public class Write1VO {
	private String id;
	private String name;
	private String date;
	
	public Write1VO() {
		
	}

	public Write1VO(String id, String name, String date) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Write1VO [id=" + id + ", name=" + name + ", date=" + date + "]";
	}
	
	
}
