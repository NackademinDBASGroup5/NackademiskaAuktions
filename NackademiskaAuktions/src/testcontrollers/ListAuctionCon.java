package testcontrollers;

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Auktion;
import application.BudHistorik;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ListCurrAuctions;

public class ListAuctionCon implements Initializable {

	@FXML
	DatePicker fromDatePicker_curr, toDatePicker_curr;

	@FXML
	Button listButton_curr, listButton_bid;

	@FXML
	ComboBox<Auktion> auctionCombobox;

	@FXML
	TableView tableView_curr;

	ArrayList<Auktion> auktionslista = new ArrayList<>();

	CallableStatement cstm = null;
	ResultSet rs = null;

	ObservableList<Auktion> data = FXCollections.observableArrayList();
	ObservableList<BudHistorik> budData = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		TableColumn<Auktion, String> firstCol = new TableColumn<Auktion, String>("Produkt");
		firstCol.setCellValueFactory(new PropertyValueFactory<Auktion, String>("produkt_"));

		TableColumn<Auktion, String> secondCol = new TableColumn<Auktion, String>("Namn");
		secondCol.setCellValueFactory(new PropertyValueFactory<Auktion, String>("namn_"));

		TableColumn<Auktion, String> thirdCol = new TableColumn<Auktion, String>("Provision");
		thirdCol.setCellValueFactory(new PropertyValueFactory<Auktion, String>("provision_"));

		TableColumn<Auktion, String> forthCol = new TableColumn<Auktion, String>("Sluttid");
		forthCol.setCellValueFactory(new PropertyValueFactory<Auktion, String>("sluttid_"));
		forthCol.setPrefWidth(125);

		TableColumn<BudHistorik, Integer> firstBidCol = new TableColumn<>("Bud");
		firstBidCol.setCellValueFactory(new PropertyValueFactory<BudHistorik, Integer>("kronor"));

		TableColumn<BudHistorik, String> secondBidCol = new TableColumn<>("Namn");
		secondBidCol.setCellValueFactory(new PropertyValueFactory<BudHistorik, String>("namn"));

		TableColumn<BudHistorik, String> thirdBidCol = new TableColumn<>("Tidpunkt");
		thirdBidCol.setCellValueFactory(new PropertyValueFactory<BudHistorik, String>("budTid"));

		ListCurrAuctions currAuctions = new ListCurrAuctions();

		auktionslista = currAuctions.createAuctionsObj();

		auctionCombobox.getItems().addAll(auktionslista);

		listButton_curr.setOnAction(a -> {

			data.clear();
			tableView_curr.getColumns().clear();
			tableView_curr.getItems().clear();

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
			tableView_curr.getColumns().addAll(firstCol, secondCol, thirdCol, forthCol);

			try {
				rs.beforeFirst();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

		listButton_bid.setOnAction(e -> {
			budData.clear();
			tableView_curr.getColumns().clear();
			tableView_curr.getItems().clear();

			budData = currAuctions.setPreparedStm(auctionCombobox.getValue().getAuktionsnummer());

			tableView_curr.setItems(budData);
			tableView_curr.getColumns().addAll(firstBidCol, secondBidCol, thirdBidCol);

		});

	}

}
