package application;

public class Kund {
	
	String personnummer;
	String fornamn;
	String efternamn;
	String gatuadress;
	String postnummer;
	String ort;
	String epost;
	String telefon;
	
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

}
