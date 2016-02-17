package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Leverantor;
import application.Produkt;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class NewAuctionC implements Initializable{
	
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
		// TODO Auto-generated method stub
		
	}

}
