package jobkorea.model.dto;

public class PostDto {
	// 멤버변수
	private int pno;
	private String ptitle;
	private String pcontent;
	private String phistory;
	private String pcount;
	private String psalary;
	private String pstart;
	private String pend;
	private int cno;
	private int eno;

	// 생성자
	public PostDto() {};
	
	
	public PostDto(int pno, String ptitle, String pcontent, String phistory, String pcount, String psalary,
			String pstart, String pend, int cno, int eno) {
		super();
		this.pno = pno;
		this.ptitle = ptitle;
		this.pcontent = pcontent;
		this.phistory = phistory;
		this.pcount = pcount;
		this.psalary = psalary;
		this.pstart = pstart;
		this.pend = pend;
		this.cno = cno;
		this.eno = eno;
	}


	// 메소드
		// getter, setter
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public String getPtitle() {
		return ptitle;
	}
	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}
	public String getPcontent() {
		return pcontent;
	}
	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}
	public String getPhistory() {
		return phistory;
	}
	public void setPhistory(String phistory) {
		this.phistory = phistory;
	}
	public String getPcount() {
		return pcount;
	}
	public void setPcount(String pcount) {
		this.pcount = pcount;
	}
	public String getPsalary() {
		return psalary;
	}
	public void setPsalary(String psalary) {
		this.psalary = psalary;
	}
	public String getPstart() {
		return pstart;
	}
	public void setPstart(String pstart) {
		this.pstart = pstart;
	}
	public String getPend() {
		return pend;
	}
	public void setPend(String pend) {
		this.pend = pend;
	}
	
		public int getCno() {
		return cno;
	}


	public void setCno(int cno) {
		this.cno = cno;
	}


	public int getEno() {
		return eno;
	}


	public void setEno(int eno) {
		this.eno = eno;
	}

	// toString

	@Override
	public String toString() {
		return "PostDto [pno=" + pno + ", ptitle=" + ptitle + ", pcontent=" + pcontent + ", phistory=" + phistory
				+ ", pcount=" + pcount + ", psalary=" + psalary + ", pstart=" + pstart + ", pend=" + pend + ", cno="
				+ cno + ", eno=" + eno + "]";
	}


	
	
	
	
	
}
