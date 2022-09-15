package com.jlvlg.pentagon.services;

public interface GenericServiceInterface<T, ID>{
	/**
	 * Saves an object into the database
	 * @param object the object to be saved
	 * @return the saved object
	 * @throws Exception 
	 */
	T save(T object) throws Exception;
	T findById(ID id);
	T update(T object);
	T remove(T object);
}
