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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.RegisterStuff;

public class RegisterC implements Initializable {

	@FXML
	TextField socialSecField, firstNameField, lastNameField, adressField, zipcodeField, cityField, emailField,
			phonenumberField, orgNumberField, supplierNameField, provPercentField;

	@FXML
	Button registerButton_customer, backButton_customer, registerButton_supplier, backButton_supplier;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		RegisterStuff rKund = new RegisterStuff();

		backButton_customer.setOnAction(e -> {
			Main.mainStage.setScene(Main.mainScene);
		});

		backButton_supplier.setOnAction(e -> {
			Main.mainStage.setScene(Main.mainScene);
		});
		registerButton_customer.setOnAction(e -> {
			Kund kund = new Kund(socialSecField.getText(), firstNameField.getText(), lastNameField.getText(),
					adressField.getText(), zipcodeField.getText(), cityField.getText(), emailField.getText(),
					phonenumberField.getText());
			boolean registered = rKund.registerToDatabase(kund);
			String success = "Ny kund registrerad";
			String fail = "något gick fel, eller kunden finns redan";
			if (registered){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(success);
				alert.showAndWait();
			}else{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(fail);
				alert.showAndWait();
			}
		});
		provPercentField.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(10));
		
		registerButton_supplier.setOnAction(e->{
			Leverantor lev = new Leverantor(orgNumberField.getText(), supplierNameField.getText(), Float.parseFloat(provPercentField.getText()));
			boolean registered = rKund.registerLeverantorToDatabase(lev);
			String success = "Ny Leverantör registrerad";
			String fail = "något gick fel, eller Leverantören finns redan";
			if (registered){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(success);
				alert.showAndWait();
			}else{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(fail);
				alert.showAndWait();
			}
		});
	}
	public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
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

}
