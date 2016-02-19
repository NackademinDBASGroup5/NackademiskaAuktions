package controllers;

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Auktion;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ListCurrAuctions;

public class AuctionC implements Initializable {

	@FXML
	DatePicker fromDatePicker_curr, toDatePicker_curr, fromDatePicker_hist, toDatePicker_hist;

	@FXML
	Button listButton_curr, listAllButton_curr, listButton_hist, listAllButton_hist, backButton_curr, backButton_hist;

	@FXML
	TableView tableView_hist;
	@FXML
	TableView<Auktion> tableView_curr;

	CallableStatement cstm = null;
	ResultSet rs = null;

	ObservableList<Auktion> data = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		TableColumn firstCol = new TableColumn("Produkt");
		firstCol.setCellValueFactory(new PropertyValueFactory<Auktion, String>("produkt_"));

		TableColumn secondCol = new TableColumn("Namn");
		secondCol.setCellValueFactory(new PropertyValueFactory<Auktion, String>("namn_"));

		TableColumn thirdCol = new TableColumn("Provision");
		thirdCol.setCellValueFactory(new PropertyValueFactory<Auktion, String>("provision_"));

		TableColumn forthCol = new TableColumn("Sluttid");
		forthCol.setCellValueFactory(new PropertyValueFactory<Auktion, String>("sluttid_"));
		forthCol.setPrefWidth(125);

		ListCurrAuctions currAuctions = new ListCurrAuctions();

		backButton_curr.setOnAction(a -> {
			Main.mainStage.setScene(Main.mainScene);
		});

		backButton_hist.setOnAction(a -> {
			Main.mainStage.setScene(Main.mainScene);
		});

		listButton_curr.setOnAction(a -> {

			data.removeAll(data);

			rs = currAuctions.getAuctionsIntervall(fromDatePicker_curr.getValue().toString(),
					toDatePicker_curr.getValue().toString());
			

			try {
				while (rs.next()) {

					data.add(new Auktion(rs.getString("produkt"), rs.getString("namn"), rs.getFloat("provision"),
							rs.getString("sluttid")));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			tableView_curr.setItems(data);
			
			try {
				rs.beforeFirst();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

		tableView_curr.getColumns().addAll(firstCol, secondCol, thirdCol, forthCol);
		

	}

}
