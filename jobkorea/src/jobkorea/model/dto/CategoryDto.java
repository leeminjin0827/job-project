package jobkorea.model.dto;

public class CategoryDto {
	// 멤버변수
	private int cno;
	private String cname;
	
	// 생성자
	public CategoryDto(){};
	public CategoryDto(int cno , String cname){
		super();
		this.cno = cno;
		this.cname = cname;
	}
	
	// 메소드
		// getter , setter
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	// toString
	@Override
	public String toString() {
		return "CategoryDto [cno=" + cno + ", cname=" + cname + "]";
	}
	
	
	
	
}
