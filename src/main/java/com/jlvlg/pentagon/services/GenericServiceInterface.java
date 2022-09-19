package com.jlvlg.pentagon.services;

import java.util.Optional;

/**
 * Defines basic CRUD functionalities for services
 * @author Lucas
 *
 * @param <T> Interface type
 * @param <ID> Identifier type
 */
public interface GenericServiceInterface<T, ID>{
	/**
	 * Saves an object into the database
	 * @param object the object to be saved
	 * @return the saved object
	 */
	T save(T object) throws Exception;
	
	/**
	 * Updates an object in the database
	 * @param object the object to be updated
	 * @return the updated object
	 */
	T update(T object) throws Exception;
	
	/**
	 * Permanently drops an object from the database
	 * @param object the object to be dropped
	 */
	void delete(T object) throws Exception;
	
	/**
	 * Finds an object by id
	 * @param id the object's id
	 * @return An Optional that might contain the object
	 */
	Optional<T> findById(ID id);
}
