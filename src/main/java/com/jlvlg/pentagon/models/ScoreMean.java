package com.jlvlg.pentagon.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*@author Luann
 * ScoreMean Object Class 
 */

@Entity
@Getter @Setter
public class ScoreMean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String category;
	private float mean;
	
	public ScoreMean() {}
	
	public ScoreMean(String category, float mean) {
		this(category);
		this.mean = mean;
	}
	public ScoreMean(String category) {
		this.category = category;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ScoreMean scoreMean = (ScoreMean) o;
		return Float.compare(scoreMean.mean, mean) == 0 && Objects.equals(id, scoreMean.id) && Objects.equals(category, scoreMean.category);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, category, mean);
	}
}
