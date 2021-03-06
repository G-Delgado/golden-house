package ui;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.GoldenHouse;

public class EnableDisableGUI extends GoldenHouseMainGUI{
	
	@FXML
	private GoldenHouse gh;
	
	@FXML
	private StackPane ghPane;
	
	private ResourceBundle rb;
	
	// Menu enableDisable buttons
	@FXML
	private Button productBtn;
	
	@FXML
	private Button employeeBtn;

	@FXML
	private Button clientBtn;
	
	@FXML
	private Button ingredientsBtn;
	
	@FXML
	private Button typesBtn;
	
	// Enable / Disable
	
	@FXML
	private Button enable;
	
	@FXML
	private Button disable;
	
	// Products
	
	@FXML
	private ListView<String> enableDisableProductList;
	
	// Employees
	@FXML
	private ListView<String> enableDisableEmployeeList;
	
	// Clients
	@FXML
	private ListView<String> enableDisableClientList;
	// Ingredients
	@FXML
	private ListView<String> enableDisableIngredientList;
	// Types
	@FXML
	private ListView<String> enableDisableTypeList;
	 
	// ------- Constructor --------- //
	public EnableDisableGUI(GoldenHouse goldenHouse, String username, String password, BorderPane mp, StackPane ghPane, ResourceBundle rb) {
		super(goldenHouse, username, password, mp, rb);
		this.ghPane = ghPane;
		this.gh = goldenHouse;
		this.setRb(rb);
	}
	
	@FXML
	public void loadEnableDisable(ActionEvent event) {
		FXMLLoader fxmlLoader = null;
		if (event.getSource() == productBtn) {
			fxmlLoader = new FXMLLoader(getClass().getResource("EnableDisableProduct.fxml"));
		} else if (event.getSource() == employeeBtn) {
			fxmlLoader = new FXMLLoader(getClass().getResource("EnableDisableEmployee.fxml"));
		} else if (event.getSource() == clientBtn) {
			fxmlLoader = new FXMLLoader(getClass().getResource("EnableDisableClient.fxml"));
		} else if (event.getSource() == ingredientsBtn) {
			fxmlLoader = new FXMLLoader(getClass().getResource("EnableDisableIngredient.fxml"));
		} else if (event.getSource() == typesBtn) {
			fxmlLoader = new FXMLLoader(getClass().getResource("EnableDisableType.fxml"));
		}
		fxmlLoader.setController(this);
		if (fxmlLoader != null) {
			Parent enableDisable;
			try {
				enableDisable = fxmlLoader.load();
				ghPane.getChildren().clear();
				ghPane.getChildren().setAll(enableDisable);
				
				if (event.getSource() == productBtn) {
					ObservableList<String> products = FXCollections.observableArrayList();
					for (int i = 0; i < gh.getProducts().size(); i++) {
						products.add(gh.getProducts().get(i).getName());
					}
					enableDisableProductList.setItems(products);
					enableDisableProductList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
					
				} else if (event.getSource() == employeeBtn) {
					ObservableList<String> employees = FXCollections.observableArrayList();
					for (int i = 0; i < gh.getEmployees().size(); i++) {
						employees.add(gh.getEmployees().get(i).getName());
					}
					enableDisableEmployeeList.setItems(employees);
					enableDisableEmployeeList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
				} else if (event.getSource() == clientBtn) {
					ObservableList<String> clients = FXCollections.observableArrayList();
					for (int i = 0; i < gh.getClients().size(); i++) {
						clients.add(gh.getClients().get(i).getName());
					}
					enableDisableClientList.setItems(clients);
					enableDisableClientList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
				} else if (event.getSource() == ingredientsBtn) {
					ObservableList<String> ingredients = FXCollections.observableArrayList();
					for (int i = 0; i < gh.getIngredients().size(); i++) {
						ingredients.add(gh.getIngredients().get(i).getName());
					}
					enableDisableIngredientList.setItems(ingredients);
					enableDisableIngredientList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
				} else if (event.getSource() == typesBtn) {
					ObservableList<String> types = FXCollections.observableArrayList();
					for (int i = 0; i < gh.getTypes().size(); i++) {
						types.add(gh.getTypes().get(i).getName());
					}
					enableDisableTypeList.setItems(types);
					enableDisableTypeList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * 
	 * DAR ALGUN MENSAJE PARA DAR A ENTENDER QUE FUE HABILITADO O DESHABILITADO.
	 * 
	 * */

	@FXML
	public void enableDisableProduct(ActionEvent event) {
		String prName = enableDisableProductList.getSelectionModel().getSelectedItem();
		if (event.getSource() == enable) {
			gh.enableDisableProduct(prName, true);		
		} else if (event.getSource() == disable){
			gh.enableDisableProduct(prName, false);	
		}
		try {
			gh.saveProducts();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText("El producto ha sido editado!");
		alert.setContentText("El producto se ha editado exitosamente");
		alert.showAndWait();
		ghPane.getChildren().clear();
	}
	
	@FXML
	public void enableDisableEmployee(ActionEvent event) {
		String emName = enableDisableEmployeeList.getSelectionModel().getSelectedItem();
		if (event.getSource() == enable) {
			gh.enableDisableEmployee(emName, true);
		} else if (event.getSource() == disable){
			gh.enableDisableEmployee(emName, false);
		}
		try {
			gh.saveEmployees();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText("El empleado ha sido editado!");
		alert.setContentText("El empleado se ha editado exitosamente");
		alert.showAndWait();
		ghPane.getChildren().clear();
	}
	
	@FXML
	public void enableDisableClient(ActionEvent event) {
		String clName = enableDisableClientList.getSelectionModel().getSelectedItem();
		if (event.getSource() == enable) {
			gh.enableDisableClient(clName, true);
		} else if (event.getSource() == disable){
			gh.enableDisableClient(clName, false);
		}
		try {
			gh.saveClients();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText("El cliente ha sido editado!");
		alert.setContentText("El cliente se ha editado exitosamente");
		alert.showAndWait();
		ghPane.getChildren().clear();
	}
	
	@FXML
	public void enableDisableIngredient(ActionEvent event) {
		String igName = enableDisableIngredientList.getSelectionModel().getSelectedItem();
		if (event.getSource() == enable) {
			gh.enableDisableIngredient(igName, true);
		} else if (event.getSource() == disable){
			gh.enableDisableIngredient(igName, false);
		}
		try {
			gh.saveIngredients();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText("El ingrediente ha sido editado!");
		alert.setContentText("El ingrediente se ha editado exitosamente");
		alert.showAndWait();
		ghPane.getChildren().clear();
	}
	
	@FXML
	public void enableDisableType(ActionEvent event) {
		String tyName = enableDisableTypeList.getSelectionModel().getSelectedItem();
		if (event.getSource() == enable) {
			gh.enableDisableType(tyName, true);
		} else if (event.getSource() == disable){
			gh.enableDisableType(tyName, false);
		}
		try {
			gh.saveTypes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText("El tipo de producto ha sido editado!");
		alert.setContentText("El tipo de producto se ha editado exitosamente");
		alert.showAndWait();
		ghPane.getChildren().clear();
	}

	/**
	 * @return the rb
	 */
	public ResourceBundle getRb() {
		return rb;
	}

	/**
	 * @param rb the rb to set
	 */
	public void setRb(ResourceBundle rb) {
		this.rb = rb;
	}
	
	
	
}
