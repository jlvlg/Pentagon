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
	private int unlikes;
	private boolean edited;
	
	public Postable() {
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

	public int getUnlikes() {
		return unlikes;
	}

	public void setUnlikes(int dislikes) {
		this.unlikes = dislikes;
	}

	public boolean isEdited() {
		return edited;
	}

	public void setEdited(boolean isEdited) {
		this.edited = isEdited;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, creationDate, unlikes, edited, id, likes, text);
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
		return Objects.equals(author, other.author)
				&& Objects.equals(creationDate, other.creationDate) && unlikes == other.unlikes
				&& edited == other.edited && Objects.equals(id, other.id) && likes == other.likes
				&& Objects.equals(text, other.text);
	}

	public void like() {
		likes++;
	}

	public void unlike() {
		unlikes++;
	}
}
