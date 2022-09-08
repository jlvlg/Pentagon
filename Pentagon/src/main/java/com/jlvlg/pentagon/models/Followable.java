package com.jlvlg.pentagon.models;

import java.util.Objects;

/**
 * Followable object class.
 * Stores the number of followers.
 * User and Page both inherit from this class.
 * 
 * @author Lucas
 *
 */

public abstract class Followable {
	private Long id;
	private int followers;
	private boolean active;
	
	public Followable() {
		this.active = true;
	}
	
	public Long getId() {
		return id;
	}
	
	public int getFollowers() {
		return followers;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFollowers(int followers) {
		this.followers = followers;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(active, followers, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Followable other = (Followable) obj;
		return active == other.active && followers == other.followers && Objects.equals(id, other.id);
	}
	
	/**
	 * Increments followers attribute
	 */
	public void follow() {
		followers++;
	}
	
	/**
	 * Decrements followers attribute
	 */
	public void unfollow() {
		followers--;
	}
}
