package com.jlvlg.pentagon.models;

import java.time.Instant;
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
	private Instant creationDate;
	private int likes;
	private int dislikes;
	private boolean isActive;
	private boolean isEdited;
	
	public Postable() {
		this.isActive = true;
		this.creationDate = Instant.now();
	}
	
	public Postable(User author, String text) {
		this();
		this.text = text;
		this.author = author;
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

	public Instant getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Instant creationDate) {
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
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isEdited() {
		return isEdited;
	}

	public void setEdited(boolean isEdited) {
		this.isEdited = isEdited;
	}

	@Override
	public int hashCode() {
		return Objects.hash(isActive, author, creationDate, dislikes, isEdited, id, likes, text);
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
		return isActive == other.isActive && Objects.equals(author, other.author)
				&& Objects.equals(creationDate, other.creationDate) && dislikes == other.dislikes
				&& isEdited == other.isEdited && Objects.equals(id, other.id) && likes == other.likes
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
