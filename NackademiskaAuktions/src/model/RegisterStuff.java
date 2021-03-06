package model;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import dbentities.Kund;
import dbentities.Leverantor;
import dbentities.Main;
import dbentities.Produkt;


public class RegisterStuff {
	Connection connect = null;
	PreparedStatement pstm = null;
	ResultSet rset = null;
	private Statement stm = null;
	
	public RegisterStuff(){

		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost/auktion", Main.username, Main.password);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean registerToDatabase(Kund kund){

		try {
			connect.setAutoCommit(false);
			PreparedStatement prepstm = connect.prepareStatement("Insert into Kund("
					+ "personnummer, f�rnamn, Efternamn, gatuadress, postnummer, ort, epost, telefon)" + " Values (?, ?, ?, ?, ?, ?, ?, ?)");
			prepstm.setString(1, kund.getPersonnummer());
			prepstm.setString(2, kund.getFornamn());
			prepstm.setString(3, kund.getEfternamn());
			prepstm.setString(4, kund.getGatuadress());
			prepstm.setString(5, kund.getPostnummer());
			prepstm.setString(6, kund.getOrt());
			prepstm.setString(7, kund.getEpost());
			prepstm.setString(8, kund.getTelefon());
			int a = prepstm.executeUpdate();
			if (a == 1) {
				connect.commit();
				return true;
			}
		} catch (MySQLIntegrityConstraintViolationException e1) {
			// TODO Auto-generated catch block
			 try {
				connect.rollback();
				return false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	return false;

	}
	public boolean registerLeverantorToDatabase(Leverantor lev) {
		try {
			connect.setAutoCommit(false);
			PreparedStatement prepstm = connect.prepareStatement("Insert into Leverant�r("
					+ "OrgNummer, Namn, provisionsprocent)" + " Values (?, ?, ?)");
			prepstm.setString(1, lev.getOrgnummer());
			prepstm.setString(2,lev.getNamn());
			prepstm.setFloat(3, lev.getProvisionsprocent());
			
			int a = prepstm.executeUpdate();
			if (a == 1) {
				connect.commit();
				return true;
			}
		} catch (MySQLIntegrityConstraintViolationException e1) {
			// TODO Auto-generated catch block
			 try {
				connect.rollback();
				return false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	return false;

	}
	public boolean registerProduktToDatabase(Produkt prod) {
		try {
			connect.setAutoCommit(false);
			PreparedStatement prepstm = connect.prepareStatement("Insert into Produkt("
					+ "Leverant�r, namn, beskrivning, bild, registreringsDatum)" + " Values (?, ?, ?, ?, now())");
			prepstm.setString(1, prod.getLeverantor());
			prepstm.setString(2,prod.getNamn());
			prepstm.setString(3, prod.getBeskrivning());
			prepstm.setBlob(4, (Blob) prod.getBild());
			
			int a = prepstm.executeUpdate();
			if (a == 1) {
				connect.commit();
				return true;
			}
		} catch (MySQLIntegrityConstraintViolationException e1) {
			// TODO Auto-generated catch block
			 try {
				connect.rollback();
				return false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	return false;
	}
	public ArrayList<Leverantor> getLeverantorer() {
		try {
			ArrayList<Leverantor> levList = new ArrayList<Leverantor>();
			stm = connect.createStatement();
			rset = stm.executeQuery("Select * from Leverant�r");

			while (rset.next()) {
				levList.add(new Leverantor(rset.getString(1), rset.getString(2), rset.getFloat(3)));
			}
			return levList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	

}
