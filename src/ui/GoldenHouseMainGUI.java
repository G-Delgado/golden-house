package ui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.GoldenHouse;  // Podría ahorrarme el importar siempre si uso un extends para cada controller
import model.User; // Podría ahorrarme el importar siempre si uso un extends para cada controller

public class GoldenHouseMainGUI extends GoldenHouseGUI{  // Podría ahorrarme el importar siempre si uso un extends para cada controller
	 
	private GoldenHouse gh;
	
	private User sessionUser;
	
	private AddGUI ghAddGUI;
	
	private EditGUI ghEditGUI;
	
	private ListGUI ghListGUI;
	
	private DeleteGUI ghDeleteGUI;
	
	private EnableDisableGUI ghEnableDisable;
	
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
	private Button listSub;
	
	@FXML
	private Button enableDisable;
	
	@FXML
	private Button deleteSub;
	
	
	@FXML
	private VBox menu;
	

	
	
	public GoldenHouseMainGUI(GoldenHouse goldenHouse, String username, String password, BorderPane mp) {
		super(goldenHouse, mp);
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
		if (event.getSource() == addSub) {
			fxmlLoader = new FXMLLoader(getClass().getResource("Add.fxml"));
			ghAddGUI = new AddGUI(gh, sessionUser, mainPane, ghPane);
			fxmlLoader.setController(ghAddGUI);
		} else if (event.getSource() == editSub) {
			fxmlLoader = new FXMLLoader(getClass().getResource("Edit.fxml"));
			ghEditGUI = new EditGUI(gh, sessionUser, mainPane, ghPane);
			fxmlLoader.setController(ghEditGUI);
		} else if (event.getSource() == listSub) {
			fxmlLoader = new FXMLLoader(getClass().getResource("List.fxml"));
			ghListGUI = new ListGUI(gh, sessionUser.getUsername(), sessionUser.getPassword(), mainPane, ghPane);
			fxmlLoader.setController(ghListGUI);
		} else if (event.getSource() == enableDisable) {
			fxmlLoader = new FXMLLoader(getClass().getResource("EnableDisable.fxml"));
			ghEnableDisable = new EnableDisableGUI(gh, sessionUser.getUsername(), sessionUser.getPassword(), mainPane, ghPane);
			fxmlLoader.setController(ghEnableDisable);
		} else if (event.getSource() == deleteSub) {
			fxmlLoader = new FXMLLoader(getClass().getResource("Delete.fxml"));
			ghDeleteGUI = new DeleteGUI(gh, sessionUser, mainPane, ghPane);
			fxmlLoader.setController(ghDeleteGUI);
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
