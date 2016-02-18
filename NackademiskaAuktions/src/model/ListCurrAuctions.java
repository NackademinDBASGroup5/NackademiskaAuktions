package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class ListCurrAuctions {
	

	CallableStatement cstm = null;
	ResultSet rs = null;
	String _from;
	String _to;
	Connection conn;
	
	
	public ListCurrAuctions(){
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/auktion", "thobias", "byll@r");
			cstm = conn.prepareCall("{CALL datumintervall(?,?)}");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public ResultSet getAuctions(String from, String to){
		
		
		try {
			cstm.setString(1, from);
			cstm.setString(2, to);
			cstm.execute();
			rs=cstm.getResultSet();
			
			
			while(rs.next()){
				
				//System.out.println(rs.getString("Produkt")+" "+rs.getString("namn")+" "+rs.getFloat("Provision")+" "+rs.getString("sluttid"));
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.beforeFirst();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	
	}

}
