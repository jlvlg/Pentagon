package com.jlvlg.pentagon.models;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import com.jlvlg.pentagon.exceptions.UserAlreadyFollowedException;
import com.jlvlg.pentagon.exceptions.UserNotFollowedException;

/*** 
 * User Object Class: Inherits Followable Objec Abstractt Class
 * @author Luann
 */
@Entity(name = "PentagonUser")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToMany
	private List<User> following;
	private int followers;
	private Instant joinDate;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Auth auth;

	public User() {
		joinDate = Instant.now();
		this.following = new ArrayList<>();
	}
	
	public User(String username, String password) {
		this();
		this.auth = new Auth(username, password);
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

	public Auth getAuth() {
		return auth;
	}

	public void setAuth(Auth auth) {
		this.auth = auth;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		return Objects.hash(followers, following, id, joinDate);
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
				&& Objects.equals(id, other.id) && Objects.equals(joinDate, other.joinDate);
	}
}
