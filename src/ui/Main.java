package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.GoldenHouse;

public class Main extends Application{
	
	private GoldenHouseGUI ghGUI;
	private GoldenHouse gh;
	
	public Main() {
		// Put the GUI and the controller class in the model
		gh = new GoldenHouse();
		ghGUI = new GoldenHouseGUI(gh);
		
		// We can also load the data
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPane.fxml"));
		fxmlLoader.setController(ghGUI);
		
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Golden House User Interface");
		primaryStage.show();
		
		// I can handle the load and the close of the app in here
		// ---
		
		ghGUI.loadLogin();
		
	}

}
