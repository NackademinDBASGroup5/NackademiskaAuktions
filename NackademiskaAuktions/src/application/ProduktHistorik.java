package application;

import java.sql.Date;

public class ProduktHistorik {
	int id; // FK produkt
	Leverantor leverantor;
	String namn;
	String beskrivning;
	String bild; // blob
	Date registreringsdatum;
}
