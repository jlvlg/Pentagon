package com.jlvlg.pentagon.models;

import java.util.Objects;

import com.jlvlg.pentagon.exceptions.InvalidModeratorOrderException;

public class Moderator {
	private Long id;
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
