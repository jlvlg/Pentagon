package com.jlvlg.pentagon.models;

import java.util.Objects;

/* Comment Object Class
 * 
 * @author Luann
 */

public class Comment {
	private Postable postable;

	public Comment(Postable postable) {
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
		return Objects.hash(postable);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		return Objects.equals(postable, other.postable);
	}
	
	
}
