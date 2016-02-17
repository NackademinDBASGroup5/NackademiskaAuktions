package application;

import java.sql.Date;

public class Auktion {
	
	int auktionsnummer;
	Produkt produkt;
	int utropspris;
	int acceptpris;
	Date starttid;
	Date sluttid;

	public Auktion(int auktionsnummer, Produkt produkt, int utropspris, int acceptpris, Date starttid, Date sluttid) {
		super();
		this.auktionsnummer = auktionsnummer;
		this.produkt = produkt;
		this.utropspris = utropspris;
		this.acceptpris = acceptpris;
		this.starttid = starttid;
		this.sluttid = sluttid;
	}

}
