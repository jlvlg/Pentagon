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
     * Saves a user into the database
     *
     * @param user
     * @throws InvalidUsernameException A username name cannot be null, empty, or contain spaces and/or special characters
     * @throws UsernameTakenException   Two users cannot have the same username
     */
    public User saveUser(User user) throws InvalidUsernameException, UsernameTakenException {
        return userService.save(user);
    }

    /**
     * Updates a user in the database
     *
     * @param user
     * @throws InvalidUsernameException A username name cannot be null, empty, or contain spaces and/or special characters
     * @throws UsernameTakenException   Two users cannot have the same username
     * @throws UserNotFoundException    User not found
     */
    public User updateUser(User user) throws UsernameTakenException, InvalidUsernameException, UserNotFoundException {
        return userService.update(user);
    }

    /**
     * Permanently drops a user from the database
     *
     * @param user
     * @throws UserNotFoundException User not found
     */
    public void deleteUser(User user) throws UserNotFoundException {
        userService.delete(user);
    }

    public void switchUserIsActive(User user) throws UserNotFoundException, UsernameTakenException, InvalidUsernameException {
        user.setActive(!user.isActive());
        updateUser(user);
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

    /**
     * Finds a post by ID
     * @param id The post's ID
     * @return The post
     * @throws PostNotFoundException Post not found
     */
    public Post findPost(Long id) throws PostNotFoundException {
        Optional<Post> post = postService.findById(id);
        if (post.isEmpty())
            throw new PostNotFoundException();
        return post.get();
    }

    /**
     * Saves a post to the database
     * @param post The post to be saved
     * @return The saved post
     * @throws InvalidPostNameException The post name cannot be null or empty
     * @throws InvalidPostTextException The post text cannot be null or empty
     * @throws PostMaxCharacterSizeExceededException The post text cannot have more than 1500 characters
     */
    public Post savePost(Post post) throws InvalidPostNameException, InvalidPostTextException, PostMaxCharacterSizeExceededException {
        return postService.save(post);
    }

    /**
     * Updates a post in the database
     * @param post The post to be updated
     * @return The updated post
     * @throws PostNotFoundException Post not found
     * @throws InvalidPostNameException The post name cannot be null or empty
     * @throws InvalidPostTextException The post text cannot be null or empty
     * @throws PostMaxCharacterSizeExceededException The post text cannot have more than 1500 characters
     */
    public Post updatePost(Post post) throws PostNotFoundException, InvalidPostNameException, InvalidPostTextException, PostMaxCharacterSizeExceededException {
        return postService.update(post);
    }

    /**
     * Deletes a post from the database
     * @param post
     * @throws PostNotFoundException Post not found
     */
    public void deletePost(Post post) throws PostNotFoundException {
        postService.delete(post);
    }

    public void switchPostIsActive(Post post) throws PostMaxCharacterSizeExceededException, PostNotFoundException, InvalidPostNameException, InvalidPostTextException {
        post.setActive(!post.isActive());
        updatePost(post);
    }

    /**
     * Loads posts by author in groups of 20
     * @param author The post's author
     * @param pageNumber The number of the slice of posts
     * @return A list of posts
     * @throws OutOfPostsException There are no posts left
     * @throws UserNotFoundException Author not found
     */
    public List<Post> loadPosts(User author, int pageNumber) throws OutOfPostsException, UserNotFoundException {
        findUser(author.getId());
        Slice<Post> posts = postService.findByAuthorAndIsActiveTrue(author, PageRequest.of(pageNumber, 20));
        if (posts.isEmpty())
            throw new OutOfPostsException(posts);
        return posts.getContent();
    }

    /**
     * Likes a post
     * @param post
     * @throws PostMaxCharacterSizeExceededException
     * @throws PostNotFoundException
     * @throws InvalidPostNameException
     * @throws InvalidPostTextException
     */
    public void likePost(Post post) throws PostMaxCharacterSizeExceededException, PostNotFoundException, InvalidPostNameException, InvalidPostTextException {
        post.like();
        updatePost(post);
    }

    public void unlikePost(Post post) throws PostMaxCharacterSizeExceededException, PostNotFoundException, InvalidPostNameException, InvalidPostTextException {
        post.unlike();
        updatePost(post);
    }

    public void editPost(Post post, String image, String title, String text) throws PostMaxCharacterSizeExceededException, PostNotFoundException, InvalidPostNameException, InvalidPostTextException {
        if (title == null || title.isBlank())
            throw new InvalidPostNameException(post);
        if (text == null || text.isBlank())
            throw new InvalidPostTextException(post);
        saveModification(new Modification(post, post.getImage(), post.getTitle(), post.getText()));
        post.setImage(image);
        post.setTitle(title);
        post.setText(text);
        updatePost(post);
    }

    public boolean turnPostVisibleTo(Post post, User user) throws PostVisibilityException, PostMaxCharacterSizeExceededException, PostNotFoundException, InvalidPostNameException, InvalidPostTextException {
        boolean result = post.turnVisibleTo(user);
        if (result) {
            updatePost(post);
        }
        return result;
    }

    public boolean turnPostInvisibleTo(Post post, User user) throws PostVisibilityException, PostMaxCharacterSizeExceededException, PostNotFoundException, InvalidPostNameException, InvalidPostTextException {
        boolean result = post.turnInvisibleTo(user);
        if (result) {
            updatePost(post);
        }
        return result;
    }

    public Comment findComment(Long id) throws CommentNotFoundException {
        Optional<Comment> comment = commentService.findById(id);
        if (comment.isEmpty())
            throw new CommentNotFoundException();
        return comment.get();
    }

    public Comment saveComment(Comment object) throws InvalidCommentException, CommentMaxCharacterSizeExceededException {
        return commentService.save(object);
    }

    public Comment updateComment(Comment object) throws CommentNotFoundException, InvalidCommentException, CommentMaxCharacterSizeExceededException {
        return commentService.update(object);
    }

    public void deleteComment(Comment object) throws CommentNotFoundException {
        commentService.delete(object);
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
        updateComment(comment);
    }
    public void unlikeComment(Comment comment) throws CommentMaxCharacterSizeExceededException, InvalidCommentException, CommentNotFoundException {
        comment.unlike();
        updateComment(comment);
    }

    public void editComment(Comment comment, String text) throws CommentMaxCharacterSizeExceededException, InvalidCommentException, CommentNotFoundException {
        if (text == null || text.isBlank())
            throw new InvalidCommentException(comment);
        saveModification(new Modification(comment, null, comment.getText()));
        comment.setText(text);
        updateComment(comment);
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

    /**
     * Saves a page to the database
     *
     * @param page The page to be saved
     * @throws InvalidPageNameException A page name cannot be null, empty, or contain special characters
     */
    public Page savePage(Page page) throws InvalidPageNameException {
        return pageService.save(page);
    }

    /**
     * Updates a page in the database
     *
     * @param page The paeg to be updated
     * @throws PageNotFoundException    Page not found
     * @throws InvalidPageNameException A page name cannot be null, empty, or contain special characters
     */
    public Page updatePage(Page page) throws PageNotFoundException, InvalidPageNameException {
        return pageService.update(page);
    }

    /**
     * Permanently drops a page from the database
     *
     * @param page The page to be deleted
     * @throws PageNotFoundException Page not found
     */
    public void deletePage(Page page) throws PageNotFoundException {
        pageService.delete(page);
    }

    public List<Modification> loadModifications(Postable postable) {
        return modificationService.findByPostable(postable);
    }

    /**
     * Saves a modification into the database
     *
     * @param modification The modification to be saved
     * @return The saved modification
     */
    public Modification saveModification(Modification modification) {
        return modificationService.save(modification);
    }

    /**
     * Updates a modification in the database
     *
     * @param modification The modification to be updated
     * @return The updated modification
     * @throws ModificationNotFoundException Modification not found
     */
    public Modification updateModification(Modification modification) throws ModificationNotFoundException {
        return modificationService.update(modification);
    }

    /**
     * Permanently drops a modification from the database
     *
     * @param modification The modification to be dropped
     * @throws ModificationNotFoundException Modification not found
     */
    public void deleteModification(Modification modification) throws ModificationNotFoundException {
        modificationService.delete(modification);
    }
}
