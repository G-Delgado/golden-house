package model;

import java.io.Serializable;

public class Employee extends Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1;

	public Employee(String n, String ln, String id) {
		super(n, ln, id);
	}
}
