package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.Leverantor;
import application.Produkt;
import javafx.scene.image.Image;

public class RegisterAuktion {
	private Connection connect = null;
	private Statement stm = null;
	private ResultSet rset = null;
	private PreparedStatement pstm = null;

	public RegisterAuktion() {
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost/auktion", "simon", "abc123");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Leverantor> getLeverantorer() {
		try {
			ArrayList<Leverantor> levList = new ArrayList<Leverantor>();
			stm = connect.createStatement();
			rset = stm.executeQuery("Select * from Leverantör");

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

	public ArrayList<Produkt> getLeverantorProdukt(Leverantor selectedItem) {
		ArrayList<Produkt> prodList = new ArrayList<Produkt>();
		try {
			pstm = connect.prepareStatement("SELECT * from produkt where produkt.leverantör=?");
			pstm.setString(1, selectedItem.getOrgnummer());
			rset = pstm.executeQuery();
			while (rset.next()) {

				prodList.add(new Produkt(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4),
						(Image) rset.getBlob(5), rset.getDate(6)));

			}
			return prodList;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstm.close();
				stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return null;
	}

}
