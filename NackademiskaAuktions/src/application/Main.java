package application;
	
import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	
	public static Stage mainStage;
	public static Scene mainScene;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			mainStage = primaryStage;
			mainStage.setResizable(false);
			mainScene = firstScene();
			mainStage.setScene(mainScene);
			mainStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
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
}
