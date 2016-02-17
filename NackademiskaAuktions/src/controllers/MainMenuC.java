package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class MainMenuC implements Initializable {

	@FXML
	Button registerButton, addAuctionButton, auctionListButton, quitButton, reportButton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Scene register = loadScene("Register.fxml");
		Scene auctions = loadScene("Auction.fxml");
		Scene createAuction = loadScene("NewAuction.fxml");
		Scene reports = loadScene("Report.fxml");
		
		registerButton.setOnAction(e->{
	    Main.mainStage.setScene(register);
		});
		
		addAuctionButton.setOnAction(e->{
			Main.mainStage.setScene(createAuction);
		});
		
		auctionListButton.setOnAction(e->{
			Main.mainStage.setScene(auctions);
		});
		
		reportButton.setOnAction(e->{
			Main.mainStage.setScene(reports);
		});
		
		quitButton.setOnAction(e->{
			Platform.exit();
		});
		
	}
	

	private Scene loadScene(String fileName){
		URL location = this.getClass().getResource("/view/"+fileName);
		FXMLLoader loader = new FXMLLoader(location);
		try {
			Parent root = loader.load();
			Scene scene = new Scene(root);
			return scene;
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
}
