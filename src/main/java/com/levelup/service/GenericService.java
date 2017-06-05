package com.levelup.service;

import java.util.List;

/**
 * An interface to manage and apply the logic for performing the generic operations on an entity
 * 
 * @author ahamouda
 *
 */
public interface GenericService<T, E> {

	/**
	 * A method that stores an object in the database
	 * 
	 * @param obj
	 *            the object to be saved
	 */
	public void save(T obj);

	/**
	 * A method that deletes an object from the database
	 * 
	 * @param obj
	 *            the object to be deleted
	 */
	public void delete(T obj);

	/**
	 * A method that deletes all the objects from the database
	 */
	public void deleteAll();

	/**
	 * A method that updates an object in the database
	 * 
	 * @param obj
	 *            the object to be updated
	 */
	public void update(T obj);

	/**
	 * A method that finds an object based on the given key
	 * 
	 * @param id
	 *            the id of the entity
	 * @return the object that matches the given Id, or <i><b>null</b></i> if id is invalid
	 */
	public T find(E id);

	/**
	 * A method that finds all objects
	 * 
	 * @return a list of objects
	 */
	public List<T> findAll();
}
