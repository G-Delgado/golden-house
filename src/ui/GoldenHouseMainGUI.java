package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import model.GoldenHouse;

public class GoldenHouseMainGUI {
	
	private GoldenHouse gh;
	
	// Main Pane
	@FXML
	private BorderPane mainPane;
	
	public GoldenHouseMainGUI(GoldenHouse goldenHouse, BorderPane mp) {
		gh = goldenHouse;
		mainPane = mp;
	}
	
	@FXML
	public void loadIngredient(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ingredient.fxml"));
		fxmlLoader.setController(this);
		try {
			Parent ingredient = fxmlLoader.load();
			mainPane.getChildren().clear();
			mainPane.setCenter(ingredient);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void addIngredient(ActionEvent event) {
		gh.addIngredient();
	}
}
