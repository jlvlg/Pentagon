package com.jlvlg.pentagon.models;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jlvlg.pentagon.exceptions.UserAlreadyFollowedException;
import com.jlvlg.pentagon.exceptions.UserNotFollowedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/*** 
 * User Object Class: Inherits Followable Objec Abstractt Class
 * @author Luann
 */
@Entity(name = "PentagonUser")
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	@ManyToMany
	private List<User> following;
	private int followers;
	private Instant joinDate;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private boolean isActive;

	public User() {
		joinDate = Instant.now();
		this.isActive = true;
		this.following = new ArrayList<>();
	}
	
	public User(String username, String password) {
		this();
		this.username = username;
		this.password = password;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isActive;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
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

	@Override
	public int hashCode() {
		return Objects.hash(followers, following, id, isActive, joinDate, password, username);
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
				&& Objects.equals(id, other.id) && isActive == other.isActive
				&& Objects.equals(joinDate, other.joinDate) && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}
}
