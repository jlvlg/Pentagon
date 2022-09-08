package com.jlvlg.pentagon.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.time.*;

/**@author Luann
 * 
 * Page Object Class: Inherits Followable Object Abstract Class and has an
 *  aggregation relationship with User Object Class much to much
 */

public class Page {
	private List<User> owners;
	private String name;
	private String image;
	private String description;
	private ZonedDateTime creationDate;
	
	public Page (List<User> owners, String name) {
		this.owners = owners;
		this.name = name;
		this.owners = new ArrayList<User>();
		this.creationDate = ZonedDateTime.now();
	}
	
	/**
	 * Method to add one or more owners to an page
	 * @param User user is a new owner to the receives page
	 * @return true to a new owner
	 */
	
	public boolean addOwner (User user) {
		return owners.add(user);
	}
	
	 /* Method to remove owner to a page
	 * @param User user is the owner that will be removed 
	 * @return true to removal of owner 
	 */
	
	public boolean removeOwner (User user) {
		return owners.remove(user);
	}
	
	public List<User> getOwners() {
		return owners;
	}

	public ZonedDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(ZonedDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public void setOwners(List<User> owners) {
		this.owners = owners;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, image, name, owners);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Page other = (Page) obj;
		return Objects.equals(description, other.description) && Objects.equals(image, other.image)
				&& Objects.equals(name, other.name) && Objects.equals(owners, other.owners);
	}
	
	
}
