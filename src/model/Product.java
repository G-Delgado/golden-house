package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2;
	private String name;
	// Size will be a String for the moment
	private String size;
	private double price;
	private Type type;
	private ArrayList<Ingredient> ingredients;
	private User createdBy;
	private User lastModifiedBy;
	private boolean enabled;
	
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
	
	@Override
	public String toString() {
		String igs = "";
		for (int i = 0; i < ingredients.size(); i++) {
			if (i != ingredients.size() - 1) {
				igs += ingredients.get(i).getName() + ", ";
			} else {
				igs += ingredients.get(i).getName();
			}
		}
		String details = "Name: " + name + 
				"\nSize: " + size + 
				"\nPrice: " + price + 
				"\nType: " + type.getName() + 
				"\nIngredients: " + igs;
		return details;
	}

	/**
	 * @return the createdBy
	 */
	public User getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the lastModifiedBy
	 */
	public User getLastModifiedBy() {
		return lastModifiedBy;
	}

	/**
	 * @param lastModifiedBy the lastModifiedBy to set
	 */
	public void setLastModifiedBy(User lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
