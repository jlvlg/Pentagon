package com.jlvlg.pentagon.models;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/*The Modification model Class serves to modify Postable Abstract Class.
 * It's necessary to inform the post and the field or fields that must be modified 
 * 
 * @author Luann
 */

@Entity
public class Modification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Postable postable;
	private String oldImage;
	private String oldText;
	private Instant date;
	
	public Modification() {
		this.date = Instant.now();
	}
	
	public Modification (Postable post, String oldImage, String oldText) {
		this(post, oldText);
		this.oldImage = oldImage;
	}
	
	public Modification (Postable post, String oldText) {
		this();
		this.postable = post;
		this.oldText = oldText;
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

	@Override
	public int hashCode() {
		return Objects.hash(date, id, oldImage, oldText, postable);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Modification other = (Modification) obj;
		return Objects.equals(date, other.date) && Objects.equals(id, other.id)
				&& Objects.equals(oldImage, other.oldImage) && Objects.equals(oldText, other.oldText)
				&& Objects.equals(postable, other.postable);
	}
}
