package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
//import javafx.stage.Stage;
import model.GoldenHouse;

public class GoldenHouseGUI {
	
	private GoldenHouse gh;
	
	// Main Pane ---
	
	@FXML
	private BorderPane mainPane;
	
	// Login ---
	@FXML
	private TextField loginUsername;
	
	@FXML
	private TextField loginPassword;
	
	// Register ---
	@FXML
	private TextField registerName;
	
	@FXML
	private TextField registerLastName;
	
	@FXML
	private TextField registerUsername;
	
	@FXML
	private TextField registerPassword;
	
	
	// Recover ---
	
	public GoldenHouseGUI(GoldenHouse gh) {
		this.gh = gh;
	}
	
	@FXML
	public void loadLogin() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
		fxmlLoader.setController(this);
		Parent login;
		login = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.getChildren().setAll(login);
		// mainPane.setCenter(login);

		/*Stage stage = (Stage) mainPane.getScene().getWindow();
		stage.setHeight(400);
		stage.setWidth(544);*/
		
	}
	
	@FXML
	public void login(ActionEvent event) {
		if (!loginUsername.getText().equals("") && !loginPassword.getText().equals("")) {
			System.out.println("Fua, bien hecho!");
			if (gh.isUser(loginUsername.getText(), loginPassword.getText())) {
				try {
					loadMain();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@FXML
	public void loadMain() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GH.fxml"));
		fxmlLoader.setController(this); // For the moment, but it probably needs another controller
		Parent GoldenHouse = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(GoldenHouse);
	}
	
	@FXML
	public void loadRegister(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Register.fxml"));
		fxmlLoader.setController(this);
		Parent login;
		login = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.getChildren().setAll(login);
		// mainPane.setCenter(login);
		
	}
	
	@FXML
	public void register(ActionEvent event) {
		if (!registerUsername.getText().equals("") && !registerPassword.getText().equals("") && !registerName.getText().equals("") && !registerLastName.getText().equals("")) {
			System.out.println("Epa, cuenta creada:" + "\nName: " + registerName.getText() + "\nLast name: " + registerLastName.getText() + "\n" + registerUsername.getText() + "\nPassword: " + registerPassword.getText());
			int num = (int)(Math.random() * 100000);
			String id = registerName.getText().substring(0,2) + registerLastName.getText().substring(0,2) + num;
			// Validate that the id never repeats
			gh.addUser(registerName.getText(), registerLastName.getText(), id, registerUsername.getText(), registerPassword.getText());
			System.out.println("\nId: " + id);
		}
	}
	
	public void loadRecover(MouseEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Recover.fxml"));
		fxmlLoader.setController(this);
		Parent login;
		login = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.getChildren().setAll(login);
		// mainPane.setCenter(login);
		
	}
}
