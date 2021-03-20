package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.GoldenHouse;
import model.User;

public class GoldenHouseMainGUI {
	
	private GoldenHouse gh;
	
	private User sessionUser;
	
	// Main Pane
	@FXML
	private BorderPane mainPane;
	
	// GH
	@FXML
	private StackPane ghPane;
	
	// Ingredient
	@FXML
	private TextField ingredientTxt;
	
	public GoldenHouseMainGUI(GoldenHouse goldenHouse, String username, String password, BorderPane mp) {
		gh = goldenHouse;
		int pos = gh.isUser(username, password);
		this.sessionUser = gh.getUsers().get(pos);
		mainPane = mp;
		System.out.println(sessionUser.toString());
	}
	
	@FXML
	public void loadIngredient(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ingredient.fxml"));
		fxmlLoader.setController(this);
		try {
			Parent ingredient = fxmlLoader.load();
			ghPane.getChildren().clear();
			ghPane.getChildren().setAll(ingredient);
			//mainPane.setCenter(ingredient);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void addIngredient(ActionEvent event) {
		String ingredientName = ingredientTxt.getText();
		gh.addIngredient(ingredientName, sessionUser);
		
	}
}
