package ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.GoldenHouse;
import model.User;

public class DeleteGUI extends GoldenHouseMainGUI {
	// General
	
	private GoldenHouse gh;
		
	private User sessionUser;
		
	// Panes
		
	private BorderPane mainPane;
		
	private StackPane ghPane;
	
	// Delete
	
	// Product
	@FXML
	private ListView<String> deleteProductList;
	
	// Client
	
	@FXML
	private ListView<String> deleteClientList;
	
	// Ingredient
	
	@FXML
	private ListView<String> deleteIngredientList;
	
	// Type
	
	@FXML
	private ListView<String> deleteTypeList;
	
	
	public DeleteGUI(GoldenHouse gh, User sessionUser, BorderPane mainPane, StackPane ghPane) {
		super(gh, sessionUser.getUsername(), sessionUser.getPassword(), mainPane);
		this.gh = gh;
		this.sessionUser = sessionUser;
		this.mainPane = mainPane;
		this.ghPane = ghPane;
	}
	
	@FXML
	public void loadDeleteProduct(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteProduct.fxml"));
		fxmlLoader.setController(this);
		
		try {
			Parent delete = fxmlLoader.load();
			ghPane.getChildren().clear();
			ghPane.getChildren().setAll(delete);
			
			ObservableList<String> options = FXCollections.observableArrayList();
			for (int i = 0; i < gh.getProducts().size(); i++) {
				options.add(gh.getProducts().get(i).getName());
			}
			deleteProductList.setItems(options);
			deleteProductList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void finishProductDelete(ActionEvent event) {
		String pr = deleteProductList.getSelectionModel().getSelectedItem();
		gh.deleteProduct(pr);
		try {
			gh.saveProducts();
		} catch (IOException e) {
			e.printStackTrace();
		}
		loadDeleteProduct(event);
	}
	
	@FXML
	public void loadDeleteClient(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteClient.fxml"));
		fxmlLoader.setController(this);
		
		try {
			Parent delete = fxmlLoader.load();
			ghPane.getChildren().clear();
			ghPane.getChildren().setAll(delete);
			
			ObservableList<String> options = FXCollections.observableArrayList();
			for (int i = 0; i < gh.getClients().size(); i++) {
				options.add(gh.getClients().get(i).getName());
			}
			deleteClientList.setItems(options);
			deleteClientList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void finishClientDelete(ActionEvent event) {
		String cl = deleteClientList.getSelectionModel().getSelectedItem();
		gh.deleteClient(cl);
		try {
			gh.saveClients();
		} catch (IOException e) {
			e.printStackTrace();
		}
		loadDeleteClient(event);
	}
	
	@FXML
	public void loadDeleteIngredient(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteIngredient.fxml"));
		fxmlLoader.setController(this);
		
		try {
			Parent delete = fxmlLoader.load();
			ghPane.getChildren().clear();
			ghPane.getChildren().setAll(delete);
			
			ObservableList<String> options = FXCollections.observableArrayList();
			for (int i = 0; i < gh.getIngredients().size(); i++) {
				options.add(gh.getIngredients().get(i).getName());
			}
			deleteIngredientList.setItems(options);
			deleteIngredientList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void finishIngredientDelete(ActionEvent event) {
		String ig = deleteIngredientList.getSelectionModel().getSelectedItem();
		gh.deleteIngredient(ig);
		try {
			gh.saveIngredients();
		} catch (IOException e) {
			e.printStackTrace();
		}
		loadDeleteIngredient(event);
	}
	
	@FXML
	public void loadDeleteType(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteType.fxml"));
		fxmlLoader.setController(this);
		
		try {
			Parent delete = fxmlLoader.load();
			ghPane.getChildren().clear();
			ghPane.getChildren().setAll(delete);
			
			ObservableList<String> options = FXCollections.observableArrayList();
			for (int i = 0; i < gh.getTypes().size(); i++) {
				options.add(gh.getTypes().get(i).getName());
			}
			deleteTypeList.setItems(options);
			deleteTypeList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void finishTypeDelete(ActionEvent event) {
		String ty = deleteTypeList.getSelectionModel().getSelectedItem();
		gh.deleteType(ty);
		try {
			gh.saveTypes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadDeleteType(event);
	}
	
}
