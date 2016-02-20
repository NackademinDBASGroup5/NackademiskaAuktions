package application;

import java.sql.Date;

public class Bud {

	AuktionsData auktion;
	int kronor;
	Kund kund;
	Date tid;
	
	public Bud(AuktionsData auktion, int kronor, Kund kund, Date tid) {
		super();
		this.auktion = auktion;
		this.kronor = kronor;
		this.kund = kund;
		this.tid = tid;
	}
	
	
}
