package com.jlvlg.pentagon.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private User user;
	private String name;
	private String image;
	private String description;
	private double appearanceScore;
	private double intelligenceScore;
	private double characterScore;
	private double humorScore;
	private double responsibilityScore;

	public Profile() {
		appearanceScore = 0;
		intelligenceScore = 0;
		characterScore = 0;
		humorScore = 0;
		responsibilityScore = 0;
	}
	
	public Profile(String name) {
		this();
		this.name = name;
	}

	public Profile(User user) {
		this(user.getAuth().getUsername());
		this.user = user;
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

	public double getAppearanceScore() {
		return appearanceScore;
	}

	public void setAppearanceScore(double appearanceScore) {
		this.appearanceScore = appearanceScore;
	}

	public double getIntelligenceScore() {
		return intelligenceScore;
	}

	public void setIntelligenceScore(double intelligenceScore) {
		this.intelligenceScore = intelligenceScore;
	}

	public double getCharacterScore() {
		return characterScore;
	}

	public void setCharacterScore(double characterScore) {
		this.characterScore = characterScore;
	}

	public double getHumorScore() {
		return humorScore;
	}

	public void setHumorScore(double humorScore) {
		this.humorScore = humorScore;
	}

	public double getResponsibilityScore() {
		return responsibilityScore;
	}

	public void setResponsibilityScore(double responsibilityScore) {
		this.responsibilityScore = responsibilityScore;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Profile profile = (Profile) o;
		return Double.compare(profile.appearanceScore, appearanceScore) == 0 && Double.compare(profile.intelligenceScore, intelligenceScore) == 0 && Double.compare(profile.characterScore, characterScore) == 0 && Double.compare(profile.humorScore, humorScore) == 0 && Double.compare(profile.responsibilityScore, responsibilityScore) == 0 && Objects.equals(id, profile.id) && Objects.equals(user, profile.user) && Objects.equals(name, profile.name) && Objects.equals(image, profile.image) && Objects.equals(description, profile.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, user, name, image, description, appearanceScore, intelligenceScore, characterScore, humorScore, responsibilityScore);
	}
}
