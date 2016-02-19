package testcontrollers;

import java.net.URL;
import java.util.ResourceBundle;

import application.KundHistorik;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Reports;

public class CustomerSpentCon implements Initializable {

	@FXML
	TableView tableView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		TableColumn<KundHistorik, String> firstCol = new TableColumn<KundHistorik, String>("Personnummer");
		firstCol.setCellValueFactory(new PropertyValueFactory<KundHistorik, String>("personnummer"));
		TableColumn<KundHistorik, String> secondCol = new TableColumn<KundHistorik, String>("F�rnamn");
		secondCol.setCellValueFactory(new PropertyValueFactory<KundHistorik, String>("fornamn"));
		TableColumn<KundHistorik, String> thirdCol = new TableColumn<KundHistorik, String>("Efternamn");
		thirdCol.setCellValueFactory(new PropertyValueFactory<KundHistorik, String>("efternamn"));
		TableColumn<KundHistorik, String> forthCol = new TableColumn<KundHistorik, String>("Gatuadress");
		forthCol.setCellValueFactory(new PropertyValueFactory<KundHistorik, String>("gatuadress"));
		TableColumn<KundHistorik, String> fifthCol = new TableColumn<KundHistorik, String>("Postnummer");
		fifthCol.setCellValueFactory(new PropertyValueFactory<KundHistorik, String>("postnummer"));
		TableColumn<KundHistorik, String> sixthCol = new TableColumn<KundHistorik, String>("Ort");
		sixthCol.setCellValueFactory(new PropertyValueFactory<KundHistorik, String>("ort"));
		TableColumn<KundHistorik, String> seventhCol = new TableColumn<KundHistorik, String>("Epost");
		seventhCol.setCellValueFactory(new PropertyValueFactory<KundHistorik, String>("epost"));
		TableColumn<KundHistorik, String> eightCol = new TableColumn<KundHistorik, String>("Telefon");
		eightCol.setCellValueFactory(new PropertyValueFactory<KundHistorik, String>("telefon"));
		TableColumn<KundHistorik, String> ninthCol = new TableColumn<KundHistorik, String>("Totalt Handlat");
		ninthCol.setCellValueFactory(new PropertyValueFactory<KundHistorik, String>("ordervarde"));

		Reports rep = new Reports();

		tableView.getColumns().clear();
		tableView.getItems().clear();
		Platform.runLater(() -> {
			ObservableList<KundHistorik> data = FXCollections.observableArrayList(rep.getCustomerReport());
			tableView.refresh();

			tableView.setItems(data);
			tableView.getColumns().addAll(firstCol, secondCol, thirdCol, forthCol, fifthCol, sixthCol, seventhCol,
					eightCol, ninthCol);
		});

	}

}