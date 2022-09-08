package com.jlvlg.pentagon.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Post model class defines the structure to be used to represent a post. It has required page
 * to be displayed at, text, title, an optional image path string and an optional list of users,
 * which will be used to determine if said user is able to visualize and access the post.
 * If visibility is an empty list, then
 * there shall be no restriction for all users.
 * @author Lucas
 *
 */
public class Post extends Postable {
	private Page page;
	private String image;
	private List<User> visibility;
	private String title;
	
	public Post(Page page, String text, String title) {
		super(text);
		this.page = page;
		this.title = title;
		this.visibility = new ArrayList<User>();
	}

	public Post(String text, Page page, String image, String title) {
		super(text);
		this.page = page;
		this.image = image;
		this.title = title;
		this.visibility = new ArrayList<User>();
	}

	public Post(String text, Page page, List<User> visibility, String title) {
		super(text);
		this.page = page;
		this.visibility = visibility;
		this.title = title;
	}

	public Post(String text, Page page, String image, List<User> visibility, String title) {
		super(text);
		this.page = page;
		this.image = image;
		this.visibility = visibility;
		this.title = title;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<User> getVisibility() {
		return visibility;
	}

	public void setVisibility(List<User> visibility) {
		this.visibility = visibility;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(image, page, title, visibility);
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
		Post other = (Post) obj;
		return Objects.equals(image, other.image) && Objects.equals(page, other.page)
				&& Objects.equals(title, other.title) && Objects.equals(visibility, other.visibility);
	}

	/**
	 * Adds an user to the visibility list.
	 * @param user The user to be added.
	 * @return true if successfully added, else false.
	 */
	public boolean turnVisibleTo(User user) {
		return visibility.add(user);
	}
	
	/**
	 * Removes an user from the visibility list.
	 * @param user The user to be removed.
	 * @return true if successfully removed, else false.
	 */
	public boolean turnInvisibleTo(User user) {
		return visibility.remove(user);
	}
}
