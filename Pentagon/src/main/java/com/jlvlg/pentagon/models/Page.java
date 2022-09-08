package com.jlvlg.pentagon.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.time.*;

/**@author Luann
 * 
 * Page Object Class: Inherits Followable Object Abstract Class and has an
 *  aggregation relationship with User Object Class much to much
 */

public class Page {
	private Long id;
	private List<Moderator> moderators;
	private String name;
	private String image;
	private String description;
	private ZonedDateTime creationDate;
		
	public Page(String name, String image, String description) {
		this(name);
		this.description = description;
		this.image = image;
	}
	
	public Page(String name) {
		this.moderators = new ArrayList<Moderator>();
		this.name = name;
		this.creationDate = ZonedDateTime.now();		
	}
	
	/**
	 * Method to add a moderator to a page
	 * @param Moderator moderator to be added to the page
	 * @return true to a successful operation
	 */
	
	public boolean addModerator (Moderator moderator) throws SameOrderModeratorException {
		if (moderator.getOrder() != 0)
			if (moderators.stream()
					.anyMatch(x -> x.getOrder() == moderator.getOrder()))
				throw new SameOrderModeratorException(this, moderator);
		return moderators.add(moderator);
	}
	
	/** 
	 * Method to remove a moderator from a page
	 * @param Moderator the moderator that will be removed 
	 * @return true to a successful operation
	 */
	
	public boolean removeModerator (Moderator moderator) {
		if (moderators.remove(moderator)) {
			moderators.stream()
				.filter(x -> x.getOrder() > moderator.getOrder())
				.forEach(x -> {
					try {
						x.promote();
					} catch (InvalidOrderModeratorException e) {
						/* Since this method already checks if moderators.remove is 
						 * successful we can assume that the removed moderator has a 
						 * order of >= 1, and since the only way of adding moderators 
						 * is through the addModerator method, which already checks if
						 * there's another moderator with the same priority order, and
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

	@Override
	public int hashCode() {
		return Objects.hash(creationDate, description, id, image, name, moderators);
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
		return Objects.equals(creationDate, other.creationDate) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Objects.equals(image, other.image)
				&& Objects.equals(name, other.name) && Objects.equals(moderators, other.moderators);
	}	
}
