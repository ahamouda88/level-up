package com.levelup.persist.repo;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.DBCollection;

/**
 * An interface to manage collections
 * 
 * @author ahamouda
 *
 */
public interface CollectionRepo {

	/**
	 * A method that creates a collection if collection doesn't already exists
	 */
	public default void createCollection() {
		if (!collectionExists()) {
			getMongoTemplate().createCollection(getCollectionType());
		}
	}

	/**
	 * A method that drops a collection if collection already exists
	 */
	public default void dropCollection() {
		if (collectionExists()) {
			getMongoTemplate().dropCollection(getCollectionType());
		}
	}

	/**
	 * A method that checks if collection exists or not
	 * 
	 * @return true if collection exists, false otherwise
	 */
	public default boolean collectionExists() {
		return getMongoTemplate().collectionExists(getCollectionType());
	}

	/**
	 * A method that returns a {@link DBCollection} given the collection name
	 * 
	 * @param collectionName
	 *            a string representing the name of the collection needed
	 * @return a {@link DBCollection} based on the given collection name
	 */
	public default DBCollection getCollection(String collectionName) {
		return getMongoTemplate().getCollection(collectionName);
	}

	/**
	 * A method that returns a {@link MongoTemplate}, and it should be implemented by concrete repositories
	 * 
	 * @return a {@link MongoTemplate} object
	 */
	public MongoTemplate getMongoTemplate();

	/**
	 * A method that returns the class type of the repository collection
	 * 
	 * @return the class type of the repo collection
	 */
	public Class<?> getCollectionType();

}
