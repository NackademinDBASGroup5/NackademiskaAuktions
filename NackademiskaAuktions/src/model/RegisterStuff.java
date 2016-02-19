package model;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import application.Kund;
import application.Leverantor;
import application.Main;
import application.Produkt;


public class RegisterStuff {
	Connection connect = null;
	PreparedStatement pstm = null;
	ResultSet rset = null;
	
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
					+ "personnummer, förnamn, Efternamn, gatuadress, postnummer, ort, epost, telefon)" + " Values (?, ?, ?, ?, ?, ?, ?, ?)");
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
			PreparedStatement prepstm = connect.prepareStatement("Insert into Leverantör("
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
					+ "Leverantör, namn, beskrivning, bild, registreringsDatum)" + " Values (?, ?, ?, ?, now())");
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
	

}
