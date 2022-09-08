package com.jlvlg.pentagon.models;

import java.util.Objects;

/*Score Object Class
 *
 *@author Luann
 */
  

public class Score {
	private Long id;
	private int score;
	private User author;;
	private Profile profile;
	private String category;
	
	public Score(int score, User author, Profile profile, String category) {
		this.score = score;
		this.author = author;
		this.profile = profile;
		this.category = category;
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, category, id, profile, score);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Score other = (Score) obj;
		return Objects.equals(author, other.author) && Objects.equals(category, other.category)
				&& Objects.equals(id, other.id) && Objects.equals(profile, other.profile) && score == other.score;
	}
}
