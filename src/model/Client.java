package model;

public class Client extends Person{
	private String address;
	private int phoneNumber;
	private String observations; /*Not much idea of what this is.*/
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getObservations() {
		return observations;
	}
	
	public void setObservations(String observations) {
		this.observations = observations;
	}
	
}
