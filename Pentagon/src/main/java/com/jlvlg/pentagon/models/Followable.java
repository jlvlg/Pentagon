package com.jlvlg.pentagon.models;

import com.jlvlg.pentagon.exceptions.NegativeFollowersException;

/**
 * Followable object class. User and Page both inherit from this class.
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
	public void followed() {
		followers++;
	}
	
	/**
	 * Decrements followers attribute
	 * @throws NegativeFollowersException You tried to unfollow from a page with 0 followers
	 */
	public void unfollowed() throws NegativeFollowersException {
		if (followers <= 0)
			throw new NegativeFollowersException(this);
		followers--;
	}
	
	/**
	 * Switches active flag
	 */
	public void delete() {
		active = !active;
	}
}
