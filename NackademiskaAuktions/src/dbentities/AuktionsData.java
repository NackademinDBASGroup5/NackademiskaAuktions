package dbentities;

import java.sql.Date;

public class AuktionsData {
	
	private int auktionsnummer;
	private String namn;
	private Produkt produkt;
	private int utropspris;
	private int acceptpris;
	private Date starttid;
	private Date sluttid;
	private String produkt_;
	private String provision_;
	private String namn_;
	private String sluttid_;
	private float provision;
	
	public AuktionsData(){
		
	}

	public AuktionsData(int auktionsnummer, Produkt produkt, int utropspris, int acceptpris, Date starttid, Date sluttid) {
		super();
		this.auktionsnummer = auktionsnummer;
		this.produkt = produkt;
		this.utropspris = utropspris;
		this.acceptpris = acceptpris;
		this.starttid = starttid;
		this.sluttid = sluttid;
	}
	
	
	public AuktionsData(String produkt, String namn, float provision,String sluttidString){
	
		produkt_= produkt;
		namn_ = namn;
		provision_ = Float.toString(provision);
		sluttid_ = sluttidString;
		
	}


	public int getAuktionsnummer() {
		return auktionsnummer;
	}


	public void setAuktionsnummer(int auktionsnummer) {
		this.auktionsnummer = auktionsnummer;
	}


	public String getNamn() {
		return namn;
	}


	public void setNamn(String namn) {
		this.namn = namn;
	}


	public Produkt getProdukt() {
		return produkt;
	}


	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}


	public int getUtropspris() {
		return utropspris;
	}


	public void setUtropspris(int utropspris) {
		this.utropspris = utropspris;
	}


	public int getAcceptpris() {
		return acceptpris;
	}


	public void setAcceptpris(int acceptpris) {
		this.acceptpris = acceptpris;
	}


	public Date getStarttid() {
		return starttid;
	}


	public void setStarttid(Date starttid) {
		this.starttid = starttid;
	}


	public Date getSluttid() {
		return sluttid;
	}


	public void setSluttid(Date sluttid) {
		this.sluttid = sluttid;
	}


	public String getProdukt_() {
		return produkt_;
	}


	public void setProdukt_(String produkt_) {
		this.produkt_ = produkt_;
	}


	public String getProvision_() {
		return provision_;
	}


	public void setProvision_(String provision_) {
		this.provision_ = provision_;
	}


	public String getNamn_() {
		return namn_;
	}


	public void setNamn_(String namn_) {
		this.namn_ = namn_;
	}


	public String getSluttid_() {
		return sluttid_;
	}


	public void setSluttid_(String sluttid_) {
		this.sluttid_ = sluttid_;
	}


	public float getProvision() {
		return provision;
	}


	public void setProvision(float provision) {
		this.provision = provision;
	}

	@Override
	public String toString() {
		return auktionsnummer+" "+ namn;
	}
	
	
}
