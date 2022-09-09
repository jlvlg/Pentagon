package com.jlvlg.pentagon.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.jlvlg.pentagon.exceptions.InvalidModeratorOrderException;

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
	private int order;
	
	public Moderator(User user, int order) {
		this(user);
		this.order = order;
	}

	public Moderator(User user) {
		this.user = user;
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

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, order, user);
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
		return Objects.equals(id, other.id) && order == other.order && Objects.equals(user, other.user);
	}
	
	public void promote() throws InvalidModeratorOrderException {
		if (order <= 1)
			throw new InvalidModeratorOrderException(this);
		order--;
	}
	
	public void demote() {
		order++;
	}
}
