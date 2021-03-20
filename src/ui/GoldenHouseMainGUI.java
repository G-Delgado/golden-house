package ui;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
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
	private Button addSub;
	
	@FXML
	private Button editSub;
	
	@FXML
	private Button deleteSub;
	
	
	@FXML
	private VBox menu;
	
	// Ingredient
	@FXML
	private TextField ingredientTxt;
	
	// Type
	@FXML
	private TextField typeTxt;
	
	// Product
	
	@FXML
	private TextField productName;
	
	@FXML
	private TextField productSize;
	
	@FXML
	private TextField productPrice;
	
	@FXML
	private ComboBox<String> productType;
	
	@FXML
	private ListView<String> ingredientsList;
	
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
			e.printStackTrace();
		}
	}
	
	@FXML /* We do a loadAdd, therefore we should do a loadEdit and a loadDelete.
	// Nevertheless, it is probably better to do one method called
	loadSubMenu in which we apply the event.getSource() and check which is the button we are clicking
	So we can load 3 fxml with one method*/
	public void loadAdd(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Add.fxml"));
		fxmlLoader.setController(this);
		try {
			Parent add = fxmlLoader.load();
			menu.getChildren().clear();
			menu.getChildren().setAll(add);
			//mainPane.setCenter(ingredient);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void loadSubMenu(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(""));;
		if (event.getSource() == addSub) {
			fxmlLoader = new FXMLLoader(getClass().getResource("Add.fxml"));
		} else if (event.getSource() == editSub) {
			fxmlLoader = new FXMLLoader(getClass().getResource("Edit.fxml"));
		} else if (event.getSource() == deleteSub) {
			fxmlLoader = new FXMLLoader(getClass().getResource("Delete.fxml"));
		}
		
		fxmlLoader.setController(this);
		Parent subMenu;
		try {
			subMenu = fxmlLoader.load();
			menu.getChildren().clear();
			menu.getChildren().setAll(subMenu);
		} catch (IOException e) {
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
			e.printStackTrace();
		}
	}
	
	@FXML
	public void addIngredient(ActionEvent event) {
		String ingredientName = ingredientTxt.getText();
		gh.addIngredient(ingredientName, sessionUser);
		
	}
	
	@FXML
	public void loadProduct(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Product.fxml"));
		fxmlLoader.setController(this);
		try {
			Parent ingredient = fxmlLoader.load();
			ghPane.getChildren().clear();
			ghPane.getChildren().setAll(ingredient);
			//mainPane.setCenter(ingredient);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// -------- Setting things up --------- //
		
		ObservableList<String> options = FXCollections.observableArrayList();
		for (int i = 0; i < gh.getTypes().size(); i++) {
			options.add(gh.getTypes().get(i).getName());
		}
		productType.setItems(options);
		
		ObservableList<String> listOptions = FXCollections.observableArrayList();
		for (int i = 0; i < gh.getIngredients().size(); i++) {
			listOptions.add(gh.getIngredients().get(i).getName());
		}
		
		ingredientsList.setItems(listOptions);
		
		ingredientsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
	
	@FXML
	public void addProduct(ActionEvent event) {
		// Warning
		String name = productName.getText();
		String size = productSize.getText();
		double price = Double.parseDouble(productPrice.getText());
		String type = productType.getValue();
		ObservableList<String> igs = ingredientsList.getSelectionModel().getSelectedItems();
		ArrayList<String> arr = new ArrayList<>();
		for (String ig: igs) {
			arr.add(ig);
		}
		gh.addProduct(name, size, price, type, arr);
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
			e.printStackTrace();
		}
	}
	
	@FXML
	public void addType(ActionEvent event) {
		String typeName = typeTxt.getText();
		gh.addType(typeName, sessionUser);
	}
}
