package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
//import javafx.stage.Stage;
//import javafx.stage.Stage;
import model.GoldenHouse;

public class GoldenHouseGUI {
	
	private GoldenHouse gh;
	
	private GoldenHouseMainGUI ghMainGUI;
	
	// Main Pane ---
	
	@FXML
	private BorderPane mainPane;
	
	// Login ---
	@FXML
	private TextField loginUsername;
	
	@FXML
	private TextField loginPassword;
	
	@FXML
	private Label warningLabel;
	
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
	
	public GoldenHouseGUI(GoldenHouse gh, BorderPane mp) {
		mainPane = mp;
		this.gh = gh;
	}
	
	@FXML
	public void loadLogin() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
		fxmlLoader.setController(this);
		Parent login;
		login = fxmlLoader.load();
		mainPane.getChildren().clear();
		//mainPane.getChildren().setAll(login);
		mainPane.setCenter(login);

		/*Stage stage = (Stage) mainPane.getScene().getWindow();
		stage.setHeight(400);
		stage.setWidth(544);*/
		
	}
	
	@FXML
	public void login(ActionEvent event) {
		if (!loginUsername.getText().equals("") && !loginPassword.getText().equals("")) {
			System.out.println("Fua, bien hecho!");
			int userPos = gh.isUser(loginUsername.getText(), loginPassword.getText());
			if (userPos != -1) {
				try {
					warningLabel.setText("");
					loadMain(loginUsername.getText(), loginPassword.getText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				warningLabel.setText("Datos incorrectos");
			}
		} else {
			warningLabel.setText("Ingresa todos los campos");
		}
	}
	
	//@FXML // Check this
	public void loadMain(String username, String password) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GH.fxml"));
		ghMainGUI = new GoldenHouseMainGUI(gh, username, password, mainPane);
		fxmlLoader.setController(ghMainGUI); // For the moment, but it probably needs another controller
		// .setController(ghMainGUI);
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
		//mainPane.getChildren().setAll(login);
		 mainPane.setCenter(login);
		
		//Stage stage = (Stage) mainPane.getScene().getWindow();
		//stage.setHeight(450);
		
	}
	
	@FXML
	public void register(ActionEvent event) {
		System.out.println("Im in register");
		if (!registerUsername.getText().equals("") && !registerPassword.getText().equals("") && !registerName.getText().equals("") && !registerLastName.getText().equals("")) {
			System.out.println("Epa, cuenta creada:" + "\nName: " + registerName.getText() + "\nLast name: " + registerLastName.getText() + "\nUsername: " + registerUsername.getText() + "\nPassword: " + registerPassword.getText());
			int num = (int)(Math.random() * 100000);
			String id = "";
			try {				
				id = registerName.getText().substring(0,2) + registerLastName.getText().substring(0,2) + num;
				if (!id.equals("")) {
					gh.addUser(registerName.getText(), registerLastName.getText(), id, registerUsername.getText(), registerPassword.getText());
					gh.saveUsers();
					loadMain(registerUsername.getText(), registerPassword.getText());					
				}
				System.out.println("\nId: " + id);
			} catch (StringIndexOutOfBoundsException e ) {
				warningLabel.setText("Las casillas tienen valores muy pequeños");
				id = "";
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			warningLabel.setText("Ingrese todos los campos");
		}
	}
	
	public void loadRecover(MouseEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Recover.fxml"));
		fxmlLoader.setController(this);
		Parent login;
		login = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(login);
	}
}
