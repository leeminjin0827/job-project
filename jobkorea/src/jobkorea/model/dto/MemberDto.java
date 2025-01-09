package jobkorea.model.dto;

public class MemberDto {
	private int mno;
	private String mid;
	private String mpwd;
	private String mname;
	private boolean mgender;
	private String mdate;
	private String maddr;
	
	public MemberDto() {}
	public MemberDto(int mno, String mid, String mpwd, String mname, boolean mgender, String mdate, String maddr) {
		super();
		this.mno = mno;
		this.mid = mid;
		this.mpwd = mpwd;
		this.mname = mname;
		this.mgender = mgender;
		this.mdate = mdate;
		this.maddr = maddr;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMpwd() {
		return mpwd;
	}
	public void setMpwd(String mpwd) {
		this.mpwd = mpwd;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public boolean isMgender() {
		return mgender;
	}
	public void setMgender(boolean mgender) {
		this.mgender = mgender;
	}
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	public String getMaddr() {
		return maddr;
	}
	public void setMaddr(String maddr) {
		this.maddr = maddr;
	}
	@Override
	public String toString() {
		return "MemberDto [mno=" + mno + ", mid=" + mid + ", mpwd=" + mpwd + ", mname=" + mname + ", mgender=" + mgender
				+ ", mdate=" + mdate + ", maddr=" + maddr + "]";
	}
	
	
	
	
	
}
