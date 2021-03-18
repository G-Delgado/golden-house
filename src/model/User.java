package model;

public class User extends Employee{
	private String username;
	private String password;
	
	public User(String n, String ln, String id, String us, String pass) {
		super(n,ln,id);
		username = us;
		password = pass;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
