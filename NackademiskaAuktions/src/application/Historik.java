package application;

import java.sql.Date;

import javafx.scene.image.Image;

public class Historik {
	private int arkiveringsnummer;
	private String leverantor;
	private String kund;
	private int auktionsnummer;
	private String produktnamn;
	private String produktregistreringsdatum;
	private String produktbeskrivning;
	private Image bild;
	private int utropspris; 
	private int AcceptPris; 
	private String starttid;
	private String sluttid;
	private int bud;
	private String Budtid;
	public Historik(int arkiveringsnummer, String leverantor, String kund, int auktionsnummer, String produktnamn,
			String produktregistreringsdatum, String produktbeskrivning, Image bild, int utropspris, int acceptPris,
			String starttid, String sluttid, int bud, String budtid) {
		super();
		this.arkiveringsnummer = arkiveringsnummer;
		this.leverantor = leverantor;
		this.kund = kund;
		this.auktionsnummer = auktionsnummer;
		this.produktnamn = produktnamn;
		this.produktregistreringsdatum = produktregistreringsdatum;
		this.produktbeskrivning = produktbeskrivning;
		this.bild = bild;
		this.utropspris = utropspris;
		AcceptPris = acceptPris;
		this.starttid = starttid;
		this.sluttid = sluttid;
		this.bud = bud;
		Budtid = budtid;
	}

	public int getArkiveringsnummer() {
		return arkiveringsnummer;
	}
	public void setArkiveringsnummer(int arkiveringsnummer) {
		this.arkiveringsnummer = arkiveringsnummer;
	}
	public String getLeverantor() {
		return leverantor;
	}
	public void setLeverantor(String leverantor) {
		this.leverantor = leverantor;
	}
	public String getKund() {
		return kund;
	}
	public void setKund(String kund) {
		this.kund = kund;
	}
	public int getAuktionsnummer() {
		return auktionsnummer;
	}
	public void setAuktionsnummer(int auktionsnummer) {
		this.auktionsnummer = auktionsnummer;
	}
	public String getProduktnamn() {
		return produktnamn;
	}
	public void setProduktnamn(String produktnamn) {
		this.produktnamn = produktnamn;
	}
	public String getProduktregistreringsdatum() {
		return produktregistreringsdatum;
	}
	public void setProduktregistreringsdatum(String produktregistreringsdatum) {
		this.produktregistreringsdatum = produktregistreringsdatum;
	}
	public String getProduktbeskrivning() {
		return produktbeskrivning;
	}
	public void setProduktbeskrivning(String produktbeskrivning) {
		this.produktbeskrivning = produktbeskrivning;
	}
	public Image getBild() {
		return bild;
	}
	public void setBild(Image bild) {
		this.bild = bild;
	}
	public int getUtropspris() {
		return utropspris;
	}
	public void setUtropspris(int utropspris) {
		this.utropspris = utropspris;
	}
	public int getAcceptPris() {
		return AcceptPris;
	}
	public void setAcceptPris(int acceptPris) {
		AcceptPris = acceptPris;
	}
	public String getStarttid() {
		return starttid;
	}
	public void setStarttid(String starttid) {
		this.starttid = starttid;
	}
	public String getSluttid() {
		return sluttid;
	}
	public void setSluttid(String sluttid) {
		this.sluttid = sluttid;
	}
	public int getBud() {
		return bud;
	}
	public void setBud(int bud) {
		this.bud = bud;
	}
	public String getBudtid() {
		return Budtid;
	}
	public void setBudtid(String budtid) {
		Budtid = budtid;
	}
	
	
}
