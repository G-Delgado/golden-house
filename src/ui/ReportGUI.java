package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.GoldenHouse;
import model.User;

public class ReportGUI extends GoldenHouseMainGUI{
	
	@FXML
	private StackPane ghPane;
	
	// Report
	
	@FXML
	private ComboBox<String> reportType;
	
	@FXML
	private TextField separatorField;
	
	@FXML
	private DatePicker initDate;
	
	@FXML
	private TextField initHour;
	
	@FXML
	private DatePicker endDate;
	
	@FXML
	private TextField endHour;
	
	public ReportGUI(GoldenHouse gh, User sessionUser, BorderPane mainPane, StackPane ghPane) {
		super(gh, sessionUser.getUsername(), sessionUser.getPassword(), mainPane);
		this.ghPane = ghPane;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GenerateReport.fxml"));
		fxmlLoader.setController(this);
		
		try {
			Parent report = fxmlLoader.load();
			ghPane.getChildren().clear();
			ghPane.getChildren().setAll(report);
			ObservableList<String> options = FXCollections.observableArrayList("Orders", "Employees","Products");
			reportType.setItems(options);
			initDate.setValue(LocalDate.now());
			endDate.setValue(LocalDate.now());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void generateReport(ActionEvent event) { // Validaciones de cuando no haya elegido un tipo o cuando no haya separador, etc.
		String type = reportType.getValue();
		String separator = separatorField.getText();
		
		LocalDate iDate = initDate.getValue();
		LocalTime iTime = LocalTime.parse(initHour.getText());
		
		LocalDate eDate = endDate.getValue();
		LocalTime eTime = LocalTime.parse(endHour.getText());
		
		try {
			getGH().generateReport(type, iDate, iTime, eDate, eTime, separator);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}

