package ui;

import model.GoldenHouse;

public class GoldenHouseMainGUI {
	
	private GoldenHouse gh;
	
	public GoldenHouseMainGUI(GoldenHouse goldenHouse) {
		gh = goldenHouse;
	}
	
	public void addProduct() {
		System.out.println(gh.toString()); /* It's just a method to temporarily remove
		 The warning*/
	}
}
