package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Auktion;
import application.Historik;
import application.Kund;
import application.Main;
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

public class ReportC implements Initializable {

	@FXML 
	Button customerReportButton, provisionPerMonthButton, backButton;
	
	@FXML
	TableView<Kund> tableView;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Reports reports = new Reports();
		TableColumn<Kund, String> firstCol = new TableColumn<Kund, String>("Personnummer");
		firstCol.setCellValueFactory(new PropertyValueFactory<Kund, String>("personnummer"));
		TableColumn<Kund, String> secondCol = new TableColumn<Kund, String>("Förnamn");
		secondCol.setCellValueFactory(new PropertyValueFactory<Kund, String>("fornamn"));
		TableColumn<Kund, String> thirdCol = new TableColumn<Kund, String>("Efternamn");
		thirdCol.setCellValueFactory(new PropertyValueFactory<Kund, String>("efternamn"));
		TableColumn<Kund, String> forthCol = new TableColumn<Kund, String>("Gatuadress");
		forthCol.setCellValueFactory(new PropertyValueFactory<Kund, String>("gatuadress"));
		TableColumn<Kund, String> fifthCol = new TableColumn<Kund, String>("Postnummer");
		fifthCol.setCellValueFactory(new PropertyValueFactory<Kund, String>("postnummer"));
		TableColumn<Kund, String> sixthCol = new TableColumn<Kund, String>("Ort");
		sixthCol.setCellValueFactory(new PropertyValueFactory<Kund, String>("ort"));
		TableColumn<Kund, String> seventhCol = new TableColumn<Kund, String>("Epost");
		seventhCol.setCellValueFactory(new PropertyValueFactory<Kund, String>("epost"));
		TableColumn<Kund, String> eightCol = new TableColumn<Kund, String>("Telefon");
		eightCol.setCellValueFactory(new PropertyValueFactory<Kund, String>("telefon"));
		
		Reports rep = new Reports();
		
		
		backButton.setOnAction(e->{
			Main.mainStage.setScene(Main.mainScene);
		});
		customerReportButton.setOnAction(e->{
			tableView.getColumns().clear();
			tableView.getItems().clear();
			Platform.runLater(()->{
				ObservableList<Kund> data = FXCollections.observableArrayList(rep.getCustomerReport());
				tableView.refresh();
				for (Kund kund : data) {
					System.out.println(kund);
				}

				tableView.setItems(data);
				tableView.getColumns().addAll(firstCol, secondCol, thirdCol, forthCol, fifthCol, sixthCol, seventhCol, eightCol );
			});
		
		});
		
	}

}
