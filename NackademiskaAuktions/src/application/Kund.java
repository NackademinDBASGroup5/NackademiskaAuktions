package application;

public class Kund {
	
	private String personnummer;
	private String fornamn;
	private String efternamn;
	private String gatuadress;
	private String postnummer;
	private String ort;
	private String epost;
	private String telefon;
	
	public Kund(String personnummer, String fornamn, String efternamn, String gatuadress, String postnummer, String ort,
			String epost, String telefon) {
		super();
		this.personnummer = personnummer;
		this.fornamn = fornamn;
		this.efternamn = efternamn;
		this.gatuadress = gatuadress;
		this.postnummer = postnummer;
		this.ort = ort;
		this.epost = epost;
		this.telefon = telefon;
	}

	public String getPersonnummer() {
		return personnummer;
	}

	public void setPersonnummer(String personnummer) {
		this.personnummer = personnummer;
	}

	public String getFornamn() {
		return fornamn;
	}

	public void setFornamn(String fornamn) {
		this.fornamn = fornamn;
	}

	public String getEfternamn() {
		return efternamn;
	}

	public void setEfternamn(String efternamn) {
		this.efternamn = efternamn;
	}

	public String getGatuadress() {
		return gatuadress;
	}

	public void setGatuadress(String gatuadress) {
		this.gatuadress = gatuadress;
	}

	public String getPostnummer() {
		return postnummer;
	}

	public void setPostnummer(String postnummer) {
		this.postnummer = postnummer;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getEpost() {
		return epost;
	}

	public void setEpost(String epost) {
		this.epost = epost;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	@Override
	public String toString() {
		return "Kund [personnummer=" + personnummer + ", fornamn=" + fornamn + ", efternamn=" + efternamn
				+ ", gatuadress=" + gatuadress + ", postnummer=" + postnummer + ", ort=" + ort + ", epost=" + epost
				+ ", telefon=" + telefon + "]";
	}
	
	

}
