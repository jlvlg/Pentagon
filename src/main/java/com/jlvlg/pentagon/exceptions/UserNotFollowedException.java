/**
 * 
 */
package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.User;

/**
 * Thrown when trying to unfollow a user you don't follow
 * @author Lucas
 *
 */
public class UserNotFollowedException extends Exception {
	private final User following;
	private final User follower;
	
	public UserNotFollowedException(User following, User follower) {
		super("The user is not being followed");
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
