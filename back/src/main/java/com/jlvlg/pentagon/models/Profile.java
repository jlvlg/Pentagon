package com.jlvlg.pentagon.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

/**
 * Page class, where users can publish posts. Belongs to a user.
 * @author Lucas
 */

@Entity
public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private User user;
	private String name;
	private String image;
	private String description;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ScoreMean> scoreMeans;
	@JsonIgnore
	private boolean active;
	
	public Profile() {
		this.scoreMeans = new ArrayList<>(List.of(
			new ScoreMean("Aparência física"),
			new ScoreMean("Inteligência"),
			new ScoreMean("Humor"),
			new ScoreMean("Responsabilidade"),
			new ScoreMean("Caráter")
		));
		this.active = true;
	}
	
	public Profile(String name) {
		this();
		this.name = name;
	}

	public Profile(User user) {
		this(user.getAuth().getUsername());
		this.user = user;
	}
	
	public Profile(List<ScoreMean> scoreMeans, String name) {
		this(scoreMeans, name, null, null, true);
	}
	
	public Profile(String name, String image, String description) {
		this(name);
		this.image = image;
		this.description = description;
	}
	
	public Profile(List<ScoreMean>  scoreMeans, String name, String image, String description, boolean active) {
		this.scoreMeans = scoreMeans;
		this.name = name;
		this.image = image;
		this.description = description;
		this.active = active;
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

	public List<ScoreMean> getScoreMeans() {
		return scoreMeans;
	}

	public void setScoreMeans(List<ScoreMean> scoreMeans) {
		this.scoreMeans = scoreMeans;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean isActive) {
		this.active = isActive;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Profile profile = (Profile) o;
		return active == profile.active && Objects.equals(id, profile.id) && Objects.equals(user, profile.user) && Objects.equals(name, profile.name) && Objects.equals(image, profile.image) && Objects.equals(description, profile.description) && Objects.equals(scoreMeans, profile.scoreMeans);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, user, name, image, description, scoreMeans, active);
	}
}
