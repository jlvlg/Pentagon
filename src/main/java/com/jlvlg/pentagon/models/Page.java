package com.jlvlg.pentagon.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import com.jlvlg.pentagon.exceptions.LeaderModeratorPresentException;
import com.jlvlg.pentagon.exceptions.ModeratorLimitExceededException;
import com.jlvlg.pentagon.exceptions.ModeratorNotFoundException;
import com.jlvlg.pentagon.exceptions.NoLeaderModeratorException;
import com.jlvlg.pentagon.exceptions.UserAlreadyModeratorException;
import com.jlvlg.pentagon.exceptions.ZeroModeratorsException;

import java.time.ZonedDateTime;

/**
 * Page Object Class: Inherits from the Followable Abstract Class and has an
 * aggregation relationship with Moderator of one to many
 * @author Luann
 * @author Lucas
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Page extends Followable {
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Moderator> moderators;
	private String name;
	private String image;
	private String description;
	private ZonedDateTime creationDate;
	private boolean archived;
	
	public Page() {
		this.creationDate = ZonedDateTime.now();
	}
		
	public Page(String name, String image, String description) {
		this(name, description);
		this.image = image;
	}
	
	public Page(String name, String description) {
		this(name);
		this.description = description;
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
		if (getLeaderModerator().isPresent()) {
			if (moderator.isLeader())
				throw new LeaderModeratorPresentException(this, moderator);
		} else {
			moderator.setLeader(true);
		}
		return moderators.add(moderator);
	}
	
	/** 
	 * Method to remove a moderator from a page
	 * @param moderator the moderator that will be removed 
	 * @return true if the operation was successful, else false
	 * @throws ZeroModeratorsException Tried to remove the page's only moderator
	 * @throws ModeratorNotFoundException The page could not find the moderator
	 * @throws NoLeaderModeratorException Tried to remove the page's leader moderator
	 */
	public boolean removeModerator(Moderator moderator) throws ZeroModeratorsException, ModeratorNotFoundException, NoLeaderModeratorException {
		if (moderators.size() <= 1)
			throw new ZeroModeratorsException(this, moderator);
		if (!moderators.contains(moderator))
			throw new ModeratorNotFoundException(this, moderator);
		if (moderator.equals(getLeaderModerator().get()))
			throw new NoLeaderModeratorException(this, moderator);
		return moderators.remove(moderator);
	}
	
	/**
	 * Promotes a moderator to leader
	 * @param moderator the moderator to be promoted
	 * @throws ModeratorNotFoundException The provided moderator is not a moderator of the page
	 */
	public void promoteModerator(Moderator moderator) throws ModeratorNotFoundException {
		Optional<Moderator> leader = getLeaderModerator();
		if (!moderators.contains(moderator))
			throw new ModeratorNotFoundException(this, moderator);
		if (leader.isPresent())
			leader.get().setLeader(false);
		moderator.setLeader(true);
	}
	
	/**
	 * Checks if an user is a moderator
	 * @param user the user to be authenticated
	 * @return true if the user is a moderator, else false
	 */
	public boolean authenticateUser(User user) {
		return getModeratorByUser(user).isPresent();
	}
	
	/**
	 * Checks if an user is the leader moderator
	 * @param user the user to be authenticated
	 * @return true if the user is the leader moderator, else false
	 */
	public boolean authenticateLeader(User user) {
		Optional<Moderator> moderator = getLeaderModerator();
		return moderator.isPresent() && moderator.get().getUser().equals(user);
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
}
