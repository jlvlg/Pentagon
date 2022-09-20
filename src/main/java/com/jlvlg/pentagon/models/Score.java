package com.jlvlg.pentagon.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/*Score Object Class
 *
 *@author Luann
 */
  
@Entity
@Getter @Setter
public class Score {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int score;
	@ManyToOne
	private User author;
	@ManyToOne
	private Page page;
	private String category;
	
	public Score() {}
	
	public Score(int score, User author, Page page, String category) {
		this.score = score;
		this.author = author;
		this.page = page;
		this.category = category;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Score score1 = (Score) o;
		return score == score1.score && Objects.equals(id, score1.id) && Objects.equals(author, score1.author) && Objects.equals(page, score1.page) && Objects.equals(category, score1.category);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, score, author, page, category);
	}
}
