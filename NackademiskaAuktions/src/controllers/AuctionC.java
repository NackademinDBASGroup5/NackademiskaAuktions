package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dbentities.AuktionsData;
import dbentities.BudHistorik;
import dbentities.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ListCurrAuctions;

public class AuctionC implements Initializable {

	@FXML
	DatePicker fromDatePicker_curr, toDatePicker_curr;

	@FXML
	Button listButton_curr, listButton_bid, backButton;

	@FXML
	ComboBox<AuktionsData> auctionCombobox;

	@SuppressWarnings("rawtypes")
	@FXML
	TableView tableView_curr;

	ArrayList<AuktionsData> auktionslista = new ArrayList<>();

	ObservableList<AuktionsData> data = FXCollections.observableArrayList();
	ObservableList<BudHistorik> budData = FXCollections.observableArrayList();

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		TableColumn<AuktionsData, String> firstCol = new TableColumn<AuktionsData, String>("Produkt");
		firstCol.setCellValueFactory(new PropertyValueFactory<AuktionsData, String>("produkt_"));

		TableColumn<AuktionsData, String> secondCol = new TableColumn<AuktionsData, String>("Namn");
		secondCol.setCellValueFactory(new PropertyValueFactory<AuktionsData, String>("namn_"));

		TableColumn<AuktionsData, String> thirdCol = new TableColumn<AuktionsData, String>("Provision");
		thirdCol.setCellValueFactory(new PropertyValueFactory<AuktionsData, String>("provision_"));

		TableColumn<AuktionsData, String> forthCol = new TableColumn<AuktionsData, String>("Sluttid");
		forthCol.setCellValueFactory(new PropertyValueFactory<AuktionsData, String>("sluttid_"));
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

		backButton.setOnAction(a -> {
			Main.mainStage.setScene(Main.mainScene);
		});

		listButton_curr.setOnAction(a -> {
			if(isDatesSelected(fromDatePicker_curr.getValue(), toDatePicker_curr.getValue())){
			data.clear();
			tableView_curr.getColumns().clear();
			tableView_curr.getItems().clear();

				data.addAll(currAuctions.getAuctionsIntervall(fromDatePicker_curr.getValue().toString(),
						toDatePicker_curr.getValue().toString()));

			tableView_curr.setItems(data);
			tableView_curr.getColumns().addAll(firstCol, secondCol, thirdCol, forthCol);
			}

		});

		listButton_bid.setOnAction(e -> {
			if(auctionCombobox.getSelectionModel().getSelectedItem()!=null){
			budData.clear();
			tableView_curr.getColumns().clear();
			tableView_curr.getItems().clear();

			budData = currAuctions.setPreparedStm(auctionCombobox.getValue().getAuktionsnummer());

			tableView_curr.setItems(budData);
			tableView_curr.getColumns().addAll(firstBidCol, secondBidCol, thirdBidCol);}
			else{
				warningMessage("Välj en auktion för att lista bud");
			}

		});

	}

	private boolean isDatesSelected(LocalDate fromDate, LocalDate toDate) {

		if (fromDate == null) {
			warningMessage("Var god välj ett fråndatum");
			return false;
		} else if (toDate == null) {
			warningMessage("Var god välj ett tilldatum");
			return false;
		}

		return true;
	}

	private void warningMessage(String s) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(s);
		alert.showAndWait();
	}

}
