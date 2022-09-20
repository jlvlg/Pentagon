package com.jlvlg.pentagon.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/* Comment Object Class
 * 
 * @author Luann
 */

@Entity
@Getter @Setter
public class Comment extends Postable {
	@ManyToOne
	private Postable postable;
	
	public Comment() {
		super();
	}

	public Comment(User author, String text, Postable postable) {
		super(author, text);
		this.postable = postable;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Comment comment = (Comment) o;
		return Objects.equals(postable, comment.postable);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), postable);
	}
}
