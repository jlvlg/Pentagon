package com.jlvlg.pentagon.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.jlvlg.pentagon.exceptions.NegativeFollowersException;

/**
 * Followable object class.
 * Stores the number of followers.
 * User and Page both inherit from this class.
 * 
 * @author Lucas
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Followable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int followers;
	private boolean active;
	
	public Followable() {}
	
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
