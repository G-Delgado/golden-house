package model;

import java.io.Serializable;

public class User extends Employee implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2;
	private String username;
	private String password;
	/*private User createdBy; That makes no sense to me. As the users are created by registering.
	 * Otherwise, One user might be able to create others, an "admin" user.
	private User lastModifiedBy;*/
	// It also has the enabled attribute. I may change it, though.
	
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
	
	@Override
	public String toString() {
		String details = "Username: " + username + "\nPassword: " + password;
		// Remember, he also has the attributes of name, lastName, id.
		return details;
		
	}
	
}
