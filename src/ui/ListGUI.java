package ui;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.Client;
import model.Employee;
import model.GoldenHouse;
import model.Ingredient;
import model.Product;
import model.Type;
import model.User;

public class ListGUI extends GoldenHouseMainGUI{
	
	@FXML
	private GoldenHouse gh;
	
	@FXML
	private StackPane ghPane;
	
	private ResourceBundle rb;
	
	// List
	
	@FXML
	private Button listProductBtn;
	
	@FXML
	private Button listEmployeeBtn;
	
	@FXML
	private Button listClientBtn;
	
	@FXML
	private Button listIngredientBtn;
	
	@FXML
	private Button listTypeBtn;
	
	@FXML
	private Button listUserBtn;
	
	// Product
	
	@FXML
	private TableView<Product> productTable;
	
	@FXML
	private TableColumn<Product, String> productNameColumn;
	
	@FXML
	private TableColumn<Product, String> productSizeColumn;
	
	@FXML
	private TableColumn<Product, String> productPriceColumn;
	
	// Employee
	
	@FXML
	private TableView<Employee> employeeTable;
	
	@FXML
	private TableColumn<Employee, String> employeeNameColumn;
	
	@FXML
	private TableColumn<Employee, String> employeeLastNameColumn;
	
	@FXML
	private TableColumn<Employee, String> employeeIdColumn;
	
	// Client
	
	@FXML
	private TableView<Client> clientTable;
	
	@FXML
	private TableColumn<Client, String> clientNameColumn;
	
	@FXML
	private TableColumn<Client, String> clientLastNameColumn;
	
	@FXML
	private TableColumn<Client, String> clientIdColumn;
	
	@FXML
	private TableColumn<Client, String> clientAddressColumn;
	
	@FXML
	private TableColumn<Client, String> clientPhoneColumn;
	
	// Ingredient
	
	@FXML
	private TableView<Ingredient> ingredientTable;
	
	@FXML
	private TableColumn<Ingredient, String> ingredientNameColumn;
	
	@FXML
	private TableColumn<Ingredient, String> ingredientCreatedByColumn;
	
	@FXML
	private TableColumn<Ingredient, String> ingredientModifiedByColumn;
	
	// Type
	
	@FXML
	private TableView<Type> typeTable;
	
	@FXML
	private TableColumn<Type, String> typeNameColumn;
	
	@FXML
	private TableColumn<Type, String> typeCreatedByColumn;
	
	@FXML
	private TableColumn<Type, String> typeModifiedByColumn;
	
	// User
	
	@FXML
	private TableView<User> userTable;
	
	@FXML
	private TableColumn<User, String> userNameColumn;
	
	@FXML
	private TableColumn<User, String> userLastNameColumn;
	
	@FXML
	private TableColumn<User, String> userUsernameColumn;
	
	@FXML
	private TableColumn<User, String> userIdColumn;

	
	// ---------------
	
	public ListGUI(GoldenHouse goldenHouse, String username, String password, BorderPane mp, StackPane ghPane, ResourceBundle rb) {
		super(goldenHouse, username, password, mp, rb);
		this.gh = goldenHouse;
		this.ghPane = ghPane;
		this.setRb(rb);
	}
	
	@FXML
	public void loadList(ActionEvent event) {
		FXMLLoader fxmlLoader = null;
		if (event.getSource() == listProductBtn) {
			fxmlLoader = new FXMLLoader(getClass().getResource("ProductList.fxml"));
			fxmlLoader.setController(this);
		} else if (event.getSource() == listEmployeeBtn) {
			fxmlLoader = new FXMLLoader(getClass().getResource("EmployeeList.fxml"));
			fxmlLoader.setController(this);
		} else if (event.getSource() == listClientBtn) {
			fxmlLoader = new FXMLLoader(getClass().getResource("ClientList.fxml"));
			fxmlLoader.setController(this);
		} else if (event.getSource() == listIngredientBtn) {
			fxmlLoader = new FXMLLoader(getClass().getResource("IngredientList.fxml"));
			fxmlLoader.setController(this);
		} else if (event.getSource() == listTypeBtn) {
			fxmlLoader = new FXMLLoader(getClass().getResource("TypeList.fxml"));
			fxmlLoader.setController(this);
		} else if (event.getSource() == listUserBtn) {
			fxmlLoader = new FXMLLoader(getClass().getResource("UserList.fxml"));
			fxmlLoader.setController(this);
		}
		
		Parent list;
		try {
			list = fxmlLoader.load();
			ghPane.getChildren().clear();
			ghPane.getChildren().setAll(list);
			if (event.getSource() == listProductBtn) {				
				initializeProductTable();				
			} else if (event.getSource() == listEmployeeBtn) {
				initializeEmployeeTable();
			} else if (event.getSource() == listClientBtn) {
				initializeClientTable();
			} else if (event.getSource() == listIngredientBtn) {
				initializeIngredientTable();
			} else if (event.getSource() == listTypeBtn) {
				initializeTypeTable();
			} else if (event.getSource() == listUserBtn) {
				initializeUserTable();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initializeProductTable() { // Deberia intentar cambiar los valores de talbeView y tableColumn
		// De esa forma no tendr?a que relacionar m?s al modelo.
		// Tambi?n podr?a quitar el goldenHouse y solo usar m?todos que lo llamen.
		ObservableList<Product> products = FXCollections.observableArrayList(gh.getProducts());
		
		productTable.setItems(products);
		productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		productSizeColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("size"));
		productPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
		
	}
	
	public void initializeEmployeeTable() {
		gh.sortEmployees();
		ObservableList<Employee> employees = FXCollections.observableArrayList(gh.getEmployees());
		
		employeeTable.setItems(employees);
		employeeNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
		employeeLastNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
		employeeIdColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("id"));
	}
	
	public void initializeClientTable() {
		ObservableList<Client> clients = FXCollections.observableArrayList(gh.getClients());
		
		clientTable.setItems(clients);
		clientNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
		clientLastNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
		clientIdColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("id"));
		clientAddressColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("address"));
		clientPhoneColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("phoneNumber"));
		
	}
	
	public void initializeIngredientTable() {
		gh.sortIngredients();
		ObservableList<Ingredient> ingredients = FXCollections.observableArrayList(gh.getIngredients());
		
		ingredientTable.setItems(ingredients);
		ingredientNameColumn.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("name"));
		ingredientCreatedByColumn.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("createdBy"));
		ingredientModifiedByColumn.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("lastModifiedBy"));
	}
	
	/*@FXML // This way I can handle that
	public void handleClick(MouseEvent event) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				String nameStuff = ingredientTable.getSelectionModel().getSelectedItem().getName();
				System.out.println("Double clicked!");
				System.out.println(nameStuff);
			}
		}
	}*/

	
	public void initializeTypeTable() {
		gh.sortTypes();
		ObservableList<Type> types = FXCollections.observableArrayList(gh.getTypes());
		
		typeTable.setItems(types);
		typeNameColumn.setCellValueFactory(new PropertyValueFactory<Type, String>("name"));
		typeCreatedByColumn.setCellValueFactory(new PropertyValueFactory<Type, String>("createdBy"));
		typeModifiedByColumn.setCellValueFactory(new PropertyValueFactory<Type, String>("lastModifiedBy"));
	}
	
	public void initializeUserTable( ) {
		gh.sortUsers();
		ObservableList<User> users = FXCollections.observableArrayList(gh.getUsers());
		
		userTable.setItems(users);
		userNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
		userLastNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
		userUsernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		userIdColumn.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
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
