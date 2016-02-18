package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Leverantor;
import application.Main;
import application.Produkt;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import model.RegisterAuktion;

public class NewAuctionC implements Initializable {

	@FXML
	ComboBox<Leverantor> supplierCombo;

	@FXML
	ComboBox<Produkt> productCombo;

	@FXML
	ComboBox<String> endTimeCombo, startTimeCombo;

	@FXML
	TextField startPriceField, acceptPriceField;

	@FXML
	Button submitButton, cancelButton;

	@FXML
	DatePicker fromDatePicker, toDatePicker;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		startPriceField.addEventFilter(KeyEvent.KEY_TYPED, numericValidation(10));
		acceptPriceField.addEventFilter(KeyEvent.KEY_TYPED, numericValidation(10));

		RegisterAuktion regA = new RegisterAuktion();
		
		ObservableList<String> hourOptions = FXCollections.observableArrayList("00", "01", "02", "03", "04", "05", "06",
				"07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
		endTimeCombo.setItems(hourOptions);
		startTimeCombo.setItems(hourOptions);
		ObservableList<Leverantor> levOptions = FXCollections.observableArrayList(regA.getLeverantorer());
		supplierCombo.setItems(levOptions);

		supplierCombo.setOnAction(e -> {
			ObservableList<Produkt> prodOptions = FXCollections.observableArrayList(
					regA.getLeverantorProdukt(supplierCombo.getSelectionModel().getSelectedItem()));
			productCombo.setItems(prodOptions);
			productCombo.setDisable(false);
		});
		submitButton.setOnAction(e -> {
			
			// check if field not null or empty
			if (endTimeCombo.getSelectionModel().getSelectedItem() != null) {
				boolean register = regA.registerToDatabase(productCombo.getSelectionModel().getSelectedItem(),
						Integer.parseInt(startPriceField.getText()), Integer.parseInt(acceptPriceField.getText()),
						fromDatePicker.getValue().toString(), toDatePicker.getValue().toString(),
						startTimeCombo.getSelectionModel().getSelectedItem(), endTimeCombo.getSelectionModel().getSelectedItem());
				String success = "Ny Auktion registrerad";
				String fail = "något gick fel, eller auktionen finns redan";
				if (register) {
					warningMessage(success);
					// kolla upp
					Platform.runLater(()->{
						ObservableList<Produkt> prodOptions = FXCollections.observableArrayList(
								regA.getLeverantorProdukt(supplierCombo.getSelectionModel().getSelectedItem()));
						productCombo.setItems(prodOptions);
						productCombo.setDisable(true);
					});
				} else {
					warningMessage(fail);
				}
			}
		});

		cancelButton.setOnAction(e -> {
			Main.mainStage.setScene(Main.mainScene);
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
				if (e.getCharacter().matches("[0-9.]")) {
					if (txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")) {
						e.consume();
					} else if (txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")) {
						e.consume();
					}
				} else {
					e.consume();
				}
			}
		};
	}

	private void warningMessage(String s) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(s);
		alert.showAndWait();
	}

}
