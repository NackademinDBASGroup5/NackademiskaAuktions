package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListCurrAuctions {
	

	CallableStatement cstm = null;
	ResultSet rs = null;
	String _from;
	String _to;
	Connection conn;
	
	
	public ListCurrAuctions(){
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/auktion", "Simon", "abc123");
			cstm = conn.prepareCall("{CALL datumintervall(?,?)}");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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

