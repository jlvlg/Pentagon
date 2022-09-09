package com.jlvlg.pentagon.models;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * Profile Object Class: inherits Page Objects Class
 * @author Luann
 */

@Entity
public class Profile extends Page {
	@OneToMany
	private float [] scoreMeans;
	
	public Profile(String name) {
		this(new float[5], name);
	}
	public Profile(float[] scoreMeans, String name) {
		super(name);
		this.scoreMeans = scoreMeans;
	}
	public Profile(String name, String image, String description) {
		this(new float[5], name, image, description);
	}
	public Profile (float[]  scoreMeans, String name, String image, String description) {
		super(name, image, description);
		this.scoreMeans = scoreMeans;
	}
	public float[] getScoreMeans() {
		return scoreMeans;
	}
	public void setScoreMeans(float[] scoreMeans) {
		this.scoreMeans = scoreMeans;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(scoreMeans);
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
		Profile other = (Profile) obj;
		return Arrays.equals(scoreMeans, other.scoreMeans);
	}
}
