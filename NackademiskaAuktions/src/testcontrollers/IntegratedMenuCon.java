
package testcontrollers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
	Button regKundButton, regProdButton, regLevButton, addAuctionButton, auctionListButton, auctionListNEWButton,
			auctionListBidsButton, reportButton, customerBoughtButton, provisionButton, ongoingAuctButton,
			finishedAuctButton, quitButton;

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
			borderpane.setCenter(loadPane("registreraAuktion.fxml"));
		});

		auctionListButton.setOnAction(e -> {
			borderpane.setCenter(loadPane("listAuctions.fxml"));
		});

		auctionListBidsButton.setOnAction(e -> {
			borderpane.setCenter(loadPane("listBids.fxml"));
		});

		reportButton.setOnAction(e -> {
			borderpane.setCenter(loadPane("Reports.fxml"));
		});

		customerBoughtButton.setOnAction(e -> {
			borderpane.setCenter(loadPane("ReportCustomerSpent.fxml"));
		});

		provisionButton.setOnAction(e -> {
			borderpane.setCenter(loadPane("ReportProvision.fxml"));
		});

		quitButton.setOnAction(e -> {
			Platform.exit();
		});

	}

	private Pane loadPane(String fileName) {
		URL location = this.getClass().getResource("/testview/" + fileName);
		FXMLLoader loader = new FXMLLoader(location);
		try {
			Pane pane = loader.load();
			return pane;
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}

}
