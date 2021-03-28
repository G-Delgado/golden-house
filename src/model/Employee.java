package model;

import java.io.Serializable;

public class Employee extends Person implements Serializable, Comparable<Employee>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2;

	public Employee(String n, String ln, String id) {
		super(n, ln, id);
	}

	@Override
	public int compareTo(Employee o) {
		return getLastName().compareTo(o.getLastName());
	}
}
