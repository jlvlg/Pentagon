package com.jlvlg.pentagon.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.jlvlg.pentagon.exceptions.LeaderModeratorPresentException;
import com.jlvlg.pentagon.exceptions.ModeratorLimitExceededException;
import com.jlvlg.pentagon.exceptions.ModeratorNotFoundException;
import com.jlvlg.pentagon.exceptions.RemovingLeaderModeratorException;
import com.jlvlg.pentagon.exceptions.UserAlreadyModeratorException;
import com.jlvlg.pentagon.exceptions.ZeroModeratorsException;

import java.time.ZonedDateTime;

/**
 * Page Object Class: Inherits Followable Object Abstract Class and has an
 * aggregation relationship with User Object Class much to much
 * @author Luann
 * @author Lucas
 */

@Entity
public class Page extends Followable {
	@OneToMany(cascade = CascadeType.ALL)
	private List<Moderator> moderators;
	private String name;
	private String image;
	private String description;
	private ZonedDateTime creationDate;
	private boolean archived;
	
	public Page() {}
		
	public Page(String name, String image, String description) {
		this(name);
		this.description = description;
		this.image = image;
	}
	
	public Page(String name) {
		super();
		this.moderators = new ArrayList<Moderator>();
		this.name = name;
		this.creationDate = ZonedDateTime.now();
		this.archived = false;
	}
	
	public List<Moderator> getModerators() {
		return moderators;
	}

	public ZonedDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(ZonedDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public void setModerators(List<Moderator> moderators) {
		this.moderators = moderators;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(archived, creationDate, description, image, moderators, name);
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
		Page other = (Page) obj;
		return archived == other.archived && Objects.equals(creationDate, other.creationDate)
				&& Objects.equals(description, other.description) && Objects.equals(image, other.image)
				&& Objects.equals(moderators, other.moderators) && Objects.equals(name, other.name);
	}

	/**
	 * Method to add a moderator to a page
	 * @param moderator moderator to be added to the page
	 * @return true if the operation was successful, else false
	 * @throws LeaderModeratorPresentException Tried to add a leader moderator when there's already one present
	 * @throws ModeratorLimitExceededException Tried to add more than 5 moderators to a page
	 * @throws UserAlreadyModeratorException The user is already a moderator
	 */
	public boolean addModerator(Moderator moderator) throws ModeratorLimitExceededException, UserAlreadyModeratorException,  LeaderModeratorPresentException {
		if (moderators.size() >= 5)
			throw new ModeratorLimitExceededException(this, moderator);
		if (getModeratorByUser(moderator.getUser()).isPresent())
			throw new UserAlreadyModeratorException(moderator.getUser(), this, moderator);
		if (moderator.isLeader())
			if (getLeaderModerator().isPresent())
				throw new LeaderModeratorPresentException(this, moderator);
		return moderators.add(moderator);
	}
	
	/** 
	 * Method to remove a moderator from a page
	 * @param moderator the moderator that will be removed 
	 * @return true if the operation was successful, else false
	 * @throws ZeroModeratorsException Tried to remove the page's only moderator
	 * @throws ModeratorNotFoundException The page could not find the moderator
	 * @throws RemovingLeaderModeratorException Tried to remove the page's leader moderator
	 */
	public boolean removeModerator(Moderator moderator) throws ZeroModeratorsException, ModeratorNotFoundException, RemovingLeaderModeratorException {
		if (moderators.size() <= 1)
			throw new ZeroModeratorsException(this, moderator);
		if (!moderators.contains(moderator))
			throw new ModeratorNotFoundException(this, moderator);
		if (moderator.equals(getLeaderModerator().get()))
			throw new RemovingLeaderModeratorException(this, moderator);
		return moderators.remove(moderator);
	}
	
	/**
	 * Promotes a moderator to leader
	 * @param moderator the moderator to be promoted
	 * @throws LeaderModeratorPresentException There is already a moderator present on the page
	 * @throws ModeratorNotFoundException The provided moderator is not a moderator of the page
	 */
	public void promoteModerator(Moderator moderator) throws LeaderModeratorPresentException, ModeratorNotFoundException {
		if (getLeaderModerator().isPresent())
			throw new LeaderModeratorPresentException(this, moderator);
		if (!moderators.contains(moderator))
			throw new ModeratorNotFoundException(this, moderator);
		moderator.setLeader(true);
	}
	
	/**
	 * Tries to find a moderator instance that references user and returns its order
	 * @param user the user to be authenticated
	 * @return true if the user is a moderator, else false
	 */
	public boolean authenticateUser(User user) {
		return getModeratorByUser(user).isPresent();
	}
	
	public Optional<Moderator> getModeratorByUser(User user) {
		return moderators.stream()
				.filter(x -> x.getUser().equals(user))
				.findFirst();
	}
	
	public Optional<Moderator> getLeaderModerator() {
		return moderators.stream()
				.filter(x -> x.isLeader())
				.findFirst();
	}
	
	/**
	 * Switches archived flag
	 */
	public void archive() {
		archived = !archived;
	}
}
