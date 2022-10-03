package com.jlvlg.pentagon.models;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Modification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Postable postable;
	private String oldTitle;
	private String oldImage;
	private String oldText;
	private Instant date;
	
	public Modification() {
		this.date = Instant.now();
	}
	
	public Modification (Postable post, String oldImage, String oldText, String oldTitle) {
		this(post, oldTitle, oldText);
		this.oldImage = oldImage;
	}
	
	public Modification (Postable post, String oldTitle, String oldText) {
		this();
		this.postable = post;
		this.oldText = oldText;
		this.oldTitle = oldTitle;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Postable getPostable() {
		return postable;
	}

	public void setPostable(Postable postable) {
		this.postable = postable;
	}

	public String getOldImage() {
		return oldImage;
	}

	public void setOldImage(String oldImage) {
		this.oldImage = oldImage;
	}

	public String getOldText() {
		return oldText;
	}

	public void setOldText(String oldText) {
		this.oldText = oldText;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public String getOldTitle() {
		return oldTitle;
	}

	public void setOldTitle(String oldTitle) {
		this.oldTitle = oldTitle;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Modification that = (Modification) o;
		return Objects.equals(id, that.id) && Objects.equals(postable, that.postable) && Objects.equals(oldTitle, that.oldTitle) && Objects.equals(oldImage, that.oldImage) && Objects.equals(oldText, that.oldText) && Objects.equals(date, that.date);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, postable, oldTitle, oldImage, oldText, date);
	}
}
