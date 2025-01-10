package jobkorea.model.dto;

public class EnterpriseDto {
	   private int eno;
	   private String eid;
	   private String epwd;
	   private String ename;
	   private String eaddr;
	   
	   public EnterpriseDto() {}

	   public EnterpriseDto(int eno, String eid, String epwd, String ename, String eaddr) {
	      super();
	      this.eno = eno;
	      this.eid = eid;
	      this.epwd = epwd;
	      this.ename = ename;
	      this.eaddr = eaddr;
	   }

	   public int getEno() {
	      return eno;
	   }

	   public void setEno(int eno) {
	      this.eno = eno;
	   }

	   public String getEid() {
	      return eid;
	   }

	   public void setEid(String eid) {
	      this.eid = eid;
	   }

	   public String getEpwd() {
	      return epwd;
	   }

	   public void setEpwd(String epwd) {
	      this.epwd = epwd;
	   }

	   public String getEname() {
	      return ename;
	   }

	   public void setEname(String ename) {
	      this.ename = ename;
	   }

	   public String getEaddr() {
	      return eaddr;
	   }

	   public void setEaddr(String eaddr) {
	      this.eaddr = eaddr;
	   }

	   @Override
	   public String toString() {
		   return "EnterpriseDto [eno=" + eno + ", eid=" + eid + ", epwd=" + epwd + ", ename=" + ename + ", eaddr=" + eaddr
				+ "]";
	   }
	   
	   
}
