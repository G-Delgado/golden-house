package model;

public class Type {
	private String name;
	private User createdBy;
	private User lastModifiedBy;
	
	public Type(String name, User createdBy) {
		this.name = name;
		this.setCreatedBy(createdBy);
		this.setLastModifiedBy(createdBy);
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

	
}
