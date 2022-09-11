package com.jlvlg.pentagon.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Moderator class. Created when a user turns into a moderator of a page
 * @author Lucas
 *
 */

@Entity
public class Moderator {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private User user;
	private boolean leader;
	
	public Moderator() {}
	
	public Moderator(User user) {
		this(user, false);
	}
	
	public Moderator(User user, boolean leader) {
		this.user = user;
		this.leader = leader;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLeader() {
		return leader;
	}

	public void setLeader(boolean leader) {
		this.leader = leader;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, leader, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Moderator other = (Moderator) obj;
		return Objects.equals(id, other.id) && leader == other.leader && Objects.equals(user, other.user);
	}
}
