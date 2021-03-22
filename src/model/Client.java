package model;

import java.io.Serializable;

public class Client extends Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	private String address;
	private String phoneNumber;
	private String observations; /*Not much idea of what this is.*/
	
	public Client(String n, String ln, String id, String ad, String ph, String obs) {
		super(n,ln,id);
		address = ad;
		phoneNumber = ph;
		observations = obs;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getObservations() {
		return observations;
	}
	
	public void setObservations(String observations) {
		this.observations = observations;
	}
	
	@Override
	public String toString() {
		String obs = observations.length() != 0? observations : "NONE";
		String details = "Name: " + getName() + "\nLast name: " + getLastName() + "\nId: " + getId() 
		+ "\nAddress: " + address + "\nPhone: " + phoneNumber + "\nObservations: " + obs;
		
		return details;
	}
	
}
