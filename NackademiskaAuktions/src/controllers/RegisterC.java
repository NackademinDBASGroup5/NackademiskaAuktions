package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegisterC implements Initializable {

	@FXML 
	TextField socialSecField, firstNameField, lastNameField,
	adressField, zipcodeField, cityField, emailField, phonenumberField, orgNumberField,
	supplierNameField, provPercentField;
	
	@FXML
	Button registerButton_customer, backButton_customer, registerButton_supplier, backButton_supplier;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
