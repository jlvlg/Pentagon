package com.jlvlg.pentagon.facade;

import com.jlvlg.pentagon.exceptions.*;
import com.jlvlg.pentagon.models.*;
import com.jlvlg.pentagon.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired private CommentServiceInterface commentService;
    @Autowired private UserServiceInterface userService;
    @Autowired private ModificationServiceInterface modificationService;
    @Autowired private PostServiceInterface postService;
    @Autowired private PageServiceInterface pageService;
    @Autowired private ScoreServiceInterface scoreService;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Finds a user by username
     * @param username The user's username
     * @return A User
     * @throws UserNotFoundException User not found
     */
    public User findUser(String username) throws UserNotFoundException {
        return userService.findByUsername(username);
    }

    /**
     * Finds a user by id
     * @param id The user's id
     * @return A User
     * @throws UserNotFoundException User not found
     */
    public User findUser(Long id) throws UserNotFoundException {
        return userService.findById(id);
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
     * @param user
     * @throws InvalidUsernameException A username name cannot be null, empty, or contain spaces and/or special characters
     * @throws UsernameTakenException   Two users cannot have the same username
     */
    public User saveUser(User user) throws InvalidUsernameException, UsernameTakenException, InvalidPageNameException {
        user.getAuth().setPassword(bCryptPasswordEncoder.encode(user.getAuth().getPassword()));
        User result = userService.save(user);
        pageService.save(new Page(user));
        return result;
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

    public void deleteUser(String username) throws UserNotFoundException {
        userService.delete(findUser(username));
    }

    public void switchUserIsActive(User user) throws UserNotFoundException {
        User _user = findUser(user.getId());
        _user.getAuth().setActive(!user.getAuth().isActive());
        try {
            updateUser(_user);
        } catch (UsernameTakenException | InvalidUsernameException e) {
            throw new RuntimeException(e);
        }
    }

    public void followUser(User following, User followed) throws UserAlreadyFollowedException, UserNotFoundException, UsernameTakenException, InvalidUsernameException {
        if (following.follow(followed)) {
            followed.setFollowers(followed.getFollowers() + 1);
            updateUser(following);
            updateUser(followed);
        }
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
        followUser(findUser(followingId), findUser(followedId));
    }

    public void followUser(String followingUsername, String followedUsername) throws UserNotFoundException, UsernameTakenException, InvalidUsernameException, UserAlreadyFollowedException {
        followUser(findUser(followingUsername), findUser(followedUsername));
    }

    public void unfollowUser(User following, User followed) throws UserNotFoundException, UsernameTakenException, InvalidUsernameException, UserNotFollowedException {
        if (following.unfollow(followed)) {
            followed.setFollowers(followed.getFollowers() - 1);
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
    public void unfollowUser(Long followingId, Long followedId) throws UserNotFoundException, UsernameTakenException, InvalidUsernameException, UserNotFollowedException {
        unfollowUser(findUser(followingId), findUser(followedId));
    }

    public void unfollowUser(String followingUsername, String followedUsername) throws UserNotFoundException, UsernameTakenException, InvalidUsernameException, UserNotFollowedException {
        unfollowUser(findUser(followingUsername), findUser(followedUsername));
    }

    /**
     * Finds a post by ID
     * @param id The post's ID
     * @return The post
     * @throws PostNotFoundException Post not found
     */
    public Post findPost(Long id) throws PostNotFoundException {
        return postService.findById(id);
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

    public void switchPostIsActive(Post post) throws PostNotFoundException {
        Post _post = findPost(post.getId());
        _post.setActive(!_post.isActive());
        try {
            updatePost(_post);
        } catch (InvalidPostNameException | InvalidPostTextException | PostMaxCharacterSizeExceededException e) {
            throw new RuntimeException(e);
        }
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
     * @throws PostNotFoundException
     */
    public void likePost(Post post) throws PostNotFoundException {
        Post _post = postService.findById(post.getId());
        _post.like();
        try {
            updatePost(_post);
        } catch (InvalidPostNameException | InvalidPostTextException | PostMaxCharacterSizeExceededException e) {
            throw new RuntimeException(e);
        }
    }
    
    /* Unlikes a post
     * @param post
     * @throws PostMaxCharaacterSizeExceededException
     * @throws PostNotFoundException
     * @throws InvalidPostNameException
     * @throws InvalidPostTextException
     */
    
    public void unlikePost(Post post) throws PostNotFoundException {
        Post _post = postService.findById(post.getId());
        _post.unlike();
        try {
            updatePost(_post);
        } catch (InvalidPostNameException | InvalidPostTextException | PostMaxCharacterSizeExceededException e) {
            throw new RuntimeException(e);
        }
    }
    

    public void editPost(Post post) throws PostMaxCharacterSizeExceededException, PostNotFoundException, InvalidPostNameException, InvalidPostTextException {
        saveModification(new Modification(post, post.getImage(), post.getTitle(), post.getText()));
        updatePost(post);
    }

    public boolean turnPostVisibleTo(User user, Post post) throws PostVisibilityException, PostNotFoundException {
        Post _post = postService.findById(post.getId());
        boolean result = _post.turnVisibleTo(user);
        if (result) {
            try {
                updatePost(_post);
            } catch (InvalidPostNameException | InvalidPostTextException |
                     PostMaxCharacterSizeExceededException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public boolean turnPostInvisibleTo(User user, Post post) throws PostVisibilityException, PostNotFoundException {
        Post _post = postService.findById(post.getId());
        boolean result = _post.turnInvisibleTo(user);
        if (result) {
            try {
                updatePost(_post);
            } catch (InvalidPostNameException | InvalidPostTextException |
                     PostMaxCharacterSizeExceededException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
    
    /* Find a post by ID
     * @param id
     * @return the comment
     * @throws CommentNotFoundException
     */

    public Comment findComment(Long id) throws CommentNotFoundException {
        return commentService.findById(id);
    }
    
    /*
     * Save a comment in the database
     * @param comment The comment to be saved
     * @return the save comment
     * @throws InvalidCommentException
     * @throws CommentMaxCharacterExceededException
     */

    public Comment saveComment(Comment comment) throws InvalidCommentException, CommentMaxCharacterSizeExceededException {
        return commentService.save(comment);
    }
    
    /*
     * Updates a comment in the database
     * @param comment The comment to be updated
     * @return The update comment 
     * @throws CommentNotFoundException
     * @throws InvalidCommentException
     * @throws CommentMaxCharacterSizeExceededException
     */
    
    public Comment updateComment(Comment comment) throws CommentNotFoundException, InvalidCommentException, CommentMaxCharacterSizeExceededException {
        return commentService.update(comment);
    }
    
    /*
     *Deletes a comment from the database
     *@param comment
     *@throws CommentNotFoundException
     */

    public void deleteComment(Comment comment) throws CommentNotFoundException {
        commentService.delete(comment);
    }
    
    /*
     * Loads the comments from a post
     * @param postable
     * @param page number
     * @return A list of comments by post
     * @throws PostNotFoundException
     * @throws CommentNotFoundException
     */
    
    public List<Comment> loadComments(Postable postable, int pageNumber) throws PostNotFoundException, CommentNotFoundException {
        if (postable instanceof Post)
            findPost(postable.getId());
        else if (postable instanceof Comment)
            findComment(postable.getId());
        return commentService.findByPostableAndIsActiveTrue(postable, PageRequest.of(pageNumber, 50)).getContent();
    }
    
    /*
     * Likes a comment 
     * @param comment The comment to be liked
     * @throws CommentMaxCharacterSizeExceededException
     * @throws InvalidCommentException
     * @throws CommentNotFoundException
     */

    public void likeComment(Comment comment) throws CommentNotFoundException {
        Comment _comment = findComment(comment.getId());
        _comment.like();
        try {
            updateComment(_comment);
        } catch (InvalidCommentException | CommentMaxCharacterSizeExceededException e) {
            throw new RuntimeException(e);
        }
    }
    
    /*
     * Unlikes a comment 
     * @param comment The comment to be liked
     * @throws CommentMaxCharacterSizeExceededException
     * @throws InvalidCommentException
     * @throws CommentNotFoundException
     */
    
    public void unlikeComment(Comment comment) throws CommentNotFoundException {
        Comment _comment = findComment(comment.getId());
        _comment.unlike();
        try {
            updateComment(_comment);
        } catch (InvalidCommentException | CommentMaxCharacterSizeExceededException e) {
            throw new RuntimeException(e);
        }
    }
    
    

    public void editComment(Comment comment) throws CommentMaxCharacterSizeExceededException, InvalidCommentException, CommentNotFoundException {
        saveModification(new Modification(comment, null, comment.getText()));
        updateComment(comment);
    }

    public Page findPage(Long id) throws PageNotFoundException {
        return pageService.findById(id);
    }
    
    /*
     * Find a page by user
     * @param user The user to be found
     * @throws PageNotFoundException
     */

    public Page findPage(User user) throws PageNotFoundException {
        return pageService.findByUser(user);
    }
    
    /*
     * Find a page by username
     * @param username The username to be found
     * @throws PageNotFoundException
     * @throws UserNotFoundException
     */

    public Page findPage(String username) throws PageNotFoundException, UserNotFoundException {
        return pageService.findByUser(userService.findByUsername(username));
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
     * @param page The page to be updated
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

    public void deletePage(User user) throws PageNotFoundException {
        pageService.delete(findPage(user));
    }

    public void deletePage(String username) throws UserNotFoundException, PageNotFoundException {
        pageService.delete(findPage(username));
    }

    public void switchPageIsActive(Page page) throws PageNotFoundException {
        Page _page = findPage(page.getId());
        _page.setActive(!_page.isActive());
        try {
            updatePage(_page);
        } catch (InvalidPageNameException e) {
            throw new RuntimeException(e);
        }
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
     * @param modification The modification to be dropped
     * @throws ModificationNotFoundException Modification not found
     */
    public void deleteModification(Modification modification) throws ModificationNotFoundException {
        modificationService.delete(modification);
    }

    public Optional<Score> findScore(User user, String category, User author) {
        return scoreService.findByPage_UserAndCategoryAndAuthor(user, category, author);
    }

    /**
     * Permanently drops an object from the database
     *
     * @param object the object to be dropped
     */
    public void deleteScore(Score object) throws Exception {
        scoreService.delete(object);
    }

    public Score saveScore(Score object) throws ScoreOutOfAllowedException {
        return scoreService.save(object);
    }

    public Score updateScore(Score object) throws ScoreOutOfAllowedException {
        return scoreService.update(object);
    }

    public List<Score> loadScores(User user, String category) {
        return scoreService.findByUser(user);
    }

    public void vote(User user, String category, User author, int score) throws ScoreOutOfAllowedException, PageNotFoundException {
        Optional<Score> maybeScore = findScore(user, category, author);
        if (maybeScore.isPresent()) {
            Score foundScore = maybeScore.get();
            foundScore.setScore(score);
            updateScore(foundScore);
        }
        Score newScore = saveScore(new Score(score, author, findPage(user), category));
//        recalculateScores(user);
    }
}
