package model;

import java.util.ArrayList;

public class GoldenHouse {
	private ArrayList<Product> products;
	private ArrayList<Ingredient> ingredients;
	private ArrayList<Type> types;
	private ArrayList<Order> orders;
	private ArrayList<Client> clients;
	private ArrayList<Employee> employees;
	private ArrayList<User> users;
	
	public GoldenHouse() {
		products = new ArrayList<>();
		ingredients = new ArrayList<>();
		types = new ArrayList<>();
		orders = new ArrayList<>();
		clients = new ArrayList<>();
		employees = new ArrayList<>();
		users = new ArrayList<>();
	}
	
	public void addUser(String n, String ln, String id, String us, String pass) {
		User mockUser = new User(n,ln,id,us,pass);
		// Recordar que deben agregarse de forma ORDENADA!!!!!!!
		users.add(mockUser);
	}
	
	public boolean isUser(String username, String password) {
		boolean isUser = false;
		// Hay que buscar un algoritmo más eficiente
		for (int i = 0; i < users.size() && !isUser; i++) {
			if (users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)) {
				isUser = true;
			}
		}
		return isUser;
	}
	
	public void addIngredient() {
		
	}
	
	/**
	 * @return the products
	 */
	public ArrayList<Product> getProducts() {
		return products;
	}
	
	/**
	 * @param products the products to set
	 */
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
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
	
	/**
	 * @return the types
	 */
	public ArrayList<Type> getTypes() {
		return types;
	}
	
	/**
	 * @param types the types to set
	 */
	public void setTypes(ArrayList<Type> types) {
		this.types = types;
	}
	
	/**
	 * @return the orders
	 */
	public ArrayList<Order> getOrders() {
		return orders;
	}
	
	/**
	 * @param orders the orders to set
	 */
	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}
	
	/**
	 * @return the clients
	 */
	public ArrayList<Client> getClients() {
		return clients;
	}
	
	/**
	 * @param clients the clients to set
	 */
	public void setClients(ArrayList<Client> clients) {
		this.clients = clients;
	}
	
	/**
	 * @return the employees
	 */
	public ArrayList<Employee> getEmployees() {
		return employees;
	}
	
	/**
	 * @param employees the employees to set
	 */
	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}
	
	/**
	 * @return the users
	 */
	public ArrayList<User> getUsers() {
		return users;
	}
	
	/**
	 * @param users the users to set
	 */
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	
}
