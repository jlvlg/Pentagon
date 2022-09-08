package com.jlvlg.pentagon.models;

import java.util.Objects;

/*@author Luann
 * ScoreMean Object Class 
 */

public class ScoreMean {
	private String category;
	private float mean;
	
	public ScoreMean(String category) {
		this.category = category;
	}
	public ScoreMean(String category, float mean) {
		this(category);
		this.mean = mean;
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
	@Override
	public int hashCode() {
		return Objects.hash(category, mean);
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
		return Objects.equals(category, other.category)
				&& Float.floatToIntBits(mean) == Float.floatToIntBits(other.mean);
	}
}
