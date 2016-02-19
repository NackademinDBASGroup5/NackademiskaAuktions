package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.Auktion;

public class ListCurrAuctions {
	

	CallableStatement cstm = null;
	ResultSet rs = null;
	ResultSet rs2 = null;
	Statement stm = null;
	String _from;
	String _to;
	Connection conn;
	ArrayList<Auktion> auktionslista = new ArrayList<>();
	
	
	public ListCurrAuctions(){
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/auktion", "thobias", "byll@r");
			cstm = conn.prepareCall("{CALL datumintervall(?,?)}");
			stm = conn.createStatement();
			rs2 = stm.executeQuery("SELECT Auktionsnummer, produkt.namn FROM AUKTION INNER JOIN Produkt ON produkt.id=auktion.produkt;");
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public ArrayList<Auktion> createAuctionsObj(){
		
		try {
			while(rs2.next()){
				
				Auktion temp = new Auktion();
				temp.setAuktionsnummer(rs2.getInt("auktionsnummer"));
				temp.setNamn(rs.getString("namn"));
				auktionslista.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return auktionslista;
	}
	
	
	public ResultSet getAuctionsIntervall(String from, String to){
		
		
		try {
			cstm.setString(1, from);
			cstm.setString(2, to+" 23:59:59");
			cstm.execute();
			rs=cstm.getResultSet();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	
	}

}

