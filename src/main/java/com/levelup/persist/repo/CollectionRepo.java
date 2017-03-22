package com.levelup.persist.repo;

import org.springframework.data.mongodb.core.MongoTemplate;

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
		if (!getMongoTemplate().collectionExists(getCollectionType())) {
			getMongoTemplate().createCollection(getCollectionType());
		}
	}

	/**
	 * A method that drops a collection if collection already exists
	 */
	public default void dropCollection() {
		if (getMongoTemplate().collectionExists(getCollectionType())) {
			getMongoTemplate().dropCollection(getCollectionType());
		}
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
