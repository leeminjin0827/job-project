package jobkorea.model.dto;

public class ApplyDto {
	private boolean apass;
	private int pno;
	private int mno;
	
	public ApplyDto() {}
	

	public ApplyDto(boolean apass, int pno, int mno) {
		super();
		this.apass = apass;
		this.pno = pno;
		this.mno = mno;
	}


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
<<<<<<< HEAD

	}
=======
	
}
>>>>>>> branch 'kimdoha' of https://github.com/leeminjin0827/job-project
