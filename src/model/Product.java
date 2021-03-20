package model;

import java.util.ArrayList;

public class Product {
	private String name;
	// Size will be a String for the moment
	private String size;
	private double price;
	private Type type;
	private ArrayList<Ingredient> ingredients;
	
	public Product(String n, String s, double p, Type ty, ArrayList<Ingredient> ar) {
		name = n;
		size = s;
		price = p;
		type = ty;
		ingredients = ar;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}
	
	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}
	
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}
	
	/**
	 * @return the ingredients
	 */
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}
	
	/**
	 * @param ingredients the ingredients to set
	 */
	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
}
