package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Employee extends Person implements Serializable, Comparable<Employee>{
	
	private LocalDate date;
	private LocalTime time;
	/**
	 * 
	 */
	private static final long serialVersionUID = 3;

	public Employee(String n, String ln, String id, LocalDate d, LocalTime t) {
		super(n, ln, id);
		date = d;
		time = t;
	}

	@Override
	public int compareTo(Employee o) {
		return getLastName().compareTo(o.getLastName());
	}
	
	@Override
	public String toString() {
		String details = getName() + " " + getLastName();
		return details;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}
	
}
