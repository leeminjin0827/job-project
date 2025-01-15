package jobkorea.model.dto;

public class ApplyDto {
	private int ano ; /// 멤버변수dp PK 키 안 들어가 있어서 수정 했습니당.
	private boolean apass;
	private int pno;
	private int mno;
	
	public ApplyDto() {}
	
	
	public ApplyDto(int ano, boolean apass, int pno, int mno) {
		super();
		this.ano = ano;
		this.apass = apass;
		this.pno = pno;
		this.mno = mno;
	} /// ano 추가한 풀 생성자 추가


	public ApplyDto(boolean apass, int pno, int mno) {
		super();
		this.apass = apass;
		this.pno = pno;
		this.mno = mno;
	}


	public int getAno() {
		return ano;
	}


	public void setAno(int ano) {
		this.ano = ano;
	}
		// ano getter , setter 추가

	public void setApass(boolean apass) {
		this.apass = apass;
	}


	public boolean isApass() {
		return apass;
	}


	public int getPno() {
		return pno;
	}


	public void setPno(int pno) {
		this.pno = pno;
	}


	public int getMno() {
		return mno;
	}


	public void setMno(int mno) {
		this.mno = mno;
	}


	@Override
	public String toString() {
		return "ApplyDto [apass=" + apass + ", pno=" + pno + ", mno=" + mno + "]";
	}
	
}
