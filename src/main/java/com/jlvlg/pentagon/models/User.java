package com.jlvlg.pentagon.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** 
 * User Object Class: Inherits Followable Objec Abstractt Class
 * @author Luann
 */

public class User extends Followable {
	private String name;
	private List<Followable> following;
	private boolean isAdmin;

	public User(String name) {
		super();
		this.name = name;
		this.isAdmin = false;
		this.following = new ArrayList<Followable>();
	}
	public User(String name, boolean isAdmin) {
		super();
		this.name = name;
		this.isAdmin = isAdmin;
		this.following = new ArrayList<Followable>();
	}
	/**
	 * Method to add followers to a user.
	 * @param Followable is a new followable to the user receives
	 * @return true to a new followable
	 */
	
	public boolean addFollowable(Followable followable) {
		return following.add(followable);
	}
	/**
	 * Method to remove followers to a user
	 * @param Followable is the followable that will be removed 
	 * @return true to removal of followable 
	 */
	public boolean removeFollowable(Followable followable) {
		return following.remove(followable);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Followable> getFollowing() {
		return following;
	}
	public void setFollowing(List<Followable> following) {
		this.following = following;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(following, isAdmin, name);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(following, other.following) && isAdmin == other.isAdmin
				&& Objects.equals(name, other.name);
	}
}
