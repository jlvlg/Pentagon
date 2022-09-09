package com.jlvlg.pentagon.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.jlvlg.pentagon.exceptions.InvalidModeratorOrderException;
import com.jlvlg.pentagon.exceptions.ModeratorLimitExceededException;
import com.jlvlg.pentagon.exceptions.ModeratorNotFoundException;
import com.jlvlg.pentagon.exceptions.NonSequentialModeratorOrderException;
import com.jlvlg.pentagon.exceptions.SameModeratorOrderException;
import com.jlvlg.pentagon.exceptions.UserAlreadyModeratorException;
import com.jlvlg.pentagon.exceptions.UserIsNotModeratorException;
import com.jlvlg.pentagon.exceptions.ZeroModeratorsException;

import java.time.ZonedDateTime;

/**
 * Page Object Class: Inherits Followable Object Abstract Class and has an
 * aggregation relationship with User Object Class much to much
 * @author Luann
 * @author Lucas
 */

public class Page {
	private Long id;
	private List<Moderator> moderators;
	private String name;
	private String image;
	private String description;
	private ZonedDateTime creationDate;
	private boolean active;
	private boolean archived;
		
	public Page(String name, String image, String description) {
		this(name);
		this.description = description;
		this.image = image;
	}
	
	public Page(String name) {
		this.moderators = new ArrayList<Moderator>();
		this.name = name;
		this.creationDate = ZonedDateTime.now();
		this.active = true;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(active, archived, creationDate, description, id, image, moderators, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Page other = (Page) obj;
		return active == other.active && archived == other.archived && Objects.equals(creationDate, other.creationDate)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(image, other.image) && Objects.equals(moderators, other.moderators)
				&& Objects.equals(name, other.name);
	}

	/**
	 * Method to add a moderator to a page
	 * @param moderator moderator to be added to the page
	 * @return true if the operation was successful, else false
	 * @throws NonSequentialModeratorOrderException The moderator to be added doesn't follow the current order sequence
	 * @throws SameModeratorOrderException The page already has a moderator with this priority order
	 * @throws ModeratorLimitExceededException The page already has 5 moderators
	 * @throws UserAlreadyModeratorException The user is already a moderator
	 */
	public boolean addModerator(Moderator moderator) throws NonSequentialModeratorOrderException, SameModeratorOrderException, ModeratorLimitExceededException, UserAlreadyModeratorException {
		if (moderators.size() >= 5)
			throw new ModeratorLimitExceededException(this, moderator);
		if (getModeratorByUser(moderator.getUser()).isPresent())
			throw new UserAlreadyModeratorException(moderator.getUser(), this, moderator);
		if (moderator.getOrder() != 0) {
			if (moderators.stream()
					.anyMatch(x -> x.getOrder() == moderator.getOrder()))
				throw new SameModeratorOrderException(this, moderator);
			if (moderators.stream()
					.mapToInt(Moderator::getOrder).max().orElse(0) + 1 < moderator.getOrder())
				throw new NonSequentialModeratorOrderException(this, moderator);
		}
		moderator.setOrder(moderators.stream()
				.mapToInt(Moderator::getOrder).max().orElse(0) + 1);
		return moderators.add(moderator);
	}
	
	/** 
	 * Method to remove a moderator from a page
	 * @param moderator the moderator that will be removed 
	 * @return true if the operation was successful, else false
	 * @throws ZeroModeratorsException Tried to remove the page's only moderator
	 * @throws ModeratorNotFoundException The page could not find the moderator
	 */
	public boolean removeModerator(Moderator moderator) throws ZeroModeratorsException, ModeratorNotFoundException {
		if (moderators.size() <= 1)
			throw new ZeroModeratorsException(this, moderator);
		if (!moderators.contains(moderator))
			throw new ModeratorNotFoundException(this, moderator);
		if (moderators.remove(moderator)) {
			moderators.stream()
				.filter(x -> x.getOrder() > moderator.getOrder())
				.forEach(x -> {
					try {
						x.promote();
					} catch (InvalidModeratorOrderException e) {
						/* Since this method already checks if moderators.remove is 
						 * successful we can assume that the removed moderator has a 
						 * order of >= 1, and since the only way of adding moderators 
						 * is through the addModerator method, which already makes sure
						 * that the moderators order attributes are sequential, and
						 * we're filtering for moderators with lower priority order
						 * (lower priority = higher number) we can assume that 
						 * InvalidOrderModeratorException will never be thrown here.
						 * As such, it will not be dealt with.
						 */
						e.printStackTrace();
					}
				});
			return true;
		}
		return false;
	}
	
	/**
	 * Swaps the moderator's order with the next one with higher priority
	 * @param moderator the moderator to be promoted
	 * @throws ModeratorNotFoundException Could not find the moderator in the page
	 * @throws InvalidModeratorOrderException The moderator already has the highest priority
	 */
	public void promoteModerator(Moderator moderator) throws ModeratorNotFoundException, InvalidModeratorOrderException {
		if (!moderators.contains(moderator))
			throw new ModeratorNotFoundException(this, moderator);
		moderator.promote();
		moderators.stream()
			.filter(x -> !x.equals(moderator) && x.getOrder() == moderator.getOrder())
			.findFirst().get().demote();
	}
	
	/**
	 * Swaps the moderator's order with the next one with lower priority
	 * @param moderator the moderator to be demoted
	 * @throws ModeratorNotFoundException
	 * @throws NonSequentialModeratorOrderException 
	 */
	public void demoteModerator(Moderator moderator) throws NonSequentialModeratorOrderException, ModeratorNotFoundException {
		if (!moderators.contains(moderator))
			throw new ModeratorNotFoundException(this, moderator);
		if (moderator.getOrder() == moderators.stream()
				.mapToInt(Moderator::getOrder).max().orElse(moderator.getOrder()))
			throw new NonSequentialModeratorOrderException(this, moderator);
		moderator.demote();
		try {
			moderators.stream()
				.filter(x -> !x.equals(moderator) && x.getOrder() == moderator.getOrder())
				.findFirst().get().promote();
		} catch (InvalidModeratorOrderException e) {
			/*
			 * As the code already ensures correct ordering and checks if
			 * moderator does not have the lowest priority order, this exception
			 * shall not be thrown, as such it will not be dealt with.
			 */
			e.printStackTrace();
		}
	}
	
	public Optional<Moderator> getModeratorByUser(User user) {
		return moderators.stream()
				.filter(x -> x.getUser().equals(user))
				.findFirst();
	}
	
	/**
	 * Tries to find a moderator instance that references user and returns its order
	 * @param user the user to be authenticated
	 * @return The moderator's clearance order
	 * @throws UserIsNotModeratorException The user is not a moderator
	 */
	public int authenticateUser(User user) throws UserIsNotModeratorException {
		Optional<Moderator> moderator = getModeratorByUser(user);
		if (moderator.isEmpty())
			throw new UserIsNotModeratorException(user, this);
		return moderator.get().getOrder();
	}
	
	/**
	 * Switches archived flag
	 */
	public void archive() {
		archived = !archived;
	}
	
	/**
	 * Switches active flag
	 */
	public void delete() {
		active = !active;
	}
}
