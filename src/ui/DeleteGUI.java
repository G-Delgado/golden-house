package ui;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.GoldenHouse;
import model.User;

public class DeleteGUI extends GoldenHouseMainGUI{
	// General
	
	private GoldenHouse gh;
		
	private User sessionUser;
		
	// Panes
		
	private BorderPane mainPane;
		
	private StackPane ghPane;
	
	public DeleteGUI(GoldenHouse gh, User sessionUser, BorderPane mainPane, StackPane ghPane) {
		super(gh, sessionUser.getUsername(), sessionUser.getPassword(), mainPane);
		this.gh = gh;
		this.sessionUser = sessionUser;
		this.mainPane = mainPane;
		this.ghPane = ghPane;
	}
}
