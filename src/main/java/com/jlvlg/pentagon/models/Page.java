package com.jlvlg.pentagon.models;

import java.util.Arrays;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Page class, where users can publish posts. Belongs to an user.
 * @author Lucas
 */

@Entity
public class Page {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private User user;
	private String name;
	private String image;
	private String description;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private ScoreMean [] scoreMeans;
	private boolean isActive;
	
	public Page() {
		this.scoreMeans = new ScoreMean[] {
			new ScoreMean("Aparência física"),
			new ScoreMean("Inteligência"),
			new ScoreMean("Humor"),
			new ScoreMean("Responsabilidade"),
			new ScoreMean("Caráter")
		};
		this.isActive = true;
	}
	
	public Page(String name) {
		this();
		this.name = name;
	}
	
	public Page(ScoreMean[] scoreMeans, String name) {
		this(scoreMeans, name, null, null, true);
	}
	
	public Page(String name, String image, String description) {
		this(name);
		this.image = image;
		this.description = description;
	}
	
	public Page(ScoreMean[]  scoreMeans, String name, String image, String description, boolean isActive) {
		this.scoreMeans = scoreMeans;
		this.name = name;
		this.image = image;
		this.description = description;
		this.isActive = isActive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ScoreMean[] getScoreMeans() {
		return scoreMeans;
	}

	public void setScoreMeans(ScoreMean[] scoreMeans) {
		this.scoreMeans = scoreMeans;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(scoreMeans);
		result = prime * result + Objects.hash(description, id, image, isActive, name, user);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Page other = (Page) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(image, other.image) && isActive == other.isActive && Objects.equals(name, other.name)
				&& Arrays.equals(scoreMeans, other.scoreMeans) && Objects.equals(user, other.user);
	}
}
