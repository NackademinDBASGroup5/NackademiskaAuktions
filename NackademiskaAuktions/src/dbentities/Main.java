
package dbentities;
	
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
import javafx.stage.StageStyle;
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
		if (login(0));{
		try {
			
			mainStage = primaryStage;
			mainStage.setResizable(false);
			mainStage.setTitle("Nackademiska auktionsn�mnden");
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
			
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private boolean login(int i){
		i++;
		if (i >3)
			System.exit(0);
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Login");
		dialog.setHeaderText("Logga in");
		
		ButtonType loginButtonType = new ButtonType("Logga in", ButtonData.OK_DONE);
		ButtonType exitButtonType = new ButtonType("Avsluta", ButtonData.YES);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, exitButtonType);
		dialog.initStyle(StageStyle.UNDECORATED);
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField username = new TextField();
		username.setPromptText("Anv�ndarnamn");
		PasswordField password = new PasswordField();
		password.setPromptText("L�senord");
		

		grid.add(new Label("Anv�ndarnamn:"), 0, 0);
		grid.add(username, 1, 0);
		grid.add(new Label("L�senord:"), 0, 1);
		grid.add(password, 1, 1);
		

		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);
		username.textProperty().addListener((observable, oldValue, newValue) -> {
		    loginButton.setDisable(newValue.trim().isEmpty());
		});
		Node exitButton = dialog.getDialogPane().lookupButton(exitButtonType);
		exitButton.setOnMouseClicked(e->{
			System.exit(0);
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
			{	warningMessage("Felaktigt anv�ndarnamn/l�senord. du har " + (3-i) + " f�rs�k kvar");
				login(i);
				//System.exit(0);
			}
		return false;
	}
	
	private boolean access(String user, String pass){
		Connection connect = null;
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost/auktion", user, pass);
		} catch (SQLException e) {
			
			return false;
		}
		try {
			if (connect != null)
			connect.close();
		} catch (SQLException e) {
			
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
