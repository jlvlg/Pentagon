package com.jlvlg.pentagon.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*@author Luann
 * ScoreMean Object Class 
 */

@Entity
public class ScoreMean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String category;
	private float mean;
	
	public ScoreMean(String category, float mean) {
		this(category);
		this.mean = mean;
	}
	public ScoreMean(String category) {
		this.category = category;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public float getMean() {
		return mean;
	}
	public void setMean(float mean) {
		this.mean = mean;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		return Objects.hash(category, id, mean);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScoreMean other = (ScoreMean) obj;
		return Objects.equals(category, other.category) && Objects.equals(id, other.id)
				&& Float.floatToIntBits(mean) == Float.floatToIntBits(other.mean);
	}
}
