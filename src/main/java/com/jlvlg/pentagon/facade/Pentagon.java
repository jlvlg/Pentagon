package com.jlvlg.pentagon.facade;

import com.jlvlg.pentagon.exceptions.*;
import com.jlvlg.pentagon.models.User;
import com.jlvlg.pentagon.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The system's core. A single-instance class that handles everything.
 * @author Lucas
 * @author Luann
 */
@Service
public class Pentagon {
    @Autowired
    private CommentServiceInterface commentService;
    @Autowired
    private UserServiceInterface userService;
    @Autowired
    private ModificationServiceInterface modificationService;
    @Autowired
    private PostServiceInterface postService;
    @Autowired
    private PageServiceInterface pageService;
    @Autowired
    private ScoreServiceInterface scoreService;

    /**
     * Finds a user by username
     * @param username The user's username
     * @return A User
     * @throws UserNotFoundException User not found
     */
    public User findUser(String username) throws UserNotFoundException {
        Optional<User> user = userService.findByUsername(username);
        if (user.isEmpty())
            throw new UserNotFoundException();
        return user.get();
    }

    /**
     * Finds a user by id
     * @param id The user's id
     * @return A User
     * @throws UserNotFoundException User not found
     */
    public User findUser(Long id) throws UserNotFoundException {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException();
        return user.get();
    }

    /**
     * Increments a user's followers attribute and adds the user to another user's following list
     * @param followingId The ID of the following user
     * @param followedId The ID of the followed user
     * @throws UserNotFoundException User not found
     * @throws UsernameTakenException Two users cannot have the same username
     * @throws InvalidUsernameException A username name cannot be null, empty, or contain spaces and/or special characters
     * @throws UserAlreadyFollowedException The user is already followed
     */
    public void followUser(Long followingId, Long followedId) throws UserNotFoundException, UsernameTakenException, InvalidUsernameException, UserAlreadyFollowedException {
        User following = findUser(followingId);
        User followed = findUser(followedId);
        if (following.follow(followed)) {
            followed.setFollowers(followed.getFollowers() + 1);
            userService.update(following);
            userService.update(followed);
        }
    }

    /**
     * Decrements a user's followers attribute and removes the user from another user's following list
     * @param followingId The ID of the following user
     * @param followedId The ID of the followers user
     * @throws UserNotFoundException User not found
     * @throws UserNotFollowedException The user if not being followed
     * @throws UsernameTakenException Two users cannot have the same username
     * @throws InvalidUsernameException A username name cannot be null, empty, or contain spaces and/or special characters
     */
    public void unfollowUser(Long followingId, Long followedId) throws UserNotFoundException, UserNotFollowedException, UsernameTakenException, InvalidUsernameException {
        User following = findUser(followingId);
        User followed = findUser(followedId);
        if (following.unfollow(followed)) {
            followed.setFollowers(followed.getFollowers() - 1);
            userService.update(following);
            userService.update(followed);
        }
    }
}
