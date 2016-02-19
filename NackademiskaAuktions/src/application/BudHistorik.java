package application;

public class BudHistorik {

	String kronor;
	String namn;
	String budTid;

	public BudHistorik(String kronor, String namn, String budTid) {
		super();
		this.kronor = kronor;
		this.namn = namn;
		this.budTid = budTid;
	}

	public String getKronor() {
		return kronor;
	}

	public void setKronor(String kronor) {
		this.kronor = kronor;
	}

	public String getNamn() {
		return namn;
	}

	public void setNamn(String namn) {
		this.namn = namn;
	}

	public String getBudTid() {
		return budTid;
	}

	public void setBudTid(String budTid) {
		this.budTid = budTid;
	}

}
