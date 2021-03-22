package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GoldenHouse {
	// Constants
	
	public static final String PRODUCTS_FILE = "data/products.gh";
	public static final String CLIENTS_FILE = "data/clients.gh";
	public static final String EMPLOYEES_FILE = "data/employees.gh";
	public static final String INGREDIENTS_FILE = "data/ingredients.gh";
	public static final String TYPES_FILE = "data/types.gh";
	public static final String USERS_FILE = "data/users.gh";
	
	// Attributes
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
	
	public void saveProducts() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PRODUCTS_FILE));
		oos.writeObject(products);
		oos.close();
		
	}
	
	public void saveClients() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CLIENTS_FILE));
		oos.writeObject(clients);
		oos.close();
	}
	
	public void saveEmployees() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(EMPLOYEES_FILE));
		oos.writeObject(employees);
		oos.close();
	}
	
	public void saveIngredients() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(INGREDIENTS_FILE));
		oos.writeObject(ingredients);
		oos.close();
	}
	
	public void saveTypes() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TYPES_FILE));
		oos.writeObject(types);
		oos.close();
	}
	
	public void saveUsers() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE));
		oos.writeObject(users);
		oos.close();
	}
	
	@SuppressWarnings("unchecked") // As I know what the ois.readObject() is going to throw;
	public void loadProducts() throws ClassNotFoundException, IOException {
		File pr = new File(PRODUCTS_FILE);
		if (pr.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pr));
			products = (ArrayList<Product>) ois.readObject();
			ois.close();
		}
	}
	
	@SuppressWarnings("unchecked") // As I know what the ois.readObject() is going to throw;
	public void loadClients() throws ClassNotFoundException, IOException {
		File cl = new File(CLIENTS_FILE);
		if (cl.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cl));
			clients = (ArrayList<Client>) ois.readObject();
			ois.close();
		}
	}
	
	@SuppressWarnings("unchecked") // As I know what the ois.readObject() is going to throw;
	public void loadEmployees() throws ClassNotFoundException, IOException {
		File em = new File(EMPLOYEES_FILE);
		if (em.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(em));
			employees = (ArrayList<Employee>) ois.readObject();
			ois.close();
		}
	}
	@SuppressWarnings("unchecked") // As I know what the ois.readObject() is going to throw;
	public void loadIngredients() throws ClassNotFoundException, IOException {
		File ig = new File(INGREDIENTS_FILE);
		if (ig.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ig));
			ingredients = (ArrayList<Ingredient>) ois.readObject();
			ois.close();
		}
	}
	@SuppressWarnings("unchecked") // As I know what the ois.readObject() is going to throw;
	public void loadTypes() throws ClassNotFoundException, IOException {
		File ty = new File(TYPES_FILE);
		if (ty.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ty));
			types = (ArrayList<Type>) ois.readObject();
			ois.close();
		}
	}
	@SuppressWarnings("unchecked") // As I know what the ois.readObject() is going to throw;
	public void loadUsers() throws ClassNotFoundException, IOException {
		File us = new File(USERS_FILE);
		if (us.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(us));
			users = (ArrayList<User>) ois.readObject();
			ois.close();
		}
	}
	
	public void addUser(String n, String ln, String id, String us, String pass) {
		User mockUser = new User(n,ln,id,us,pass);
		// Recordar que deben agregarse de forma ORDENADA!!!!!!!
		users.add(mockUser);
	}
	
	public int isUser(String username, String password) {
		boolean isUser = false;
		int pos = -1;
		// Hay que buscar un algoritmo más eficiente
		for (int i = 0; i < users.size() && !isUser; i++) {
			if (users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)) {
				isUser = true;
				pos = i;
			}
		}
		return pos;
	}
	
	public void addEmployee(String name, String lastName, String id) {
		Employee em = new Employee(name, lastName, id);
		employees.add(em);
	}
	
	public Employee getEmployeeByName(String name) {
		Employee em = null;
		for (int i = 0; i < employees.size() && em == null; i++) {
			if (employees.get(i).getName().equals(name)) {
				em = employees.get(i);
			}
		}
		return em;
	}
	
	public void editEmployee(String n, String newN, String ln) {
		Employee em = null;
		for (int i = 0; i < employees.size() && em == null; i++) {
			if (employees.get(i).getName().equals(n)) {
				em = employees.get(i);
			}
		}
		em.setName(newN);
		em.setLastName(ln);
	}
	
	public void deleteEmployee(String name) {
		boolean found = false;
		for (int i = 0; i < employees.size() && !found; i++) {
			if (employees.get(i).getName().equals(name)) {
				employees.remove(i);
				found = true;
			}
		}
	}
	
	public void addIngredient(String ingredient, User createdBy) {
		Ingredient ig = new Ingredient(ingredient, createdBy);
		ingredients.add(ig);
		System.out.println(ig.toString());
	}
	
	public int searchIngredientPos(String igName) { // Me serviria un algoritmo de busqueda
		int pos = -1;
		for (int i = 0; i < ingredients.size(); i++) {
			if (igName.equals(ingredients.get(i).getName())) {
				pos = i;
			}
		}
		return pos;
	}

	public void editIngredient(String n, String newN, User sessionUser) {
		Ingredient ig = null;
		for (int i = 0; i < ingredients.size() && ig == null; i++) {
			if (ingredients.get(i).getName().equals(n)) {
				ig = ingredients.get(i);
			}
		}
		
		System.out.println("\nBefore: " + ig.toString());
		
		ig.setName(newN);
		ig.setLastModifiedBy(sessionUser);
		
		System.out.println("\nAfter: " + ig.toString());
	}
	
	public void deleteIngredient(String name) {
		boolean found = false;
		for (int i = 0; i < ingredients.size() && !found; i++) {
			if (ingredients.get(i).getName().equals(name)) {
				ingredients.remove(i);
				found = true;
			}
		}
	}
	
	public void addType(String type, User createdBy) {
		Type ty = new Type(type, createdBy);
		types.add(ty);
		System.out.println(ty.toString());
	}
	
	public void editType(String n, String newN, User sessionUser) {
		Type ty = null;
		for (int i = 0; i < types.size() && ty == null; i++) {
			if (types.get(i).getName().equals(n)) {
				ty = types.get(i);
			}
		}
		
		System.out.println("\nBefore: " + ty.toString());
		
		ty.setName(newN);
		ty.setLastModifiedBy(sessionUser);
		
		System.out.println("\nAfter: " + ty.toString());
	}
	
	public void deleteType(String name) {
		boolean found = false;
		for (int i = 0; i < types.size() && !found; i++) {
			if (types.get(i).getName().equals(name)) {
				types.remove(i);
				found = true;
			}
		}
	}
	
	public void addProduct(String n, String s, double p, String ty, ArrayList<String> ig) {
		boolean foundType = false;
		Type type = null;
		for (int i = 0; i < types.size() && !foundType; i++) {
			if (types.get(i).getName().equals(ty)) {
				type = types.get(i);
				foundType = true;
			}
		}
		ArrayList<Ingredient> ing = new ArrayList<>();
		for (int i = 0; i < ig.size(); i++) {
			ing.add(ingredients.get(searchIngredientPos(ig.get(i))));
		}
		
		System.out.println(ing);
		Product pr = new Product(n,s,p,type,ing);
		products.add(pr);
	}
	
	public Product getProductByName(String n) {
		Product pr = null;
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getName().equals(n)) {
				pr = products.get(i);
			}
		}
		
		return pr;
	}
	
	public void editProduct(String n, String newN, String s, double p, String ty, ArrayList<String> ig) {
		Product pr = null;
		for (int i = 0; i < products.size() && pr == null; i++) {
			if (products.get(i).getName().equals(n)) {
				pr = products.get(i);
			}
		}
		
		System.out.println("\nBefore: \n" + pr.toString());
		
		pr.setName(newN);
		pr.setSize(s);
		pr.setPrice(p);
		Type type = null;
		for (int i = 0; i < types.size(); i++) {
			if (types.get(i).getName().equals(ty)) {
				type = types.get(i);
			}
		}
		pr.setType(type);
		ArrayList<Ingredient> igs = new ArrayList<>();
		for (int i = 0; i < ig.size(); i++) {
			igs.add(ingredients.get(searchIngredientPos(ig.get(i))));
		}
		pr.setIngredients(igs);
		
		System.out.println("\nAfter: \n" + pr.toString());
	}
	
	public void deleteProduct(String name) {
		boolean found = false;
		for (int i = 0; i < products.size() && !found; i++) {
			if (products.get(i).getName().equals(name)) {
				products.remove(i);
				found = true;
			}
		}
	}
	
	public Client getClientByName(String n) {
		Client cl = null;
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getName().equals(n)) {
				cl = clients.get(i);
			}
		}
		
		return cl;
	}
	
	public void editClient(String n, String newN, String ln, String ad, String pn, String obs) {
		Client cl = null;
		for (int i = 0; i < clients.size() && cl == null; i++) {
			if (clients.get(i).getName().equals(n)) {
				cl = clients.get(i);
			}
		}
		
		System.out.println("\nBefore: " + cl.toString());
		
		cl.setName(newN);
		cl.setLastName(ln);
		cl.setAddress(ad);
		cl.setPhoneNumber(pn);
		cl.setObservations(obs);
		
		System.out.println("\nAfter: " + cl.toString());
	}
	
	
	public void addClient(String n, String ln, String id, String address, String phone, String observations) {
		Client cl = new Client(n,ln,id,address,phone,observations);
		clients.add(cl);
		System.out.println("\n" + cl.toString() + "\n");
	}
	
	public void deleteClient(String name) {
		boolean found = false;
		for (int i = 0; i < clients.size() && !found; i++) {
			if (clients.get(i).getName().equals(name)) {
				clients.remove(i);
				found = true;
			}
		}
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
