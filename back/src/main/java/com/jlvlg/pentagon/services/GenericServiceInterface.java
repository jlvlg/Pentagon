package com.jlvlg.pentagon.services;

import com.jlvlg.pentagon.exceptions.*;

import java.util.Optional;

public interface GenericServiceInterface<T, ID>{

	T save(T object) throws Exception;

	T update(T object) throws Exception;

	void delete(T object) throws Exception;

	T findById(ID id) throws ObjectNotFoundException;
}
