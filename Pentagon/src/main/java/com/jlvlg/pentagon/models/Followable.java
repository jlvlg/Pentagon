package com.jlvlg.pentagon.models;

/**Followable object class. User and Page both inherit from this class.
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
