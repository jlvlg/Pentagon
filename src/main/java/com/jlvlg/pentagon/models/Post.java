package com.jlvlg.pentagon.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.jlvlg.pentagon.exceptions.PostVisibilityException;
import lombok.Getter;
import lombok.Setter;

/**
 * The Post model class defines the structure to be used to represent a post. It has required page
 * to be displayed at, text, title, an optional image path string and an optional list of users,
 * which will be used to determine if said user is able to visualize and access the post.
 * If visibility is an empty list, then
 * there shall be no restriction for all users.
 * @author Lucas
 *
 */

@Entity
@Getter @Setter
public class Post extends Postable {
	@ManyToOne
	private Page page;
	private String image;
	@ManyToMany
	private List<User> visibility;
	private String title;
	
	public Post() {
		super();
		this.visibility = new ArrayList<>();
	}
	
	public Post(User author, Page page, String text, String image, String title) {
		this(author, page, text, title);
		this.image = image;
	}
	
	public Post(User author, Page page, String text, String title) {
		this(author, page, text, new ArrayList<>(), title);
	}
	
	public Post(User author, Page page, String text, String image, List<User> visibility, String title) {
		this(author, page, text, visibility, title);
		this.image = image;
	}

	public Post(User author, Page page, String text, List<User> visibility, String title) {
		super(author, text);
		this.page = page;
		this.visibility = visibility;
		this.title = title;
	}

	/**
	 * Adds a user to the visibility list.
	 * @param user The user to be added.
	 * @return true if successfully added, else false.
	 * @throws PostVisibilityException The post is already visible to the user
	 */
	public boolean turnVisibleTo(User user) throws PostVisibilityException {
		if (visibility.contains(user))
			throw new PostVisibilityException(this, user);
		return visibility.add(user);
	}
	
	/**
	 * Removes a user from the visibility list.
	 * @param user The user to be removed.
	 * @return true if successfully removed, else false.
	 * @throws PostVisibilityException The post is already invisible to the user
	 */
	public boolean turnInvisibleTo(User user) throws PostVisibilityException {
		if (!visibility.contains(user))
			throw new PostVisibilityException(this, user);
		return visibility.remove(user);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Post post = (Post) o;
		return Objects.equals(page, post.page) && Objects.equals(image, post.image) && Objects.equals(visibility, post.visibility) && Objects.equals(title, post.title);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), page, image, visibility, title);
	}
}
