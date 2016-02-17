package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class ReportC implements Initializable {

	@FXML 
	Button customerReportButton, provisionPerMonthButton, backButton;
	
	@FXML
	TableView<String> tableView;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
