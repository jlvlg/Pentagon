/**
 * 
 */
package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.User;

/**
 * Thrown when trying to follow a user you already follow
 * @author Lucas
 *
 */
public class UserAlreadyFollowedException extends Exception {
	private static final long serialVersionUID = 2454072147111365387L;
	private User following;
	private User follower;
	
	public UserAlreadyFollowedException(User following, User follower) {
		super("The user is already being followed");
		this.following = following;
		this.follower = follower;
	}

	public User getFollowing() {
		return following;
	}

	public User getFollower() {
		return follower;
	}
}
