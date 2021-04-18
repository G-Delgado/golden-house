package ui;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.GoldenHouse; // Podría ahorrarme el importar siempre si uso un extends para cada controller
import model.User;  // Podría ahorrarme el importar siempre si uso un extends para cada controller

public class EditGUI extends GoldenHouseMainGUI{  // Podría ahorrarme el importar siempre si uso un extends para cada controller
	
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
	private Button editEmployeeBtn;
	
	@FXML
	private Button editIngredientBtn;
	
	@FXML
	private Button editTypeBtn;
	
	// ------------------ New elements ----------------- //
	
	// Edit Product
	
	@FXML
	private ListView<String> productList;
	
	//
	
	private String oldProductName;
	
	@FXML
	private TextField editProductName;
	
	@FXML
	private TextField editProductSize;
	
	@FXML
	private TextField editProductPrice;
	
	@FXML
	private ComboBox<String> editProductType;
	
	@FXML
	private ListView<String> editIngredientsList;
	
	// Edit Client
	@FXML
	private ListView<String> clientList;
	
	private String oldClientName;
	
	@FXML
	private TextField editClientName;
	
	@FXML
	private TextField editClientLastName;
	
	@FXML
	private TextField editClientAddress;
	
	@FXML
	private TextField editClientPhone;
	
	@FXML
	private TextArea editClientObservations;
	
	// Edit Employee
	
	@FXML
	private TextField editEmployeeName;
	
	@FXML
	private TextField editEmployeeLastName;
	
	@FXML
	private String oldEmployeeName;
	
	@FXML
	private ListView<String> employeesList;
	
	// Edit Ingredient
	
	@FXML
	private ListView<String> ingredientList;
	
	@FXML
	private TextField editIngredientName;
	
	private String oldIngredientName;
	
	// Edit Type
	
	@FXML
	private ListView<String> typeList;
	
	@FXML
	private TextField editTypeName;
	
	private String oldTypeName;
	
	
	// Constructor --------------------
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
		} else if (event.getSource() == editEmployeeBtn) {
			fxmlLoader = new FXMLLoader(getClass().getResource("EditEmployee.fxml"));
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
					clients.add(gh.getClients().get(i).getName() + " " + gh.getClients().get(i).getLastName());
				}
				clientList.setItems(clients);
				clientList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
				
			} else if (event.getSource() == editEmployeeBtn) {
				ObservableList<String> employees = FXCollections.observableArrayList();
				for (int i = 0; i < gh.getEmployees().size(); i++) {
					employees.add(gh.getEmployees().get(i).getName() + " " + gh.getEmployees().get(i).getLastName());
				}
				employeesList.setItems(employees);
				employeesList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
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
		String prName = productList.getSelectionModel().getSelectedItem();
		oldProductName = prName;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditOneProduct.fxml"));
		fxmlLoader.setController(this);
		
		try {
			Parent edit = fxmlLoader.load();
			ghPane.getChildren().clear();
			ghPane.getChildren().setAll(edit);
			editProductName.setText(prName);
			editProductSize.setText(gh.getProductByName(prName).getSize());
			editProductPrice.setText(gh.getProductByName(prName).getPrice() + "");
			ObservableList<String> types = FXCollections.observableArrayList();
			String productType = gh.getProductByName(prName).getType().getName();
			for (int i = 0; i < gh.getTypes().size(); i++) {
				String typeName = gh.getTypes().get(i).getName();
				if (typeName != productType ) {
					types.add(gh.getTypes().get(i).getName());
				}
			}
			editProductType.setItems(types);
			if (types.size() == 1) {
				types.add(productType);
				editProductType.setItems(types);
			}
			editProductType.setValue(productType);
			ObservableList<String> ingredients = FXCollections.observableArrayList();
			for (int i = 0; i < gh.getIngredients().size(); i++) {
				ingredients.add(gh.getIngredients().get(i).getName());
			}
			editIngredientsList.setItems(ingredients);
			editIngredientsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void finishProductEdit(ActionEvent event) {
		String name = editProductName.getText();
		String size = editProductSize.getText();
		double price = Double.parseDouble(editProductPrice.getText());
		String type = editProductType.getValue();
		ObservableList<String> igs = editIngredientsList.getSelectionModel().getSelectedItems();
		ArrayList<String> arr = new ArrayList<>();
		for (String ig: igs) {
			arr.add(ig);
		}
		gh.editProduct(oldProductName, name, size, price, type, arr);
		try {
			gh.saveProducts();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ghPane.getChildren().clear();
	}
	
	@FXML
	public void editClient(ActionEvent event) {
		String clName = clientList.getSelectionModel().getSelectedItem();
		oldClientName = clName;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditOneClient.fxml"));
		fxmlLoader.setController(this);
		
		try {
			Parent edit = fxmlLoader.load();
			ghPane.getChildren().clear();
			ghPane.getChildren().setAll(edit);
			editClientName.setText(clName.split(" ")[0]);
			editClientLastName.setText(gh.getClientByName(clName).getLastName());
			editClientAddress.setText(gh.getClientByName(clName).getAddress());
			editClientPhone.setText(gh.getClientByName(clName).getPhoneNumber());
			editClientObservations.setText(gh.getClientByName(clName).getObservations());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void finishClientEdit(ActionEvent event) {
		String name = editClientName.getText();
		String lastName = editClientLastName.getText();
		String address = editClientAddress.getText();
		String phone = editClientPhone.getText();
		String observations = editClientObservations.getText();
		
		gh.editClient(oldClientName, name, lastName, address, phone, observations);
		try {
			gh.saveClients();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText("Cliente editado!");
		alert.setTitle("Info!");
		alert.setContentText("El cliente se ha editado exitosamente");
		alert.showAndWait();
		//editClient(event);
		// Lo que se debería hacer, es o devolverlo a alguna parte
		// O vaciar los campos de texto
		// O cambiar el campo editado
		// De todas formas, es necesario un alert con la edición exitosa!
	}
	
	@FXML
	public void editEmployee(ActionEvent event) {
		String em = employeesList.getSelectionModel().getSelectedItem();
		oldEmployeeName = em;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditOneEmployee.fxml"));
		fxmlLoader.setController(this);
		
		try {
			Parent edit = fxmlLoader.load();
			ghPane.getChildren().clear();
			ghPane.getChildren().setAll(edit);
			editEmployeeName.setText(em.split(" ")[0]);
			editEmployeeLastName.setText(gh.getEmployeeByName(em).getLastName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void finishEmployeeEdit(ActionEvent event) {
		String name = editEmployeeName.getText();
		String lastName = editEmployeeLastName.getText();
		
		gh.editEmployee(oldEmployeeName, name, lastName);
		
		try {
			gh.saveEmployees();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void editIngredient(ActionEvent event) {
		String ig = ingredientList.getSelectionModel().getSelectedItem();
		oldIngredientName = ig;
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditOneIngredient.fxml"));
		fxmlLoader.setController(this);
		
		try {
			Parent edit = fxmlLoader.load();
			ghPane.getChildren().clear();
			ghPane.getChildren().setAll(edit);
			editIngredientName.setText(ig);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void finishIngredientEdit(ActionEvent event) {
		String name = editIngredientName.getText();
		gh.editIngredient(oldIngredientName, name, sessionUser);
		try {
			gh.saveIngredients();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//editIngredientName.setText("");
		editIngredient(event);
	}
	
	@FXML
	public void editType(ActionEvent event) {
		String ty = typeList.getSelectionModel().getSelectedItem();
		oldTypeName = ty;
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditOneType.fxml"));
		fxmlLoader.setController(this);
		
		try {
			Parent edit = fxmlLoader.load();
			ghPane.getChildren().clear();
			ghPane.getChildren().setAll(edit);
			editTypeName.setText(ty);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void finishTypeEdit(ActionEvent event) {
		String name = editTypeName.getText();
		gh.editType(oldTypeName, name, sessionUser);
		try {
			gh.saveTypes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		editType(event);
	}
	
	
}
