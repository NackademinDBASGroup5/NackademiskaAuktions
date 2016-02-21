
package application;
	

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;



public class Main extends Application {
	
	public static Stage mainStage;
	public static Scene mainScene;
	public static String username;
	public static String password;
	
	@Override
	public void start(Stage primaryStage) {
		if (login());{
		try {
			
			mainStage = primaryStage;
			mainStage.setResizable(false);
			mainStage.setTitle("Nackademiska auktionsnämnden");
			mainStage.getIcons().add(new Image("/images/icon.png"));
			mainScene = firstScene();
			mainStage.setScene(mainScene);
			mainStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		}
	}
	
	private Scene firstScene() {

		 URL location = this.getClass().getResource("/view/Meny.fxml");

		FXMLLoader loader = new FXMLLoader(location);
		try {
			Parent root = loader.load();
			Scene scene = new Scene(root);
			return scene;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private boolean login(){
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Login");
		dialog.setHeaderText("");
		
		ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField username = new TextField();
		username.setPromptText("Username");
		PasswordField password = new PasswordField();
		password.setPromptText("Password");

		grid.add(new Label("Username:"), 0, 0);
		grid.add(username, 1, 0);
		grid.add(new Label("Password:"), 0, 1);
		grid.add(password, 1, 1);

		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);
		username.textProperty().addListener((observable, oldValue, newValue) -> {
		    loginButton.setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);

		Platform.runLater(() -> username.requestFocus());
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == loginButtonType) {
		        return new Pair<>(username.getText(), password.getText());
		    }
		    return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();

		result.ifPresent(usernamePassword -> {
		
				 Main.username = usernamePassword.getKey();
				    Main.password = usernamePassword.getValue();
		
		});
		if (access(Main.username, Main.password)){
			return true;
		    
			}
			else
			{	warningMessage("Felaktigt användarnamn/lösenord.");
				login();
				//System.exit(0);
			}
		return false;
	}
	
	private boolean access(String user, String pass){
		Connection connect = null;
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost/auktion", user, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		try {
			if (connect != null)
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	private void warningMessage(String s) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(s);
		alert.showAndWait();
	}
}
