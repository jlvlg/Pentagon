package com.jlvlg.pentagon.models;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.jlvlg.pentagon.exceptions.UserAlreadyFollowedException;
import com.jlvlg.pentagon.exceptions.UserNotFollowedException;

/*** 
 * User Object Class: Inherits Followable Objec Abstractt Class
 * @author Luann
 */

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
	@ManyToMany
	private List<User> following;
	private int followers;
	private Instant joinDate;
	private boolean isActive;
	private boolean isAdmin;

	public User() {
		joinDate = Instant.now();
		this.isActive = true;
		this.following = new ArrayList<User>();
	}
	
	public User(String username, String password) {
		this(username, password, false);
	}
	
	public User(String username, String password, boolean isAdmin) {
		super();
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
		this.isActive = true;
		this.following = new ArrayList<User>();
	}
	
	/**
	 * Method to add users to an user's following list.
	 * @param user the user to be followed
	 * @return true to a successful operation
	 * @throws UserAlreadyFollowedException Tried to follow an user you already follow
	 */
	public boolean follow(User user) throws UserAlreadyFollowedException {
		if (following.contains(user))
			throw new UserAlreadyFollowedException(this, user);
		return following.add(user);
	}
	
	/**
	 * Method to remove users from an user's following list
	 * @param user the user to be unfollowed 
	 * @return true to a successful operation 
	 * @throws UserNotFollowedException The user was not found in your following list
	 */
	public boolean unfollow(User user) throws UserNotFollowedException {
		if (!following.contains(user))
			throw new UserNotFollowedException(this, user);
		return following.remove(user);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<User> getFollowing() {
		return following;
	}

	public void setFollowing(List<User> following) {
		this.following = following;
	}

	public int getFollowers() {
		return followers;
	}

	public void setFollowers(int followers) {
		this.followers = followers;
	}

	public Instant getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Instant joinDate) {
		this.joinDate = joinDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public int hashCode() {
		return Objects.hash(followers, following, id, isActive, isAdmin, joinDate, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return followers == other.followers && Objects.equals(following, other.following)
				&& Objects.equals(id, other.id) && isActive == other.isActive && isAdmin == other.isAdmin
				&& Objects.equals(joinDate, other.joinDate) && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}
}
