package model;

public class User extends Employee{
	private String userName;
	private String password;
	
	public User(String n, String ln, String id) {
		super(n,ln,id);
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
