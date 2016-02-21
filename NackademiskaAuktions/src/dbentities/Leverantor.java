package dbentities;

public class Leverantor {

	private String orgnummer;
	private String namn;
	private float provisionsprocent;
	
	public Leverantor(String orgnummer, String namn, float provisionsprocent) {
		super();
		this.orgnummer = orgnummer;
		this.namn = namn;
		this.provisionsprocent = provisionsprocent;
	}

	public String getOrgnummer() {
		return orgnummer;
	}

	public void setOrgnummer(String orgnummer) {
		this.orgnummer = orgnummer;
	}

	public String getNamn() {
		return namn;
	}

	public void setNamn(String namn) {
		this.namn = namn;
	}

	public float getProvisionsprocent() {
		return provisionsprocent;
	}

	public void setProvisionsprocent(float provisionsprocent) {
		this.provisionsprocent = provisionsprocent;
	}

	@Override
	public String toString() {
		return  namn ;
	}
	
	
	
	
}
