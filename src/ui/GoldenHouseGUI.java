package ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.stage.Stage;
//import javafx.stage.Stage;
import model.GoldenHouse;

public class GoldenHouseGUI {
	
	private GoldenHouse gh;
	
	private GoldenHouseMainGUI ghMainGUI;
	
	private ResourceBundle rb;
	
	// Main Pane ---
	
	@FXML
	private BorderPane mainPane;
	
	//
	@FXML
	private Label timeLabel;
	
	@FXML
	private Label dateLabel;
	
	private Thread td;
	
	// Login ---
	@FXML
	private TextField loginUsername;
	
	@FXML
	private TextField loginPassword;
	
	@FXML
	private Label warningLabel;
	
	// Internationalization
	
	@FXML
	private Label loginLabel;
	
	@FXML
	private Label usernameLabel;
	
	@FXML
	private Label passwordLabel;
	
	@FXML
	private Button loginBtn;
	
	@FXML
	private Label forgotLabel;
	
	@FXML
	private Button createBtn;
	
	@FXML
	private ComboBox<String> languageBox;
	
	@FXML
	private Label changeLabel;
	
	@FXML
	private Button changeBtn;
	
	@FXML
	private Button cancelBtn;
	
	@FXML
	private Label repeatLabel;
	
	
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
	
	@FXML
	private TextField recoverUsername;
	
	@FXML
	private TextField newPassword;
	
	@FXML
	private TextField repeatPassword;
	
	public GoldenHouseGUI(GoldenHouse gh, BorderPane mp) {
		mainPane = mp;
		this.gh = gh;
		rb = ResourceBundle.getBundle("ui/Bundle");
		td = null;
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
		
		ObservableList<String> opts = FXCollections.observableArrayList();
		opts.add("CO");
		opts.add("US");
		languageBox.setItems(opts);
		
		loginLabel.setText(rb.getString("log_in"));
		usernameLabel.setText(rb.getString("user_name"));
		passwordLabel.setText(rb.getString("password"));
		loginBtn.setText(rb.getString("log_in"));
		forgotLabel.setText(rb.getString("forgot_password"));
		createBtn.setText(rb.getString("new_account"));
		
		if (td == null) {			
			LocalDate date = LocalDate.now();
			LocalTime time = LocalTime.now();
			dateLabel.setText(date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear());
			timeLabel.setText(time.getHour() + ":" + time.getMinute() + ":" + time.getSecond());
			
			
			/*
			 * 
			 * ATTEMPT TO CREATE THE RUNNING TIME IN ANOTHER THREAD;  
			 * 
			 * */
			td = new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Platform.runLater(new Runnable() {
							@Override
							public void run() {								
								dateLabel.setText(LocalDate.now().getDayOfMonth() + "/" + LocalDate.now().getMonthValue() + "/" + LocalDate.now().getYear());
								timeLabel.setText(LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond());
							}
						});
						
						
					}
				}
			});
			td.start();
		}
	}
	
	@FXML
	public void changeLanguage(ActionEvent event) {
		String selected = languageBox.getSelectionModel().getSelectedItem();
		if (selected.equals("CO")) {
			rb = ResourceBundle.getBundle("ui/Bundle");
		} else if (selected.equals("US")) {
			rb = ResourceBundle.getBundle("ui/Bundle", Locale.US);
		}
		changeStrings();
	}
	
	public void changeStrings() {
		loginLabel.setText(rb.getString("log_in"));
		usernameLabel.setText(rb.getString("user_name"));
		passwordLabel.setText(rb.getString("password"));
		loginBtn.setText(rb.getString("log_in"));
		forgotLabel.setText(rb.getString("forgot_password"));
		createBtn.setText(rb.getString("new_account"));
	}
	
	@FXML
	public void login(ActionEvent event) {
		if (!loginUsername.getText().equals("") && !loginPassword.getText().equals("")) {
			//System.out.println("Fua, bien hecho!");
			int userPos = gh.isUser(loginUsername.getText(), loginPassword.getText());
			if (userPos != -1) {
				try {
					warningLabel.setText("");
					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
					alert.setContentText("Has iniciado sesión correctamente!");
					alert.showAndWait();
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
		ghMainGUI = new GoldenHouseMainGUI(gh, username, password, mainPane, rb);
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
					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
					alert.setContentText("Te has registrado correctamente!");
					alert.showAndWait();
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
		
		changeLabel.setText(rb.getString("change_password"));
		usernameLabel.setText(rb.getString("user_name"));
		passwordLabel.setText(rb.getString("password"));
		repeatLabel.setText(rb.getString("repeat"));
		changeBtn.setText(rb.getString("change_password"));
		cancelBtn.setText(rb.getString("cancel"));
	}
	
	public void recover(ActionEvent event) {
		String username = recoverUsername.getText();
		int isUser = gh.isUser(username);
		if (isUser != -1) {
			String password = newPassword.getText();
			String confirmPassword = repeatPassword.getText();
			if (password.equals(confirmPassword) && !password.equals("") && !confirmPassword.equals("")) {
				gh.changeUserPassword(username, confirmPassword);
				warningLabel.setText("");
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setContentText("Has cambiado la contraseña correctamente!");
				alert.showAndWait();
				try {
					loadMain(username, password);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				warningLabel.setText("Las contraseñas no coinciden");
			}
		} else {
			warningLabel.setText("Este usuario no existe");
		}
	}
}
