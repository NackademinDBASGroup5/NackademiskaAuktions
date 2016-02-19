package testcontrollers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Auktion;
import application.Historik;
import application.Kund;
import application.KundHistorik;
import application.Main;
import application.Provision;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Reports;

public class ProvisionMonthCon implements Initializable {

	@FXML
	TableView tableView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		TableColumn<Provision, String> provisionCol = new TableColumn<Provision, String>("Månad");
		provisionCol.setCellValueFactory(new PropertyValueFactory<Provision, String>("manad"));
		TableColumn<Provision, String> provisionCol2 = new TableColumn<Provision, String>("Provision");
		provisionCol2.setCellValueFactory(new PropertyValueFactory<Provision, String>("provision"));

		Reports rep = new Reports();

		tableView.getColumns().clear();
		tableView.getItems().clear();
		Platform.runLater(() -> {
			ObservableList<Provision> data = FXCollections.observableArrayList(rep.getProvisionReport());
			tableView.refresh();

			tableView.setItems(data);
			tableView.getColumns().addAll(provisionCol, provisionCol2);
		});

	}

}
