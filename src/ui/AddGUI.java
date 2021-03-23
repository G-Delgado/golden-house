package ui;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.GoldenHouse;
import model.User;

public class AddGUI extends GoldenHouseMainGUI{	
	// General
	
	private GoldenHouse gh;
	
	private User sessionUser;
	
	// Panes
	
	private BorderPane mainPane; // Not used in the add option
	
	private StackPane ghPane;
	
	
	// Add Menu -----------//
	
	// Employee
	@FXML
	private TextField employeeName;
	
	@FXML
	private TextField employeeLastName;
	
	// Client
	@FXML
	private TextField clientName;
		
	@FXML
	private TextField clientLastName;
		
	@FXML
	private TextField clientAddress;
		
	@FXML
	private TextField clientPhone;
		
	@FXML
	private TextArea clientObservations;
		
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
	
	public AddGUI(GoldenHouse gh, User sessionUser, BorderPane mainPane, StackPane ghPane) {
		super(gh, sessionUser.getUsername(), sessionUser.getPassword(), mainPane);
		this.gh = gh;
		this.sessionUser = sessionUser;
		this.mainPane = mainPane;
		this.ghPane = ghPane;
	}
	
	@FXML
	public void loadClient(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Client.fxml"));
		fxmlLoader.setController(this);
		try {
			Parent client = fxmlLoader.load();
			ghPane.getChildren().clear();
			ghPane.getChildren().setAll(client);
			//mainPane.setCenter(ingredient);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void addClient(ActionEvent event) {
		if (!clientName.getText().equals("") && !clientLastName.getText().equals("") && !clientAddress.getText().equals("") && !clientPhone.getText().equals("")) {			
			int num = (int)(Math.random() * 100000);
			String id = clientName.getText().substring(0,2) + clientLastName.getText().substring(0,2) + num;
			gh.addClient(clientName.getText(), clientLastName.getText(), id, clientAddress.getText(), clientPhone.getText(), clientObservations.getText());
			try {
				gh.saveClients();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// Didn't add the observations in the conditional as they shouldn't be mandatory
		} else {
			
		}
	}
	
	@FXML
	public void loadEmployee(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Employee.fxml"));
		fxmlLoader.setController(this);
		try {
			Parent employee = fxmlLoader.load();
			ghPane.getChildren().clear();
			ghPane.getChildren().setAll(employee);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void addEmployee(ActionEvent event) {
		if (!employeeName.getText().equals("") && !employeeLastName.getText().equals("")) {
			int num = (int)(Math.random() * 100000);
			String id = employeeName.getText().substring(0,2) + employeeLastName.getText().substring(0,2) + num;
			gh.addEmployee(employeeName.getText(), employeeLastName.getText(), id);
			try {
				gh.saveEmployees();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			
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
		try {
			gh.saveIngredients();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
			if (gh.getIngredients().get(i).isEnabled()) {				
				listOptions.add(gh.getIngredients().get(i).getName());
			}
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
		try {
			gh.saveProducts();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		try {
			gh.saveTypes();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
