package com.jlvlg.pentagon.models;

import java.time.ZonedDateTime;
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
	private Postable post;
	private String oldImage;
	private String oldText;
	private ZonedDateTime date;
	
	public Modification() {}
	
	public Modification (Postable post, String oldImage, String oldText) {
		this(post, oldText);
		this.oldImage = oldImage;
	}
	
	public Modification (Postable post, String oldText) {
		this.post = post;
		this.oldText = oldText;
		this.date = ZonedDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Postable getPost() {
		return post;
	}

	public void setPost(Postable post) {
		this.post = post;
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

	public ZonedDateTime getDate() {
		return date;
	}

	public void setDate(ZonedDateTime date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, id, oldImage, oldText, post);
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
				&& Objects.equals(post, other.post);
	}
}
