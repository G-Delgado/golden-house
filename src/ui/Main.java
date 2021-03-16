package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	
	private GoldenHouseGUI ghGUI;
	
	public Main() {
		// Put the GUI and the controller class in the model
		ghGUI = new GoldenHouseGUI();
		
		// We can also load the data
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(""));
		fxmlLoader.setController(ghGUI);
		
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("");
		primaryStage.show();
		
		// I can handle the load and the close of the app in here
		// ---
		
	}

}
