package controllers;

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

public class ReportC implements Initializable {

	@FXML 
	Button customerReportButton, provisionPerMonthButton, backButton;
	
	@FXML
	TableView tableView;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		TableColumn<KundHistorik, String> firstCol = new TableColumn<KundHistorik, String>("Personnummer");
		firstCol.setCellValueFactory(new PropertyValueFactory<KundHistorik, String>("personnummer"));
		TableColumn<KundHistorik, String> secondCol = new TableColumn<KundHistorik, String>("Förnamn");
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
		
		TableColumn<Provision, String> provisionCol = new TableColumn<Provision, String>("Månad");
		provisionCol.setCellValueFactory(new PropertyValueFactory<Provision, String>("manad"));
		TableColumn<Provision, String> provisionCol2 = new TableColumn<Provision, String>("Provision");
		provisionCol2.setCellValueFactory(new PropertyValueFactory<Provision, String>("provision"));
		
		Reports rep = new Reports();
		
		backButton.setOnAction(e->{
			Main.mainStage.setScene(Main.mainScene);
		});
		customerReportButton.setOnAction(e->{
			
			tableView.getColumns().clear();
			tableView.getItems().clear();
			Platform.runLater(()->{
				ObservableList<KundHistorik> data = FXCollections.observableArrayList(rep.getCustomerReport());
				tableView.refresh();

				tableView.setItems(data);
				tableView.getColumns().addAll(firstCol, secondCol, thirdCol, forthCol, fifthCol, sixthCol, seventhCol, eightCol, ninthCol );
			});
		
		});
		
		provisionPerMonthButton.setOnAction(e->{
			tableView.getColumns().clear();
			tableView.getItems().clear();
			Platform.runLater(()->{
				ObservableList<Provision> data = FXCollections.observableArrayList(rep.getProvisionReport());
				tableView.refresh();

				tableView.setItems(data);
				tableView.getColumns().addAll(provisionCol, provisionCol2);
			});
		
		});
		
	}
	
	

}
