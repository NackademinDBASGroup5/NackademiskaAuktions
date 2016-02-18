package application;

import java.sql.Date;

import javafx.scene.image.Image;

public class Produkt {

	private int id;
	private String leverantor;
	private String namn;
	private String beskrivning;
	private Image bild; 
	private Date registreringsdatum;
	
	public Produkt(int id, String leverantor, String namn, String beskrivning, Image bild,
			Date registreringsdatum) {
		super();
		this.id = id;
		this.leverantor = leverantor;
		this.namn = namn;
		this.beskrivning = beskrivning;
		this.bild = bild;
		this.registreringsdatum = registreringsdatum;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLeverantor() {
		return leverantor;
	}

	public void setLeverantor(String leverantor) {
		this.leverantor = leverantor;
	}

	public String getNamn() {
		return namn;
	}

	public void setNamn(String namn) {
		this.namn = namn;
	}

	public String getBeskrivning() {
		return beskrivning;
	}

	public void setBeskrivning(String beskrivning) {
		this.beskrivning = beskrivning;
	}

	public Image getBild() {
		return bild;
	}

	public void setBild(Image bild) {
		this.bild = bild;
	}

	public Date getRegistreringsdatum() {
		return registreringsdatum;
	}

	public void setRegistreringsdatum(Date registreringsdatum) {
		this.registreringsdatum = registreringsdatum;
	}
	
	
	
}
