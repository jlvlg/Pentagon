package com.jlvlg.pentagon.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

/**
 * Page class, where users can publish posts. Belongs to a user.
 * @author Lucas
 */

@Entity
@Getter @Setter
public class Page {
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
	private boolean isActive;
	
	public Page() {
		this.scoreMeans = new ArrayList<>(List.of(
			new ScoreMean("Aparência física"),
			new ScoreMean("Inteligência"),
			new ScoreMean("Humor"),
			new ScoreMean("Responsabilidade"),
			new ScoreMean("Caráter")
		));
		this.isActive = true;
	}
	
	public Page(String name) {
		this();
		this.name = name;
	}

	public Page(User user) {
		this();
		this.user = user;
		this.name = user.getUsername();
	}
	
	public Page(List<ScoreMean> scoreMeans, String name) {
		this(scoreMeans, name, null, null, true);
	}
	
	public Page(String name, String image, String description) {
		this(name);
		this.image = image;
		this.description = description;
	}
	
	public Page(List<ScoreMean>  scoreMeans, String name, String image, String description, boolean isActive) {
		this.scoreMeans = scoreMeans;
		this.name = name;
		this.image = image;
		this.description = description;
		this.isActive = isActive;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Page page = (Page) o;
		return isActive == page.isActive && Objects.equals(id, page.id) && Objects.equals(user, page.user) && Objects.equals(name, page.name) && Objects.equals(image, page.image) && Objects.equals(description, page.description) && Objects.equals(scoreMeans, page.scoreMeans);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, user, name, image, description, scoreMeans, isActive);
	}
}
