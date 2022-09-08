package com.jlvlg.pentagon.models;

import java.util.Arrays;

/**
 * Profile Object Class: inherits Page Objects Class
 * @author Luann
 */

public class Profile extends Page {
	private float [] scoreMeans;
	
	public Profile(String name) {
		super(name);
		this.scoreMeans = new float[5];
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
