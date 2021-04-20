package ui;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.GoldenHouse;  // Podría ahorrarme el importar siempre si uso un extends para cada controller
import model.User; // Podría ahorrarme el importar siempre si uso un extends para cada controller

public class GoldenHouseMainGUI extends GoldenHouseGUI {  // Podría ahorrarme el importar siempre si uso un extends para cada controller
	 
	private GoldenHouse gh;
	
	private User sessionUser;
	
	private OrderGUI ghOrderGUI;
	
	private AddGUI ghAddGUI;
	
	private EditGUI ghEditGUI;
	
	private ListGUI ghListGUI;
	
	private DeleteGUI ghDeleteGUI;
	
	private ReportGUI ghReportGUI;
	
	private EnableDisableGUI ghEnableDisable;
	
	private ResourceBundle rb;
	
	// Time
	@FXML
	private Label timeLabel;
	
	// Main Pane
	@FXML
	private BorderPane mainPane;
	
	// GH
	@FXML
	private StackPane ghPane;
	
	@FXML
	private Button orderSub;
	
	@FXML
	private Button addSub;
	
	@FXML
	private Button editSub;
	
	@FXML
	private Button listSub;
	
	@FXML
	private Button enableDisable;
	
	@FXML
	private Button deleteSub;
	
	@FXML
	private Button reportSub;
	
	
	@FXML
	private VBox menu;
	

	
	
	public GoldenHouseMainGUI(GoldenHouse goldenHouse, String username, String password, BorderPane mp, ResourceBundle rb) {
		super(goldenHouse, mp);
		gh = goldenHouse;
		int pos = gh.isUser(username, password);
		this.sessionUser = gh.getUsers().get(pos);
		mainPane = mp;
		this.rb = rb;
		//System.out.println(sessionUser.toString());
	}
	
	@FXML
	public void loadImport(ActionEvent event) {
		gh.importData(sessionUser);
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText("Datos importados!");
		alert.setContentText("Se han importado los datos de testeo para:\nIngredientes, Productos, Clientes y Pedidos!");
		alert.showAndWait();
	}
	
	public User getSessionUser() {
		return sessionUser;
	}
	
	public BorderPane getMainPane() {
		return mainPane;
	}
	
	public GoldenHouse getGH() {
		return gh;
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
	public void loadAdd(ActionEvent event) { // I probably dont need this anymore
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
		if (event.getSource() == orderSub) {
			fxmlLoader = new FXMLLoader(getClass().getResource("OrderMenu.fxml"));
			ghOrderGUI = new OrderGUI(gh, sessionUser.getUsername(), sessionUser.getPassword(), mainPane, ghPane, rb);
			fxmlLoader.setController(ghOrderGUI);
		} else if (event.getSource() == addSub) {
			fxmlLoader = new FXMLLoader(getClass().getResource("Add.fxml"));
			ghAddGUI = new AddGUI(gh, sessionUser, mainPane, ghPane, rb);
			fxmlLoader.setController(ghAddGUI);
		} else if (event.getSource() == editSub) {
			fxmlLoader = new FXMLLoader(getClass().getResource("Edit.fxml"));
			ghEditGUI = new EditGUI(gh, sessionUser, mainPane, ghPane, rb);
			fxmlLoader.setController(ghEditGUI);
		} else if (event.getSource() == listSub) {
			fxmlLoader = new FXMLLoader(getClass().getResource("List.fxml"));
			ghListGUI = new ListGUI(gh, sessionUser.getUsername(), sessionUser.getPassword(), mainPane, ghPane, rb);
			fxmlLoader.setController(ghListGUI);
		} else if (event.getSource() == enableDisable) {
			fxmlLoader = new FXMLLoader(getClass().getResource("EnableDisable.fxml"));
			ghEnableDisable = new EnableDisableGUI(gh, sessionUser.getUsername(), sessionUser.getPassword(), mainPane, ghPane, rb);
			fxmlLoader.setController(ghEnableDisable);
		} else if (event.getSource() == deleteSub) {
			fxmlLoader = new FXMLLoader(getClass().getResource("Delete.fxml"));
			ghDeleteGUI = new DeleteGUI(gh, sessionUser, mainPane, ghPane, rb);
			fxmlLoader.setController(ghDeleteGUI);
		} else if (event.getSource() == reportSub) {
			fxmlLoader = new FXMLLoader(getClass().getResource("Reports/Reports.fxml"));
			ghReportGUI = new ReportGUI(gh, sessionUser, mainPane, ghPane, rb);
			fxmlLoader.setController(ghReportGUI);
		}
		
		Parent subMenu;
		try {
			subMenu = fxmlLoader.load();
			menu.getChildren().clear();
			menu.getChildren().setAll(subMenu);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	
}
