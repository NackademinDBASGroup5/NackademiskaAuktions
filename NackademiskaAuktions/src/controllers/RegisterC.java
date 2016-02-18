package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Kund;
import application.Leverantor;
import application.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.RegisterStuff;

public class RegisterC implements Initializable {

	@FXML
	TextField socialSecField, firstNameField, lastNameField, adressField, zipcodeField, cityField, emailField,
			phonenumberField, orgNumberField, supplierNameField, provPercentField, productNameField;

	@FXML
	Button registerButton_customer, backButton_customer, registerButton_supplier, backButton_supplier, backButton_product, registerButton_product;

	@FXML
	TextArea productDescriptArea;
	
	@FXML ComboBox<Leverantor> supplierCombo;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		RegisterStuff rKund = new RegisterStuff();

		backButton_customer.setOnAction(e -> {
			Main.mainStage.setScene(Main.mainScene);
		});

		backButton_supplier.setOnAction(e -> {
			Main.mainStage.setScene(Main.mainScene);
		});
		socialSecField.addEventFilter(KeyEvent.KEY_TYPED , onlyNumericValidation(12));
		zipcodeField.addEventFilter(KeyEvent.KEY_TYPED , onlyNumericValidation(5));
		phonenumberField.addEventFilter(KeyEvent.KEY_TYPED , bindNummerValidation(15));
		// check if field not null or empty
		registerButton_customer.setOnAction(e -> {
			Kund kund = new Kund(socialSecField.getText(), firstNameField.getText(), lastNameField.getText(),
					adressField.getText(), zipcodeField.getText(), cityField.getText(), emailField.getText(),
					phonenumberField.getText());
			boolean registered = rKund.registerToDatabase(kund);
			String success = "Ny kund registrerad";
			String fail = "något gick fel, eller kunden finns redan";
			if (registered){
				warningMessage(success);
			}else{
				warningMessage(fail);
			}
		});
		orgNumberField.addEventFilter(KeyEvent.KEY_TYPED , bindNummerValidation(25));
		provPercentField.addEventFilter(KeyEvent.KEY_TYPED , numericValidation(5));
		// check if field not null or empty
		registerButton_supplier.setOnAction(e->{
			Leverantor lev = new Leverantor(orgNumberField.getText(), supplierNameField.getText(), Float.parseFloat(provPercentField.getText()));
			boolean registered = rKund.registerLeverantorToDatabase(lev);
			String success = "Ny Leverantör registrerad";
			String fail = "något gick fel, eller Leverantören finns redan";
			if (registered){
				warningMessage(success);
			}else{
				warningMessage(fail);
			}
		});
	}
	public EventHandler<KeyEvent> numericValidation(final Integer max_Lengh) {
	    return new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent e) {
	            TextField txt_TextField = (TextField) e.getSource();                
	            if (txt_TextField.getText().length() >= max_Lengh) {                    
	                e.consume();
	            }
	            if(e.getCharacter().matches("[0-9.]")){ 
	                if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
	                    e.consume();
	                }else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
	                    e.consume(); 
	                }
	            }else{
	                e.consume();
	            }
	        }
	    };
	}   
	public EventHandler<KeyEvent> bindNummerValidation(final Integer max_Lengh) {
	    return new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent e) {
	            TextField txt_TextField = (TextField) e.getSource();                
	            if (txt_TextField.getText().length() >= max_Lengh) {                    
	                e.consume();
	            }
	            if(e.getCharacter().matches("[0-9-]")){ 
	                if(txt_TextField.getText().contains("-") && e.getCharacter().matches("[-]")){
	                    e.consume();
	                }else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[-]")){
	                    e.consume(); 
	                }
	            }else{
	                e.consume();
	            }
	        }
	    };
	}   
	public EventHandler<KeyEvent> onlyNumericValidation(final Integer max_Lengh) {
	    return new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent e) {
	            TextField txt_TextField = (TextField) e.getSource();                
	            if (txt_TextField.getText().length() >= max_Lengh) {                    
	                e.consume();
	            }
	            if(e.getCharacter().matches("[0-9]")){ 
	                if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
	                    e.consume();
	                }else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
	                    e.consume(); 
	                }
	            }else{
	                e.consume();
	            }
	        }
	    };
	}   
	private void warningMessage(String s){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(s);
		alert.showAndWait();
	}

}
