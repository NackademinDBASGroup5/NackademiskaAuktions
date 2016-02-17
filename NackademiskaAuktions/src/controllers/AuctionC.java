package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;

public class AuctionC implements Initializable {
	
	@FXML 
	DatePicker fromDatePicker_curr, toDatePicker_curr,fromDatePicker_hist, toDatePicker_hist;

	@FXML
	Button listButton_curr, listAllButton_curr, listButton_hist, listAllButton_hist;
	
	@FXML
	TableView<String> tableView_curr, tableView_hist;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
