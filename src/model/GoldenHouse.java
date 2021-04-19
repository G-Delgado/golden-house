package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class GoldenHouse {
	// Constants
	public static final String ORDERS_FILE = "data/orders.gh";
	public static final String PRODUCTS_FILE = "data/products.gh";
	public static final String CLIENTS_FILE = "data/clients.gh";
	public static final String EMPLOYEES_FILE = "data/employees.gh";
	public static final String INGREDIENTS_FILE = "data/ingredients.gh";
	public static final String TYPES_FILE = "data/types.gh";
	public static final String USERS_FILE = "data/users.gh";
	public static final String REPORT_FILE = "data/report.txt";
	
	// Imports
	public static final String IMPORT_INGREDIENTS = "data/Mock_Ingredients.csv";
	public static final String IMPORT_INGREDIENTS_TEST = "data/Mock_Ingredients_Test_Result.csv";
	public static final String IMPORT_PRODUCTS = "data/Mock_Products.csv";
	public static final String IMPORT_PRODUCTS_RESULT = "data/Mock_Products_Result.csv";
	public static final String IMPORT_CLIENTS = "data/Mock_Clients.csv";
	public static final String IMPORT_CLIENTS_RESULT = "data/Mock_Clients_Result.csv";
	public static final String IMPORT_ORDERS = "data/Mock_Orders.csv";
	public static final String IMPORT_ORDERS_RESULT = "data/Mock_Orders_Result.csv";
	
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
	
	public void importData(User sessionUser) {
		importIngredients(sessionUser);
		importProducts(sessionUser);
		importClients();
		importOrders(sessionUser);
		
	}
	
	public void importOrders(User sessionUser) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(IMPORT_ORDERS));
			PrintWriter pw = new PrintWriter(IMPORT_ORDERS_RESULT);
			String line = "";
			pw.println(br.readLine());
			while (line != null) {
				line = br.readLine();
				if (line != null) {
					String [] parts = line.split(",");
					int randState = (int) (1+ Math.random() * 4);
					State state = null;
					if (randState == 1) {
						state = State.REQUESTED;
					} else if (randState == 2) {
						state = State.INPROCESS;
					} else if (randState == 3){
						state = State.SENT;
					} else {
						state = State.DELIVERED;
					}
					
					int randPr = (int) (1 + Math.random() * 10);
					ArrayList<Product> prs = new ArrayList<>();
					for (int i = 0; i < products.size() && i < randPr; i++) {
						prs.add(products.get(i));
					}
					
					int randCl = (int) (Math.random() * clients.size());
					Client cl = clients.get(randCl);
					
					int randEm = (int) (Math.random() * employees.size());
					Employee em = employees.get(randEm);
					
					LocalDate date = LocalDate.now();
					LocalTime time = LocalTime.now();
					
					orders.add(new Order(parts[0], state, prs, cl, em, date, time, parts[7], sessionUser));
					pw.println(parts[0] + "," + state + "," + prs.toString() + "," + cl.getName() + " " + cl.getLastName() + "," + em.getName() + " " + em.getLastName() + "," + date + "," + time + "," + parts[7] + "," + sessionUser);
				}
			}
			pw.close();
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void importClients() {
		try {
		BufferedReader br = new BufferedReader(new FileReader(IMPORT_CLIENTS));
		PrintWriter pw = new PrintWriter(IMPORT_CLIENTS_RESULT);
		String line = "";
		pw.println(br.readLine());
		while (line != null) {
			line = br.readLine();
			if (line != null) {
				String [] parts = line.split(",");
				clients.add(new Client(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
				pw.println(line);
			}
		}
		pw.close();
		br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void importProducts(User sessionUser) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(IMPORT_PRODUCTS));
			PrintWriter pw = new PrintWriter(IMPORT_PRODUCTS_RESULT);
			String line = "";
			pw.println(br.readLine());
			while (line != null) {
				line = br.readLine();
				if (line != null) {
					String [] parts = line.split(",");
					String name = parts[0];
					String size = parts[1];
					double price = Double.parseDouble(parts[2].substring(1, parts[2].length()));
					int rand = (int) (Math.random() * types.size() - 1);
					Type ty = types.get(rand);
					int randIg = (int) (1 + Math.random() * 10);
					ArrayList<Ingredient> igs = new ArrayList<>();
					for (int i = 0; i < ingredients.size() && i < randIg; i++) {
						igs.add(ingredients.get(i));
					}
					// It seems i forgot to add the createdBy and lastModifiedBy
					LocalDate date = LocalDate.now();
					LocalTime time = LocalTime.now();
					products.add(new Product(name, size, price, ty, igs, sessionUser, date, time));
					pw.println(name + "," + size + "," + ty.getName() + "," + igs.toString() + "," + sessionUser + "," + sessionUser + "," +date + "," + time);
					
				}
			}
			pw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void importIngredients(User sessionUser) {
		BufferedReader br;
		PrintWriter pw;
		try {
			br = new BufferedReader(new FileReader(IMPORT_INGREDIENTS));
			pw = new PrintWriter(IMPORT_INGREDIENTS_TEST);
			String line = "";
			pw.println(br.readLine());
			while (line != null) {
				//System.out.println("Here!");
				line = br.readLine();
				if (line != null) {
					//System.out.println(line);
					String [] parts = line.split(",");
					String name = parts[0];
					boolean enabled = true;
					if (parts[1].equals("")) {						
						parts[1] = sessionUser.toString();
						parts[2] = sessionUser.toString();
						enabled = parts[3] == "true" ? true : false;
						pw.println(name + "," + parts[1] + "," + parts[2] + "," + parts[3]);
					} else {
						parts[2] = sessionUser.toString();
						parts[3] = sessionUser.toString();
						enabled = parts[4] == "true" ? true : false;
						if (parts.length >= 6) {
							pw.println(name + "," + parts[1] + "," + parts[2] + "," + parts[3] + "," + parts[4] + "true");
						} else {							
							pw.println(name + "," + parts[1] + "," + parts[2] + "," + parts[3] + "," + parts[4]);
						}
					}
					Ingredient ig = new Ingredient(name, sessionUser, enabled);
					//System.out.println(ig.toString());
					ingredients.add(ig);
					
				}
			}
			br.close();
			pw.close();
		} catch (IOException e) {
			System.out.println("Something Went Wrong!");
		}
	}
	
	
	public void generateReport(String type, LocalDate iDate, LocalTime iTime, LocalDate eDate, LocalTime eTime, String separator) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(REPORT_FILE);
		if (type.equals("Orders")) {
			pw.println("Nombre cliente,direccion,telefono,empleado,estado,fecha,hora del pedido,observaciones,productos...");
			for (int i = 0; i < orders.size(); i++) {
				Order or = orders.get(i);
				if ((or.getDate().isAfter(iDate) && or.getDate().isBefore(eDate)) || or.getDate().isEqual(eDate) && eDate.isEqual(iDate)) {
					if (true/*(eDate.isEqual(iDate) && or.getTime().isAfter(iTime) && or.getTime().isBefore(eTime)) || (!eDate.isEqual(iDate) && or.getTime().isAfter(iTime) && or.getTime().isBefore(eTime))*/) {						
						Client cl = or.getClient();
						String result = "";
						String appeared = "";
						for (int j = 0; j < or.getProducts().size(); j++) {
							if (!appeared.contains(or.getProducts().get(j).getName())) {						
								Product pr = or.getProducts().get(j);
								String productName = pr.getName();
								double price = pr.getPrice();
								int quantity = or.productTimes(pr);
								result += productName + separator + price + separator + quantity + separator;
								appeared += productName;
								//pw.print(separator + productName + separator+ price + separator + quantity);
							}
						}
						pw.println(cl.getName() + " " + cl.getLastName() + separator + cl.getAddress() + separator + cl.getPhoneNumber() + separator +
								or.getEmployee().getName() + " " + or.getEmployee().getLastName() + separator +
								or.getState() + separator + or.getDate() + separator + or.getTime() + separator + or.getObservations() + separator + result/* Stuff here*/);
					}
					}
				}
			
		} else if (type.equals("Employees")) {
			pw.println("Nombre y apellido,pedidos entregados,dinero total obtenido,total de pedidos,dinero total");
			int totalOrders = 0;
			int totalGain = 0;
			for (int i = 0; i < employees.size(); i++) {
				Employee em = employees.get(i);
				if ((em.getDate().isAfter(iDate) && em.getDate().isBefore(eDate)) || em.getDate().isEqual(eDate) && eDate.isEqual(iDate)) {					
					int ordersCont = 0;
					long totalPrice = 0;
					System.out.println(orders.size() + " Tamaño\n");
					for (int j = 0; j < orders.size(); j++) {
						if (orders.get(j).isEmployee(em)) {
							System.out.println("Im inside");
							ordersCont++;
							totalPrice += orders.get(i).getTotalPrice();
						}
					}
					totalOrders += ordersCont;
					totalGain += totalPrice;
					//System.out.println(ordersCont + " Conteo.  \n" + totalPrice + " Precio total");
					pw.println(em.getName() + " " + em.getLastName() + separator + ordersCont + separator + totalPrice + separator + totalOrders + separator + totalGain);
				}
			}
		} else if (type.equals("Products")) {
			pw.println("Nombre del producto,veces pedido,total del producto,total de pedidos,dinero total");
			int totalOrders = 0;
			int totalGain = 0;
			for (int i = 0; i < products.size(); i++) {
				Product pr = products.get(i);
				if ((pr.getDate().isAfter(iDate) && pr.getDate().isBefore(eDate)) || pr.getDate().isEqual(eDate) && eDate.isEqual(iDate)) {					
					int times = 0;
					double total = 0;
					for (int j = 0; j < orders.size(); j++) {
						times += orders.get(i).productTimes(pr);
					}
					totalOrders += times;
					total = pr.getPrice() * times;
					totalGain += total;
					if (i == products.size() - 1) {					
						pw.println(pr.getName() + separator + times + separator +total + separator + totalOrders + separator + totalGain);
					} else {
						pw.println(pr.getName() + separator + times + separator +total + separator + totalOrders + separator + totalGain);
					}
				}
			}
		}
		pw.close();
	}
	
	public void saveOrders() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ORDERS_FILE));
		oos.writeObject(orders);
		oos.close();
		
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
	public void loadOrders() throws ClassNotFoundException, IOException {
		File or = new File(ORDERS_FILE);
		if (or.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(or));
			orders = (ArrayList<Order>) ois.readObject();
			ois.close();
		}
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
	
	public Order getOrderByCode(String orderCode) {
		Order order = null;
		for (int i = 0; i < orders.size() && order == null; i++) {
			if (orders.get(i).getCode().equals(orderCode)) {
				order = orders.get(i);
			}
		}
		return order;
	}
	
	public void addOrder(String cl, String em, ArrayList<String[]> productsList, LocalDate date, LocalTime time, String obs, User sessionUser) {
		// Crear codigo
		int num = (int)(Math.random() * 100000);
		String code = cl.substring(0,2) + em.substring(0,2) + num;
		// Crear estado
		State state = State.REQUESTED;
		
		// Client
		Client client = getClientByName(cl);
		
		// Employee
		Employee employee = getEmployeeByName(em);
		String print = "";
		ArrayList<Product> orderProducts = new ArrayList<>();
		Product pr = null;
		for (int i = 0; i  < productsList.size(); i++) {
			for (int j = 0; j < Integer.parseInt(productsList.get(i)[1]); j++) {				
				pr = getProductByName(productsList.get(i)[0]);
				orderProducts.add(pr);
				print += pr.getName() + ", ";
			}
		}
		
		System.out.println(print);
		
		// Podemos pasar el sessionUser.
		Order order = new Order(code, state, orderProducts, client, employee, date, time, obs, sessionUser);
		orders.add(order);
		
		System.out.println("\nOrder" + order.toString());
	}
	
	public boolean changeStateOfOrder(String orderCode, int statePos) {
		boolean changed = false;
		Order order = getOrderByCode(orderCode);
		switch (statePos) {
			case 1:
				changed = order.setState(State.INPROCESS);
				break;
			case 2:
				changed = order.setState(State.SENT);
				break;
			case 3:
				changed = order.setState(State.DELIVERED);
				break;
		}
		
		return changed;
	}
	
	public void editOrder() {
		
	}
	
	public void addUser(String n, String ln, String id, String us, String pass) { ////
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		User mockUser = new User(n,ln,id,us,pass, date, time);
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
	
	public int isUser(String username) {
		int pos = -1;
		for (int i = 0; i < users.size() && pos == -1; i++) {
			if (users.get(i).getUsername().equals(username)) {
				pos = i;
			}
		}
		return pos;
	}
	
	public void changeUserPassword (String username, String newPassword) {
		User user = users.get(isUser(username));
		System.out.println("Before: " + user.toString());
		user.setPassword(newPassword);
		System.out.println("After: " + user.toString());
	}
	
	public void sortUsers() {
		Comparator<User> UserUsernameIdComparator = new UserUsernameIdComparator();
		Collections.sort(users, UserUsernameIdComparator);
	}
	
	public void addEmployee(String name, String lastName, String id) {
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		Employee em = new Employee(name, lastName, id, date, time);
		employees.add(em);
	}
	
	public Employee getEmployeeByName(String name) { // Revisar
		Employee em = null;
		for (int i = 0; i < employees.size() && em == null; i++) {
			System.out.println("Name: " + employees.get(i).getName() + "\nLastName: " + employees.get(i).getLastName());
			if (employees.get(i).getName().equals(name.split(" ")[0]) && employees.get(i).getLastName().equals(name.split(" ")[1])) {
				em = employees.get(i);
			}
		}
		return em;
	}
	
	public void editEmployee(String n, String newN, String ln) { // Revisar // Hay que manejar que pasa si es NULL
		Employee em = null;
		for (int i = 0; i < employees.size() && em == null; i++) {
			System.out.println("Name: " + employees.get(i).getName() + "\nLastName: " + employees.get(i).getLastName());
			if (employees.get(i).getName().equals(n.split(" ")[0]) && employees.get(i).getLastName().equals(n.split(" ")[1])) {
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
	
	public void enableDisableEmployee(String name, boolean isEnabled) {
		Employee em = null;
		for (int i = 0; i < employees.size() && em == null; i++) {
			if (employees.get(i).getName().equals(name)) {
				em = employees.get(i);
			}
		}
		em.setEnabled(isEnabled);
	}
	
	public void sortEmployees() {
		Collections.sort(employees);
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
	
	public void sortIngredients() { // Bubble sort
		System.out.println(Arrays.toString(ingredients.toArray()));
		for (int i = 0; i < ingredients.size(); i++) {
			for (int j = i + 1; j < ingredients.size(); j++) {
				if (ingredients.get(i).getName().compareTo(ingredients.get(j).getName()) > 0) {
					Ingredient temp = ingredients.get(i);
					ingredients.set(i, ingredients.get(j));
					ingredients.set(j, temp);
				}
			}
		}
		System.out.println(Arrays.toString(ingredients.toArray()));
	}
	
	public void addType(String type, User createdBy) {
		Type ty = new Type(type, createdBy);
		types.add(ty);
		System.out.println(ty.toString());
	}
	
	public void enableDisableType(String name, boolean isEnabled) {
		Type ty = null;
		for (int i = 0; i < types.size() && ty == null; i++) {
			if (types.get(i).getName().equals(name)) {
				ty = types.get(i);
			}
		}
		ty.setEnabled(isEnabled);
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
	
	public void sortTypes() { // Insertion sort
		System.out.println(Arrays.toString(types.toArray()));
		for (int i = 1; i < types.size(); i++) {
			for (int j = i; j > 0 && types.get(j).getName().compareTo(types.get(j-1).getName()) < 0; j--) {
				Type temp = types.get(j);
				types.set(j, types.get(j-1));
				types.set(j-1,temp);
			}
		}
		System.out.println(Arrays.toString(types.toArray()));
	}
	
	public void addProduct(String n, String s, double p, String ty, ArrayList<String> ig, User sessionUser) {
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
		LocalDate d = LocalDate.now();
		LocalTime t = LocalTime.now();
		System.out.println(ing);
		Product pr = new Product(n,s,p,type,ing,sessionUser, d, t);
		System.out.println(pr.toString());
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
	
	public void enableDisableProduct(String name, boolean isEnabled) {
		Product pr = null;
		for (int i = 0; i < products.size() && pr == null; i++) {
			if (products.get(i).getName().equals(name)) {
				pr = products.get(i);
			}
		}
		pr.setEnabled(isEnabled);
	}
	
	public boolean productIsUsed(String name) {
		Product pr = null; 
		for (int i = 0; i < products.size() && pr == null; i++) {
			if (products.get(i).getName().equals(name)) {
				pr = products.get(i);
			}
		}
		
		boolean found = false;
		for (int i = 0; i < orders.size() && !found; i++) {
			ArrayList<Product> orderProducts = orders.get(i).getProducts();
			for (int j = 0; j < orderProducts.size() && !found; j++) {
				if (pr.getName().equals(orderProducts.get(j).getName())) {
					found = true;
				}
			}
		}
		
		return found;
	}
	
	public Client getClientByName(String n) {
		Client cl = null;
		for (int i = 0; i < clients.size(); i++) {
			//System.out.println("Name:" + clients.get(i).getName() + "\nLast name: " + clients.get(i).getLastName());
			if (clients.get(i).getName().equals(n.split(" ")[0]) && clients.get(i).getLastName().equals(n.split(" ")[1])) {
				cl = clients.get(i);
			}
		}
		
		return cl;
	}
	
	public void editClient(String n, String newN, String ln, String ad, String pn, String obs) {
		Client cl = null;
		for (int i = 0; i < clients.size() && cl == null; i++) {
			//System.out.println("Name:" + clients.get(i).getName() + "\nLast name: " + clients.get(i).getLastName());
			if (clients.get(i).getName().equals(n.split(" ")[0]) && clients.get(i).getLastName().equals(n.split(" ")[1])) {
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
	
	public void enableDisableIngredient(String name, boolean isEnabled) {
		Ingredient ig = null;
		for (int i = 0; i < ingredients.size() && ig == null; i++) {
			if (ingredients.get(i).getName().equals(name)) {
				ig = ingredients.get(i);
			}
		}
		ig.setEnabled(isEnabled);
	}
	
	public boolean ingredientIsUsed(String name) {
		Ingredient ig = null; 
		for (int i = 0; i < ingredients.size() && ig == null; i++) {
			if (ingredients.get(i).getName().equals(name)) {
				ig = ingredients.get(i);
			}
		}
		
		boolean found = false;
		for (int i = 0; i < products.size() && !found; i++) {
			ArrayList<Ingredient> prIngredients = products.get(i).getIngredients();
			for (int j = 0; j < prIngredients.size() && !found; j++) {
				if (ig.getName().equals(prIngredients.get(j).getName())) {
					found = true;
				}
			}
		}
		
		return found;
	}
	
	
	// Arreglar la parte de insertarlos ordenadamente :")
	public void addClient(String n, String ln, String id, String address, String phone, String observations) {
		Client cl = new Client(n,ln,id,address,phone,observations);
		if (clients.isEmpty()) {
			clients.add(cl);
		} else {			
			int i = 0;
			while (i < clients.size() && (clients.get(i).getLastName().compareTo(cl.getLastName()) < 0)) {
				i++;
			}
			clients.add(i, cl);
		}
		System.out.println("\n" + cl.toString() + "\n");
	}
	
	public void deleteClient(String name, String lastName) {
		boolean found = false;
		for (int i = 0; i < clients.size() && !found; i++) {
			if (clients.get(i).getName().equals(name) && clients.get(i).getLastName().equals(lastName)) {
				clients.remove(i);
				found = true;
			}
		}
	}
	
	public void enableDisableClient(String name, boolean isEnabled) {
		Client cl = null;
		for (int i = 0; i < clients.size() && cl == null; i++) {
			if (clients.get(i).getName().equals(name)) {
				cl = clients.get(i);
			}
		}
		cl.setEnabled(isEnabled);
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
