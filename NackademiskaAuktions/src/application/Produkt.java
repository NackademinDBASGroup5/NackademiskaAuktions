package application;

import java.sql.Date;

public class Produkt {

	int id;
	Leverantor leverantor;
	String namn;
	String beskrivning;
	String bild; // annan datatyp?
	Date registreringsdatum;
	
	public Produkt(int id, Leverantor leverantor, String namn, String beskrivning, String bild,
			Date registreringsdatum) {
		super();
		this.id = id;
		this.leverantor = leverantor;
		this.namn = namn;
		this.beskrivning = beskrivning;
		this.bild = bild;
		this.registreringsdatum = registreringsdatum;
	}
	
}
