package ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.GoldenHouse;
import model.Order;

public class OrderGUI extends GoldenHouseMainGUI{
	
	private GoldenHouse gh;
	
	private StackPane ghPane;
	
	// Order menu
	
	// Add order
	
	private ArrayList<String[]> productsList;
	
	@FXML
	private ComboBox<String> clientField;
	
	@FXML
	private ComboBox<String> employeeField;
	
	@FXML
	private ComboBox<String> productField;
	
	@FXML
	private TextField productQuantityField;
	
	@FXML
	private TextArea observationsField;
	
	// Modify State
	
	@FXML
	TableView<Order> orderTable;
	
	@FXML
	TableColumn<Order, String> tcCode;
	
	@FXML
	TableColumn<Order, String> tcState;
	
	@FXML
	TableColumn<Order, String> tcCName;
	
	@FXML
	TableColumn<Order, String> tcEName;
	
	@FXML
	TableColumn<Order, String> tcObservations;
	
	@FXML
	private Button inProcessBtn;
	
	@FXML
	private Button sentBtn;
	
	@FXML
	private Button deliveredBtn;
	
	// Edit order

	public OrderGUI(GoldenHouse goldenHouse, String username, String password, BorderPane mp, StackPane ghPane) {
		super(goldenHouse, username, password, mp);
		gh = goldenHouse;
		this.ghPane = ghPane;
	}
	
	@FXML
	public void addOrder(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Order.fxml"));
		fxmlLoader.setController(this);
		
		try {
			Parent addOrder = fxmlLoader.load();
			ghPane.getChildren().clear();
			ghPane.getChildren().setAll(addOrder);
			productsList = new ArrayList<>();
			
			// ------- Setting things ups -------- //
			ObservableList<String> clients = FXCollections.observableArrayList();
			for (int i = 0; i < gh.getClients().size(); i++) {
				clients.add(gh.getClients().get(i).getName() + " " + gh.getClients().get(i).getLastName());
			}
			clientField.setItems(clients);
			
			ObservableList<String> employees = FXCollections.observableArrayList();
			for (int i = 0; i < gh.getEmployees().size(); i++) {
				employees.add(gh.getEmployees().get(i).getName() + " " + gh.getEmployees().get(i).getLastName());
			}
			employeeField.setItems(employees);
			
			ObservableList<String> products = FXCollections.observableArrayList();
			for (int i = 0; i < gh.getProducts().size(); i++) {
				products.add(gh.getProducts().get(i).getName());
			}
			productField.setItems(products);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	public void addProductToOrder(ActionEvent event) {
		System.out.println("Im inside then");
		String pr = productField.getSelectionModel().getSelectedItem();
		String quantity = productQuantityField.getText();
		
		String[] product = {pr, quantity};
		System.out.println("In GUI: " + Arrays.toString(product));
		productsList.add(product);
	}
	
	@FXML
	public void finishAddOrder(ActionEvent event) {
		String cl = clientField.getSelectionModel().getSelectedItem();
		String em = employeeField.getSelectionModel().getSelectedItem();
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		String observations = observationsField.getText();
		
		gh.addOrder(cl, em, productsList, date, time, observations, getSessionUser());
		try {
			gh.saveOrders();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initializeTableView() {
		ObservableList<Order> orders = FXCollections.observableArrayList(gh.getOrders());
		
		orderTable.setItems(orders);
		tcCode.setCellValueFactory(new PropertyValueFactory<Order, String>("code"));
		tcState.setCellValueFactory(new PropertyValueFactory<Order, String>("state"));
		tcCName.setCellValueFactory(new PropertyValueFactory<Order, String>("client"));
		tcEName.setCellValueFactory(new PropertyValueFactory<Order, String>("employee"));
		tcObservations.setCellValueFactory(new PropertyValueFactory<Order, String>("observations"));
	}
	
	@FXML
	public void modifyState(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderState.fxml"));
		fxmlLoader.setController(this);
		
		try {
			Parent state = fxmlLoader.load();
			ghPane.getChildren().clear();
			ghPane.getChildren().setAll(state);
			initializeTableView();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	public void finishModifyState(ActionEvent event) {
		String code = orderTable.getSelectionModel().getSelectedItem().getCode();
		boolean changed = false;
		if (event.getSource() == inProcessBtn) {
			changed = gh.changeStateOfOrder(code, 1);
		} else if (event.getSource() == sentBtn) {
			changed = gh.changeStateOfOrder(code, 2);
		} else if (event.getSource() == deliveredBtn) {
			changed = gh.changeStateOfOrder(code, 3);
		}
		if (changed) {
			try {
				gh.saveOrders();
			} catch (IOException e) {
				e.printStackTrace();
			}
			modifyState(event);
		} else {			
			// Warning Label in here
			System.out.println("Couldn't update the state");
		}
	}
	
	@FXML
	public void editOrder(ActionEvent event) {
		
	}

}
