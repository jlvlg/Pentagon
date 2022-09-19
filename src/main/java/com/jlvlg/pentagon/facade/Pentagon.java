package com.jlvlg.pentagon.facade;

import com.jlvlg.pentagon.exceptions.*;
import com.jlvlg.pentagon.models.*;
import com.jlvlg.pentagon.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
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
     * Finds users by theirs username likeness
     * @param username The username to search for
     * @return List of users
     * @throws UserNotFoundException No users found
     */
    public List<User> findUsers(String username) throws UserNotFoundException {
        List<User> users = userService.findByUsernameLikeIgnoreCase(username);
        if (users.isEmpty())
            throw new UserNotFoundException();
        return users;
    }

    /**
     * Calls the userService save method
     * @param user The user to be saved
     * @return The saved user
     * @throws InvalidUsernameException A username name cannot be null, empty, or contain spaces and/or special characters
     * @throws UsernameTakenException Two users cannot have the same username
     */
    public User saveUser(User user) throws InvalidUsernameException, UsernameTakenException {
        return userService.save(user);
    }

    /**
     * Calls the userService update method
     * @param user The user to be updated
     * @return The updated user
     * @throws UsernameTakenException Two users cannot have the same username
     * @throws InvalidUsernameException A username name cannot be null, empty, or contain spaces and/or special characters
     * @throws UserNotFoundException User not found
     */
    public User updateUser(User user) throws UsernameTakenException, InvalidUsernameException, UserNotFoundException {
        return userService.update(user);
    }

    /**
     * Calls the userService delete method
     * @param user The user to be deleted
     * @throws UserNotFoundException User not found
     */
    public void deleteUser(User user) throws UserNotFoundException {
        userService.delete(user);
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
            updateUser(following);
            updateUser(followed);
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
            updateUser(following);
            updateUser(followed);
        }
    }

    public Post findPost(Long id) throws PostNotFoundException {
        Optional<Post> post = postService.findById(id);
        if (post.isEmpty())
            throw new PostNotFoundException();
        return post.get();
    }

    public List<Post> loadPosts(User author, int pageNumber) throws OutOfPostsException, UserNotFoundException {
        findUser(author.getId());
        Slice<Post> posts = postService.findByAuthorAndIsActiveTrue(author, PageRequest.of(pageNumber, 20));
        if (posts.isEmpty())
            throw new OutOfPostsException(posts);
        return posts.getContent();
    }

    public void likePost(Post post) throws PostMaxCharacterSizeExceededException, PostNotFoundException, InvalidPostNameException, InvalidPostTextException {
        post.like();
        postService.update(post);
    }

    public void unlikePost(Post post) throws PostMaxCharacterSizeExceededException, PostNotFoundException, InvalidPostNameException, InvalidPostTextException {
        post.unlike();
        postService.update(post);
    }

    public Post save(Post object) throws InvalidPostNameException, InvalidPostTextException, PostMaxCharacterSizeExceededException {
        return postService.save(object);
    }

    public Post update(Post object) throws PostNotFoundException, InvalidPostNameException, InvalidPostTextException, PostMaxCharacterSizeExceededException {
        return postService.update(object);
    }

    public void delete(Post object) throws PostNotFoundException {
        postService.delete(object);
    }

    public Comment findComment(Long id) throws CommentNotFoundException {
        Optional<Comment> comment = commentService.findById(id);
        if (comment.isEmpty())
            throw new CommentNotFoundException();
        return comment.get();
    }

    public List<Comment> loadComments(Postable postable, int pageNumber) throws PostNotFoundException, CommentNotFoundException {
        if (postable instanceof Post)
            findPost(postable.getId());
        else if (postable instanceof Comment)
            findComment(postable.getId());
        return commentService.findByPostableAndIsActiveTrue(postable, PageRequest.of(pageNumber, 50)).getContent();
    }

    public void likeComment(Comment comment) throws CommentMaxCharacterSizeExceededException, InvalidCommentException, CommentNotFoundException {
        comment.like();
        commentService.update(comment);
    }
    public void unlikeComment(Comment comment) throws CommentMaxCharacterSizeExceededException, InvalidCommentException, CommentNotFoundException {
        comment.unlike();
        commentService.update(comment);
    }

    public Page findPage(User user) throws PageNotFoundException {
        Optional<Page> page = pageService.findByUser(user);
        if (page.isEmpty())
            throw new PageNotFoundException();
        return page.get();
    }

    public Page findPage(String username) throws PageNotFoundException, UserNotFoundException {
        Optional<Page> page = pageService.findByUser(findUser(username));
        if (page.isEmpty())
            throw new PageNotFoundException();
        return page.get();
    }
}
