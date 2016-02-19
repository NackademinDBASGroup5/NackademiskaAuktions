package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.Kund;
import application.Provision;
import javafx.util.Callback;

public class Reports {
	private Connection connect;
	private Statement stm = null;
	private ResultSet rset = null;

	public Reports() {

		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost/auktion", "thobias", "byll@r");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<Kund> getCustomerReport() {
		ArrayList<Kund> kundList = new ArrayList<Kund>();
		try {
			stm = connect.createStatement();
			rset = stm.executeQuery("Select kund.* from auktionshistorik inner "
					+ "join kund on auktionshistorik.kund = kund.personnummer "
					+ "group by kund.personnummer");
			while (rset.next()) {
				kundList.add(new Kund(rset.getString(1),rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5),rset.getString(6),
						rset.getString(7), rset.getString(8)));
				
			}
			return kundList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kundList;

	}

	public ArrayList<Provision> getProvisionReport() {
		ArrayList<Provision> provList = new ArrayList<Provision>();
		try {
			stm = connect.createStatement();
			rset = stm.executeQuery("SELECT date_format(sluttid,'%b %Y'), ROUND(sum(provision),2) FROM provision group by month(sluttid);");
			while (rset.next()) {
			
				provList.add(new Provision(rset.getString(1), Float.toString(rset.getFloat(2))));
			}
			return provList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return provList;

	}
}
