package ui;

import java.io.IOException;

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
		ghGUI = new GoldenHouseGUI(gh, null);
		
		// We can also load the data
		try {
			// Client and user are not working
			gh.loadProducts();
			gh.loadClients();
			gh.loadEmployees();
			gh.loadIngredients();
			gh.loadTypes();
			gh.loadUsers();
			gh.loadOrders();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
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
		//Mirscene.getStylesheets().add("../src/ui/styles.css");
		primaryStage.setScene(scene);
		primaryStage.setTitle("Golden House User Interface");
		primaryStage.show();
		
		// I can handle the load and the close of the app in here
		// ---
		
		// Just for tagging
		primaryStage.setHeight(460);
		ghGUI.loadLogin();
		
	}

}
