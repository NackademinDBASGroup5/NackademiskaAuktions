package application;

import java.sql.Date;

public class AuktionsHistorik {
	
	int auktionsnummer;
	int utropsPris;
	int acceptPris;
	Date starttid;
	Date sluttid;
	int kronor;
	String kund;
	Date tid;
	int produktid;
	String leverantor;
	String namn;
	String beskrivning;
	String bild; // något annat format?
	Date registreringsDatum;
	
	public AuktionsHistorik(int auktionsnummer, int utropsPris, int acceptPris, Date starttid, Date sluttid, int kronor,
			String kund, Date tid, int produktid, String leverantor, String namn, String beskrivning, String bild,
			Date registreringsDatum) {
		super();
		this.auktionsnummer = auktionsnummer;
		this.utropsPris = utropsPris;
		this.acceptPris = acceptPris;
		this.starttid = starttid;
		this.sluttid = sluttid;
		this.kronor = kronor;
		this.kund = kund;
		this.tid = tid;
		this.produktid = produktid;
		this.leverantor = leverantor;
		this.namn = namn;
		this.beskrivning = beskrivning;
		this.bild = bild;
		this.registreringsDatum = registreringsDatum;
	}


}
