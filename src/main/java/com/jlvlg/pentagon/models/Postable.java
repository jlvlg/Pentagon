package com.jlvlg.pentagon.models;

import lombok.Getter;
import lombok.Setter;

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
@Getter @Setter
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

	/**
	 * Increments likes attribute
	 */
	public void like() {
		likes++;
	}
	
	/**
	 * Increments dislikes attribute
	 */
	public void unlike() {
		dislikes++;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Postable postable = (Postable) o;
		return likes == postable.likes && dislikes == postable.dislikes && isActive == postable.isActive && isEdited == postable.isEdited && Objects.equals(id, postable.id) && Objects.equals(author, postable.author) && Objects.equals(text, postable.text) && Objects.equals(creationDate, postable.creationDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, author, text, creationDate, likes, dislikes, isActive, isEdited);
	}
}
