package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
	
	@FXML
	private VBox menu;
	
	// Ingredient
	@FXML
	private TextField ingredientTxt;
	
	// Type
	@FXML
	private TextField typeTxt;
	
	public GoldenHouseMainGUI(GoldenHouse goldenHouse, String username, String password, BorderPane mp) {
		gh = goldenHouse;
		int pos = gh.isUser(username, password);
		this.sessionUser = gh.getUsers().get(pos);
		mainPane = mp;
		System.out.println(sessionUser.toString());
	}
	
	@FXML
	public void loadMenu(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GH.fxml"));
		fxmlLoader.setController(this);
		try {
			Parent main = fxmlLoader.load();
			mainPane.getChildren().clear();
			mainPane.setCenter(main);
			//mainPane.setCenter(ingredient);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void loadAdd(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Add.fxml"));
		fxmlLoader.setController(this);
		try {
			Parent add = fxmlLoader.load();
			menu.getChildren().clear();
			menu.getChildren().setAll(add);
			//mainPane.setCenter(ingredient);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	@FXML
	public void loadType(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Type.fxml"));
		fxmlLoader.setController(this);
		try {
			Parent type = fxmlLoader.load();
			ghPane.getChildren().clear();
			ghPane.getChildren().setAll(type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void addType(ActionEvent event) {
		String typeName = typeTxt.getText();
		gh.addType(typeName, sessionUser);
	}
}
