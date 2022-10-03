package com.jlvlg.pentagon.facade;

import com.jlvlg.pentagon.exceptions.*;
import com.jlvlg.pentagon.models.*;
import com.jlvlg.pentagon.security.SecurityUtils;
import com.jlvlg.pentagon.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

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
    @Autowired private ProfileServiceInterface profileService;
    @Autowired private ScoreServiceInterface scoreService;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired private SecurityUtils securityUtils;

    public User findUser(String username) throws UserNotFoundException {
        return userService.findByUsername(username);
    }

    public User findUser(Long id) throws UserNotFoundException {
        return userService.findById(id);
    }

    public Profile saveUser(User user) throws InvalidUsernameException, UsernameTakenException, InvalidProfileNameException {
        user.getAuth().setPassword(bCryptPasswordEncoder.encode(user.getAuth().getPassword()));
        User result = userService.save(user);
        return profileService.save(new Profile(result));
    }

    public User updateUser(User user) throws UsernameTakenException, InvalidUsernameException, UserNotFoundException {
        return userService.update(user);
    }

    public void deleteUser(User user) throws UserNotFoundException {
        try {
            profileService.delete(findProfile(user));
        } catch (ProfileNotFoundException e) {
            throw new RuntimeException(e);
        }
        userService.delete(user);
    }

    public void deleteUser(String username) throws UserNotFoundException {
        deleteUser(findUser(username));
    }

    public void followUser(User following, User followed) throws UserAlreadyFollowedException, UserNotFoundException, UsernameTakenException, InvalidUsernameException {
        if (following.follow(followed)) {
            followed.setFollowers(followed.getFollowers() + 1);
            updateUser(following);
            updateUser(followed);
        }
    }

    public void followUser(Long followingId, Long followedId) throws UserNotFoundException, UserAlreadyFollowedException {
        try {
            followUser(findUser(followingId), findUser(followedId));
        } catch (UsernameTakenException | InvalidUsernameException e) {
            throw new RuntimeException(e);
        }
    }

    public void followUser(String followingUsername, String followedUsername) throws UserNotFoundException, UserAlreadyFollowedException {
        try {
            followUser(findUser(followingUsername), findUser(followedUsername));
        } catch (UsernameTakenException | InvalidUsernameException e) {
            throw new RuntimeException(e);
        }
    }

    public void unfollowUser(User following, User followed) throws UserNotFoundException, UsernameTakenException, InvalidUsernameException, UserNotFollowedException {
        if (following.unfollow(followed)) {
            followed.setFollowers(followed.getFollowers() - 1);
            updateUser(following);
            updateUser(followed);
        }
    }

    public void unfollowUser(Long followingId, Long followedId) throws UserNotFoundException, UserNotFollowedException {
        try {
            unfollowUser(findUser(followingId), findUser(followedId));
        } catch (UsernameTakenException | InvalidUsernameException e) {
            throw new RuntimeException(e);
        }
    }

    public void unfollowUser(String followingUsername, String followedUsername) throws UserNotFoundException, UserNotFollowedException {
        try {
            unfollowUser(findUser(followingUsername), findUser(followedUsername));
        } catch (UsernameTakenException | InvalidUsernameException e) {
            throw new RuntimeException(e);
        }
    }

    public Post findPost(Long id) throws PostNotFoundException {
        return postService.findById(id);
    }

    public Post savePost(Post post) throws InvalidPostNameException, InvalidPostTextException, PostMaxCharacterSizeExceededException {
        return postService.save(post);
    }

    public Post updatePost(Post post) throws PostNotFoundException, InvalidPostNameException, InvalidPostTextException, PostMaxCharacterSizeExceededException {
        return postService.update(post);
    }

    public void deletePost(Post post) throws PostNotFoundException {
        postService.delete(post);
    }

    public List<Post> loadPosts(Long author) throws UserNotFoundException {
        User user = findUser(author);
        return postService.findByAuthor(user);
    }

    public List<Post> followingPosts(Long user) throws UserNotFoundException {
        return postService.followingPosts(findUser(user).getFollowing());
    }

    public void likePost(Post post) throws PostNotFoundException {
        Post _post = postService.findById(post.getId());
        _post.like();
        try {
            updatePost(_post);
        } catch (InvalidPostNameException | InvalidPostTextException | PostMaxCharacterSizeExceededException e) {
            throw new RuntimeException(e);
        }
    }

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

    public Comment findComment(Long id) throws CommentNotFoundException {
        return commentService.findById(id);
    }

    public Comment saveComment(Comment comment) throws InvalidCommentException, CommentMaxCharacterSizeExceededException {
        return commentService.save(comment);
    }

    public Comment updateComment(Comment comment) throws CommentNotFoundException, InvalidCommentException, CommentMaxCharacterSizeExceededException {
        return commentService.update(comment);
    }

    public void deleteComment(Comment comment) throws CommentNotFoundException {
        commentService.delete(comment);
    }

    public List<Comment> loadComments(Postable postable) throws PostNotFoundException, CommentNotFoundException {
        if (postable instanceof Post)
            findPost(postable.getId());
        else if (postable instanceof Comment)
            findComment(postable.getId());
        return commentService.findByPostable(postable);
    }

    public void likeComment(Comment comment) throws CommentNotFoundException {
        Comment _comment = findComment(comment.getId());
        _comment.like();
        try {
            updateComment(_comment);
        } catch (InvalidCommentException | CommentMaxCharacterSizeExceededException e) {
            throw new RuntimeException(e);
        }
    }

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

    public Profile findProfile(Long id) throws ProfileNotFoundException {
        return profileService.findById(id);
    }
    public Profile findProfile(User user) throws ProfileNotFoundException {
        return profileService.findByUser(user);
    }

    public Profile findProfile(String username) throws ProfileNotFoundException, UserNotFoundException {
        return profileService.findByUser(userService.findByUsername(username));
    }

    public List<Profile> searchProfiles(String text) throws ProfileNotFoundException {
        return profileService.search(text);
    }

    public Profile saveProfile(Profile profile) throws InvalidProfileNameException {
        return profileService.save(profile);
    }

    public Profile updateProfile(Profile profile) throws ProfileNotFoundException, InvalidProfileNameException {
        return profileService.update(profile);
    }

    public void deleteProfile(Profile profile) throws ProfileNotFoundException {
        profileService.delete(profile);
    }

    public void deleteProfile(User user) throws ProfileNotFoundException {
        profileService.delete(findProfile(user));
    }

    public void deleteProfile(String username) throws UserNotFoundException, ProfileNotFoundException {
        profileService.delete(findProfile(username));
    }

    public List<Modification> loadModifications(Postable postable) {
        return modificationService.findByPostable(postable);
    }
    public Modification saveModification(Modification modification) {
        return modificationService.save(modification);
    }

    public Modification updateModification(Modification modification) throws ModificationNotFoundException {
        return modificationService.update(modification);
    }

    public void deleteModification(Modification modification) throws ModificationNotFoundException {
        modificationService.delete(modification);
    }

    public Optional<Score> findScore(Profile profile, String category, User author) {
        return scoreService.findByProfileAndCategoryAndAuthor(profile, category, author);
    }

    public List<Score> loadScores(Profile profile, User author) {
        return scoreService.findByProfileAndAuthor(profile, author);
    }

    public void deleteScore(Score object) throws ScoreNotFoundException {
        scoreService.delete(object);
    }

    public Score saveScore(Score object) throws ScoreOutOfAllowedException {
        return scoreService.save(object);
    }

    public Score updateScore(Score object) throws ScoreOutOfAllowedException {
        return scoreService.update(object);
    }

    public List<Score> loadScores(Profile profile) {
        return scoreService.findByProfile(profile);
    }

    public Score vote(Long profileId, String category, Long authorId, int score) throws ScoreOutOfAllowedException, ProfileNotFoundException, UserNotFoundException {
        Profile profile = findProfile(profileId);
        User author = findUser(authorId);
        Score newScore = new Score(score, author, profile, category);
        Optional<Score> maybeScore = findScore(profile, category, author);
        if (maybeScore.isPresent()) {
            newScore = maybeScore.get();
            newScore.setScore(score);
            newScore = updateScore(newScore);
        } else {
            newScore = saveScore(newScore);
        }
        recalculateScores(profile, category);
        return newScore;
    }

    private void recalculateScores(Profile profile, String category) {
        List<Score> scores = scoreService.findByProfileAndCategory(profile, category);
        OptionalDouble maybeMean = scores.stream().mapToInt(Score::getScore).filter(x -> x != 0).average();
        double mean = maybeMean.isPresent() ? maybeMean.getAsDouble() : 0;
        switch (category) {
            case "appearance" -> profile.setAppearanceScore(mean);
            case "intelligence" -> profile.setIntelligenceScore(mean);
            case "character" -> profile.setCharacterScore(mean);
            case "humor" -> profile.setHumorScore(mean);
            case "responsibility" -> profile.setResponsibilityScore(mean);
        }
    }

    public Profile updateAuth(Auth oldAuth, Auth newAuth) throws UserNotFoundException, IncorrectPasswordException, UsernameTakenException, InvalidUsernameException {
        User user = findUser(oldAuth.getUsername());
        if (securityUtils.authorizeUser(oldAuth)) {
            user.setAuth(newAuth);
            updateUser(user);
        } else {
            throw new IncorrectPasswordException();
        }
        try {
            return findProfile(user);
        } catch (ProfileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
