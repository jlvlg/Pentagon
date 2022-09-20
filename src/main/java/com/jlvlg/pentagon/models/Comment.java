package com.jlvlg.pentagon.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/* Comment Object Class
 * 
 * @author Luann
 */

@Entity
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

	public Postable getPostable() {
		return postable;
	}

	public void setPostable(Postable postable) {
		this.postable = postable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(postable);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		return Objects.equals(postable, other.postable);
	}
}
