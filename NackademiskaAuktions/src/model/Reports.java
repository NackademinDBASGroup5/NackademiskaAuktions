package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Reports {

	
	Connection connect = null;
	PreparedStatement pstm = null;
	ResultSet rset = null;
	
	//temporary
	public Reports(){
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost/auktion", "simon", "abc123");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
		
	public void showProvisions() {
		// TODO Auto-generated method stub
		
	}
	
}
