package model;

import java.io.Serializable;

public class Ingredient implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2;
	private String name;
	private User createdBy;
	private User lastModifiedBy;
	private boolean enabled;
	
	public Ingredient(String name, User createdBy) {
		this.name = name;
		this.setCreatedBy(createdBy);
		setLastModifiedBy(createdBy);
		setEnabled(true);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the createdBy
	 */
	public User getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the lastModifiedBy
	 */
	public User getLastModifiedBy() {
		return lastModifiedBy;
	}

	/**
	 * @param lastModifiedBy the lastModifiedBy to set
	 */
	public void setLastModifiedBy(User lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	
	@Override
	public String toString() {
		String details = "Name: " + name + "\nCreated by: " + createdBy.getUsername() + "\nLast modified by: " + lastModifiedBy.getUsername();
		return details;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


}
