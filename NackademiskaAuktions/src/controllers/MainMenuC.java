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
	//Scene register = loadScene("Register.fxml");
	//Scene auctions = loadScene("Auction.fxml");
	//Scene createAuction = loadScene("NewAuction.fxml");
	//Scene reports = loadScene("Report.fxml");

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		registerButton.setOnAction(e -> {
			Main.mainStage.setScene(loadScene("Register.fxml"));
		});

		addAuctionButton.setOnAction(e -> {
			Main.mainStage.setScene(loadScene("NewAuction.fxml"));
		});

		auctionListButton.setOnAction(e -> {
			Main.mainStage.setScene(loadScene("Auction.fxml"));
		});

		reportButton.setOnAction(e -> {
			Main.mainStage.setScene(loadScene("Report.fxml"));
		});

		quitButton.setOnAction(e -> {
			Platform.exit();
		});

	}

	private Scene loadScene(String fileName) {
		URL location = this.getClass().getResource("/view/" + fileName);
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
