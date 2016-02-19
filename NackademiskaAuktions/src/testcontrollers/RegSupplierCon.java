package testcontrollers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Kund;
import application.Leverantor;
import application.Main;
import application.Produkt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.RegisterAuktion;
import model.RegisterStuff;

public class RegSupplierCon implements Initializable {

	@FXML
	TextField orgNumberField, supplierNameField, provPercentField, productNameField;

	@FXML
	Button  registerButton_supplier;

	
	@FXML ComboBox<Leverantor> supplierCombo;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		TextField[] suppFields = {orgNumberField, supplierNameField, provPercentField};
		
		RegisterStuff rKund = new RegisterStuff();
		
		orgNumberField.addEventFilter(KeyEvent.KEY_TYPED , onlyNumericValidation(10));
		provPercentField.addEventFilter(KeyEvent.KEY_TYPED , onlyNumericValidation(2));
		// check if field not null or empty
		registerButton_supplier.setOnAction(e->{
			
			if(isFilledSupplier(suppFields)){
				Leverantor lev = new Leverantor(orgNumberField.getText(), supplierNameField.getText(), Float.parseFloat(provPercentField.getText()));
				boolean registered = rKund.registerLeverantorToDatabase(lev);
				String success = "Ny Leverantör registrerad";
				String fail = "något gick fel, eller Leverantören finns redan";
				if (registered){
					warningMessage(success);
				}else{
					warningMessage(fail);
				}
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
	

	
private boolean isFilledSupplier(TextField[] fields){
		
		for(TextField tf: fields){
			if(tf.getText().equals("")){
				String fail = "Var god fyll i alla fält";
				warningMessage(fail);
				return false;
			}
			
		}
		if(fields[0].getText().length()<10){
			String fail = "Organisationsnummer ska matas in med tio siffror UTAN bindesstreck";
			warningMessage(fail);
			return false;
		}
		
		return true;
	}

}
