package com.jlvlg.pentagon.models;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

/**
 * Abstract Postable class. Serves as base to Post and Comment classes.
 * @author Lucas
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Postable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private User author;
	private String text;
	private ZonedDateTime creationDate;
	private int likes;
	private int dislikes;
	private boolean active;
	private boolean edited;
	
	public Postable() {}
	
	public Postable(User author, String text) {
		this.text = text;
		this.author = author;
		this.creationDate = ZonedDateTime.now();
		this.active = true;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ZonedDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(ZonedDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isEdited() {
		return edited;
	}

	public void setEdited(boolean edited) {
		this.edited = edited;
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, author, creationDate, dislikes, edited, id, likes, text);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Postable other = (Postable) obj;
		return active == other.active && Objects.equals(author, other.author)
				&& Objects.equals(creationDate, other.creationDate) && dislikes == other.dislikes
				&& edited == other.edited && Objects.equals(id, other.id) && likes == other.likes
				&& Objects.equals(text, other.text);
	}

	/**
	 * Increments likes attribute
	 */
	public void like() {
		likes++;
	}
	
	/**
	 * Increments dislikes attribute
	 */
	public void dislike() {
		dislikes++;
	}
}