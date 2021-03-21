package ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.GoldenHouse; // Podr�a ahorrarme el importar siempre si uso un extends para cada controller
import model.User;  // Podr�a ahorrarme el importar siempre si uso un extends para cada controller

public class EditGUI extends GoldenHouseMainGUI{  // Podr�a ahorrarme el importar siempre si uso un extends para cada controller
	
	// Model
	
	private GoldenHouse gh;
	
	private User sessionUser;
	
	
	// Main pane
	
	@FXML
	private BorderPane mainPane;
	
	// Gh Main Pane
	
	@FXML
	private StackPane ghPane;
	

	@FXML
	private Button editProductBtn;
	
	@FXML
	private Button editClientBtn;
	
	@FXML
	private Button editIngredientBtn;
	
	@FXML
	private Button editTypeBtn;
	
	// ------------------ New elements ----------------- //
	
	// Edit Product
	
	@FXML
	private ListView<String> productList;
	
	// Edit Client
	
	@FXML
	private ListView<String> clientList;
	
	// Edit Ingredient
	
	@FXML
	private ListView<String> ingredientList;
	
	// Edit Type
	
	@FXML
	private ListView<String> typeList;
	
	public EditGUI(GoldenHouse gh, User sessionUser, BorderPane mainPane, StackPane ghPane) {
		super(gh,sessionUser.getUsername(), sessionUser.getPassword(), mainPane);
		this.gh = gh;
		this.sessionUser = sessionUser;
		this.mainPane = mainPane;
		this.ghPane = ghPane;
	}
	
	@FXML
	public void loadEdit(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(""));
		if (event.getSource() == editProductBtn) {
			fxmlLoader = new FXMLLoader(getClass().getResource("EditProduct.fxml"));	
		} else if (event.getSource() == editClientBtn) {
			fxmlLoader = new FXMLLoader(getClass().getResource("EditClient.fxml"));
		} else if (event.getSource() == editIngredientBtn) {
			fxmlLoader = new FXMLLoader(getClass().getResource("EditIngredient.fxml"));
		} else if (event.getSource() == editTypeBtn) {
			fxmlLoader = new FXMLLoader(getClass().getResource("EditType.fxml"));
		}
		
		fxmlLoader.setController(this);
		Parent edit;
		try {
			edit = fxmlLoader.load();
			ghPane.getChildren().clear();
			ghPane.getChildren().setAll(edit);
			if (event.getSource() == editProductBtn) {
				ObservableList<String> products = FXCollections.observableArrayList();
				for (int i = 0; i < gh.getProducts().size(); i++) {
					products.add(gh.getProducts().get(i).getName());
				}
				productList.setItems(products);
				productList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			} else if (event.getSource() == editClientBtn) {
				ObservableList<String> clients = FXCollections.observableArrayList();
				for (int i = 0; i < gh.getClients().size(); i++) {
					clients.add(gh.getClients().get(i).getName());
				}
				clientList.setItems(clients);
				clientList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
				
			} else if (event.getSource() == editIngredientBtn) {
				ObservableList<String> ingredients = FXCollections.observableArrayList();
				for (int i = 0; i < gh.getIngredients().size(); i++) {
					ingredients.add(gh.getIngredients().get(i).getName());
				}
				ingredientList.setItems(ingredients);
				ingredientList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
				
			} else if (event.getSource() == editTypeBtn) {
				ObservableList<String> types = FXCollections.observableArrayList();
				for (int i = 0; i < gh.getTypes().size(); i++) {
					types.add(gh.getTypes().get(i).getName());
				}
				typeList.setItems(types);
				typeList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void editProduct(ActionEvent event) {
		productList.getSelectionModel().getSelectedItem();
	}
	
	@FXML
	public void editClient(ActionEvent event) {
		
	}
	
	@FXML
	public void editIngredient(ActionEvent event) {
		
	}
	
	@FXML
	public void editType(ActionEvent event) {
		
	}
	
	
}
