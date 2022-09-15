package com.jlvlg.pentagon.models;

import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * Profile Object Class: inherits Page Objects Class
 * @author Luann
 */

@Entity
public class Profile extends Page {
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private ScoreMean [] scoreMeans;
	
	public Profile() {}
	
	public Profile(String name) {
		this(new ScoreMean[5], name);
	}
	public Profile(ScoreMean[] scoreMeans, String name) {
		super(name);
		this.scoreMeans = scoreMeans;
	}
	public Profile(String name, String image, String description) {
		this(new ScoreMean[5], name, image, description);
	}
	public Profile (ScoreMean[]  scoreMeans, String name, String image, String description) {
		super(name, image, description);
		this.scoreMeans = scoreMeans;
	}
	public ScoreMean[] getScoreMeans() {
		return scoreMeans;
	}
	public void setScoreMeans(ScoreMean[] scoreMeans) {
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
