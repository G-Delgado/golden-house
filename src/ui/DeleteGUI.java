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
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.GoldenHouse;
import model.User;

public class DeleteGUI extends GoldenHouseMainGUI {
	// General
	
	private GoldenHouse gh;
		
	//private User sessionUser; // Check this warning
		
	// Panes
		
	//private BorderPane mainPane; // Check this warning
		
	private StackPane ghPane;
	
	private ResourceBundle rb;
	
	// Delete
	
	// Product
	@FXML
	private ListView<String> deleteProductList;
	
	// Client
	
	@FXML
	private ListView<String> deleteClientList;
	
	
	// Employee
	@FXML
	private ListView<String> deleteEmployeeList;
	
	// Ingredient
	
	@FXML
	private ListView<String> deleteIngredientList;
	
	// Type
	
	@FXML
	private ListView<String> deleteTypeList;
	
	
	public DeleteGUI(GoldenHouse gh, User sessionUser, BorderPane mainPane, StackPane ghPane, ResourceBundle rb) {
		super(gh, sessionUser.getUsername(), sessionUser.getPassword(), mainPane, rb);
		this.gh = gh;
		//this.sessionUser = sessionUser;
		//this.mainPane = mainPane;
		this.ghPane = ghPane;
		this.setRb(rb);
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
		if (!gh.productIsUsed(pr)) {
			gh.deleteProduct(pr);
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setContentText("El producto ha sido eliminado correctamente!");
			alert.showAndWait();
			try {
				gh.saveProducts();
				//loadDeleteProduct(event);
			} catch (IOException e) {
				e.printStackTrace();
			}
			ghPane.getChildren().clear();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("El producto est� siendo utilizado!");
			alert.showAndWait();
		}
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
				options.add(gh.getClients().get(i).getName() + " " + gh.getClients().get(i).getLastName());
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
		String[] clArr = cl.split(" ");
		gh.deleteClient(clArr[0], clArr[1]);
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setContentText("El cliente ha sido eliminado correctamente!");
		alert.showAndWait();
		try {
			gh.saveClients();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//loadDeleteClient(event);
		ghPane.getChildren().clear();
	}
	
	@FXML
	public void loadDeleteEmployee(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteEmployee.fxml"));
		fxmlLoader.setController(this);
		
		try {
			Parent delete = fxmlLoader.load();
			ghPane.getChildren().clear();
			ghPane.getChildren().setAll(delete);
			
			ObservableList<String> options = FXCollections.observableArrayList();
			for (int i = 0; i < gh.getEmployees().size(); i++) {
				options.add(gh.getEmployees().get(i).getName() + " " + gh.getEmployees().get(i).getLastName());
			}
			deleteEmployeeList.setItems(options);
			deleteEmployeeList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	public void finishEmployeeDelete(ActionEvent event) {
		String em = deleteEmployeeList.getSelectionModel().getSelectedItem();
		gh.deleteEmployee(em);
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setContentText("El emplead ha sido eliminado correctamente!");
		alert.showAndWait();
		try {
			gh.saveEmployees();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//loadDeleteEmployee(event);
		ghPane.getChildren().clear();
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
		if (!gh.ingredientIsUsed(ig)) {			
			gh.deleteIngredient(ig);
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setContentText("El ingrediente ha sido eliminado correctamente!");
			alert.showAndWait();
			try {
				gh.saveIngredients();
				//loadDeleteIngredient(event);
			} catch (IOException e) {
				e.printStackTrace();
			}
			ghPane.getChildren().clear();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("El ingrediente est� siendo utilizado!");
			alert.showAndWait();
		}
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
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setContentText("El tipo de producto ha sido eliminado correctamente!");
		alert.showAndWait();
		try {
			gh.saveTypes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//loadDeleteType(event);
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
