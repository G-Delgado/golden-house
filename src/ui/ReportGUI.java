package ui;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.GoldenHouse;
import model.User;

public class ReportGUI extends GoldenHouseMainGUI{
	
	@FXML
	private StackPane ghPane;
	
	public ReportGUI(GoldenHouse gh, User sessionUser, BorderPane mainPane, StackPane ghPane) {
		super(gh, sessionUser.getUsername(), sessionUser.getPassword(), mainPane);
		this.ghPane = ghPane;
	}
	
}

