package ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
//import javafx.stage.Stage;
import model.GoldenHouse;

public class GoldenHouseGUI {
	
	private GoldenHouse gh;
	
	@FXML
	private BorderPane mainPane;
	
	public GoldenHouseGUI(GoldenHouse gh) {
		this.gh = gh;
	}
	
	public void loadLogin() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
		fxmlLoader.setController(this);
		Parent login;
		login = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.getChildren().setAll(login);
		// mainPane.setCenter(login);

		/*Stage stage = (Stage) mainPane.getScene().getWindow();
		stage.setHeight(400);
		stage.setWidth(544);*/
		
	}
	
	public void loadRegister() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Register.fxml"));
		fxmlLoader.setController(this);
		Parent login;
		login = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.getChildren().setAll(login);
		// mainPane.setCenter(login);
		
	}
	
	public void loadRecover() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Recover.fxml"));
		fxmlLoader.setController(this);
		Parent login;
		login = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.getChildren().setAll(login);
		// mainPane.setCenter(login);
		
	}
}
