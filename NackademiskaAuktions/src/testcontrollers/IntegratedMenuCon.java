
package testcontrollers;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class IntegratedMenuCon implements Initializable {

	@FXML
	private BorderPane borderpane;

	@FXML
	Button regKundButton, regProdButton, regLevButton,addAuctionButton,
	auctionListButton, quitButton, reportButton,
	ongoingAuctButton,finishedAuctButton;

	// @FXML
	// Button registerButton, addAuctionButton, auctionListButton, quitButton,
	// reportButton;
	// Pane register = Pane("Register.fxml");
	// Scene auctions = loadScene("Auction.fxml");
	// Scene createAuction = loadScene("NewAuction.fxml");
	// Scene reports = loadScene("Report.fxml");

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		regKundButton.setOnAction(e -> {
			borderpane.setCenter(loadPane("registreraKund.fxml"));
		});

		regProdButton.setOnAction(e -> {
			borderpane.setCenter(loadPane("registreraProdukt.fxml"));
		});

		regLevButton.setOnAction(e -> {
			borderpane.setCenter(loadPane("registreraLeverantor.fxml"));
		});

		addAuctionButton.setOnAction(e -> {
			// Main.mainStage.setScene(loadScene("NewAuction.fxml"));
			borderpane.setCenter(loadPane("registreraAuktion.fxml"));
		});
/*
		auctionListButton.setOnAction(e -> {
			// Main.mainStage.setScene(loadScene("Auction.fxml"));
			borderpane.setCenter(loadPane("NewAuction.fxml"));
		});*/

		auctionListButton.setOnAction(e -> {
			// Main.mainStage.setScene(loadScene("Report.fxml"));
			borderpane.setCenter(loadPane("listAuctions.fxml"));
		});
		
		reportButton.setOnAction(e -> {
			// Main.mainStage.setScene(loadScene("Report.fxml"));
			borderpane.setCenter(loadPane("Reports.fxml"));
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

	private Pane loadPane(String fileName) {
		URL location = this.getClass().getResource("/testview/" + fileName);
		FXMLLoader loader = new FXMLLoader(location);
		try {
			// Parent root = loader.load();
			// Scene scene = new Scene(root);
			Pane pane = loader.load();
			return pane;
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}

}
