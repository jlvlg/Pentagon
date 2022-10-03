package com.jlvlg.pentagon.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.jlvlg.pentagon.exceptions.PostVisibilityException;

@Entity
public class Post extends Postable {
	@ManyToOne
	private Profile profile;
	private String image;
	@ManyToMany
	private List<User> visibility;
	private String title;
	
	public Post() {
		super();
		this.visibility = new ArrayList<>();
	}
	
	public Post(User author, Profile profile, String text, String image, String title) {
		this(author, profile, text, title);
		this.image = image;
	}
	
	public Post(User author, Profile profile, String text, String title) {
		this(author, profile, text, new ArrayList<>(), title);
	}
	
	public Post(User author, Profile profile, String text, String image, List<User> visibility, String title) {
		this(author, profile, text, visibility, title);
		this.image = image;
	}

	public Post(User author, Profile profile, String text, List<User> visibility, String title) {
		super(author, text);
		this.profile = profile;
		this.visibility = visibility;
		this.title = title;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
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
		result = prime * result + Objects.hash(image, profile, title, visibility);
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
		return Objects.equals(image, other.image) && Objects.equals(profile, other.profile)
				&& Objects.equals(title, other.title) && Objects.equals(visibility, other.visibility);
	}

	public boolean turnVisibleTo(User user) throws PostVisibilityException {
		if (visibility.contains(user))
			throw new PostVisibilityException(this, user);
		return visibility.add(user);
	}

	public boolean turnInvisibleTo(User user) throws PostVisibilityException {
		if (!visibility.contains(user))
			throw new PostVisibilityException(this, user);
		return visibility.remove(user);
	}
}
