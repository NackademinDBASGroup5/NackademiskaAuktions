package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Leverantor;
import application.Main;
import application.Produkt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.RegisterAuktion;

public class NewAuctionC implements Initializable {

	@FXML
	ComboBox<Leverantor> supplierCombo;

	@FXML
	ComboBox<Produkt> productCombo;

	@FXML
	ComboBox<String> endTimeCombo;

	@FXML
	TextField startPriceField, acceptPriceField;

	@FXML
	Button submitButton, cancelButton;

	@FXML
	DatePicker fromDatePicker, toDatePicker;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		RegisterAuktion regA = new RegisterAuktion();
		ObservableList<Leverantor> levOptions = FXCollections.observableArrayList(regA.getLeverantorer());
		supplierCombo.setItems(levOptions);
		
		supplierCombo.setOnAction(e->{
			ObservableList<Produkt> prodOptions = FXCollections.observableArrayList(regA.getLeverantorProdukt(supplierCombo.getSelectionModel().getSelectedItem()));
			productCombo.setItems(prodOptions);
			productCombo.setDisable(false);
		});
		
		
		
		cancelButton.setOnAction(e -> {
			Main.mainStage.setScene(Main.mainScene);
		});

	}

}
