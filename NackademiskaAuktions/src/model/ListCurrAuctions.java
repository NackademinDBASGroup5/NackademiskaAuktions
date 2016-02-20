package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.Auktion;
import application.BudHistorik;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListCurrAuctions {

	CallableStatement cstm = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	ResultSet rs2 = null;
	ResultSet rs3 = null;
	Statement stm = null;
	String _from;
	String _to;
	Connection conn;
	ArrayList<Auktion> auktionslista = new ArrayList<>();
	ObservableList<BudHistorik> budData = FXCollections.observableArrayList();

	public ListCurrAuctions() {

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/auktion", Main.username, Main.password);

			cstm = conn.prepareCall("{CALL datumintervall(?,?)}");
			stm = conn.createStatement();
			rs2 = stm.executeQuery(
					"SELECT Auktionsnummer, produkt.namn FROM AUKTION INNER JOIN Produkt ON produkt.id=auktion.produkt;");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<Auktion> createAuctionsObj() {

		try {
			while (rs2.next()) {
				Auktion temp = new Auktion();
				temp.setAuktionsnummer(rs2.getInt("auktionsnummer"));
				temp.setNamn(rs2.getString("namn"));
				auktionslista.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return auktionslista;
	}

	public ObservableList<BudHistorik> setPreparedStm(int auktionsNr) {
		try {

			pstm = conn.prepareStatement(
					"SELECT kronor, concat(kund.förnamn,' ',kund.efternamn) AS 'Namn', tid FROM BUD INNER JOIN Kund ON Kund.personnummer=bud.kund WHERE auktion=? ORDER BY Kronor ASC");
			pstm.setInt(1, auktionsNr);

			rs3 = pstm.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return addDataToBudHistorik(rs3);

	}

	private ObservableList<BudHistorik> addDataToBudHistorik(ResultSet rs3) {
		budData.clear();

		try {
			while (rs3.next()) {

				budData.add(new BudHistorik(Integer.toString(rs3.getInt("kronor")), rs3.getString("namn"),
						rs3.getString("tid")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs3.beforeFirst();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return budData;

	}

	public ArrayList<Auktion> getAuctionsIntervall(String from, String to) {
		ArrayList<Auktion> auktionData = new ArrayList<Auktion>();
		try {
			cstm.setString(1, from);
			cstm.setString(2, to + " 23:59:59");
			cstm.execute();
			rs = cstm.getResultSet();
			try {
				while (rs.next()) {

					auktionData.add(new Auktion(rs.getString("produkt"), rs.getString("namn"), rs.getFloat("provision"),
							rs.getString("sluttid")));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return auktionData;

	}

}
