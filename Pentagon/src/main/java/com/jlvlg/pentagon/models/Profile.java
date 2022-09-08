package com.jlvlg.pentagon.models;

import java.util.HashMap;
import java.util.Map;

/*@author Luann
 * Profile Object Class: inherits Page Objects Class
 */

public class Profile extends Page {
	private Long id;
	private float [] scoreMeans;
	
	public Profile(String name ) {
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
}
