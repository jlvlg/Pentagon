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
import lombok.Getter;
import lombok.Setter;

/*** 
 * User Object Class: Inherits Followable Objec Abstractt Class
 * @author Luann
 */

@Entity(name = "PentagonUser")
@Getter @Setter
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
		this.following = new ArrayList<>();
	}
	
	public User(String username, String password) {
		this(username, password, false);
	}
	
	public User(String username, String password, boolean isAdmin) {
		this();
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	
	/**
	 * Method to add users to a user's following list.
	 * @param user the user to be followed
	 * @return true to a successful operation
	 * @throws UserAlreadyFollowedException Tried to follow a user you already follow
	 */
	public boolean follow(User user) throws UserAlreadyFollowedException {
		if (following.contains(user))
			throw new UserAlreadyFollowedException(this, user);
		return following.add(user);
	}
	
	/**
	 * Method to remove users from a user's following list
	 * @param user the user to be unfollowed 
	 * @return true to a successful operation 
	 * @throws UserNotFollowedException The user was not found in your following list
	 */
	public boolean unfollow(User user) throws UserNotFollowedException {
		if (!following.contains(user))
			throw new UserNotFollowedException(this, user);
		return following.remove(user);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return followers == user.followers && isActive == user.isActive && isAdmin == user.isAdmin && Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(following, user.following) && Objects.equals(joinDate, user.joinDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, username, password, following, followers, joinDate, isActive, isAdmin);
	}
}
