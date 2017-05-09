package com.levelup.persist.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.DBCollection;

/**
 * An interface for managing collections
 * 
 * @author ahamouda
 *
 */
public interface CollectionDao<T, E extends Serializable> {

	/**
	 * A method that creates a collection if collection doesn't already exist
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
	 * A method that inserts a document to the collection
	 * 
	 * @param document
	 *            the document to be stored in the collection
	 */
	public default void insertDocument(T document) {
		try {
			getMongoTemplate().insert(document);
		} catch (DuplicateKeyException ex) {
			throw new DuplicateKeyException("Key exists and can't insert the following document: " + document);
		}
	}

	/**
	 * A method that either updates a document if already exists, or creates a new one if documents doens't exist
	 * 
	 * @param document
	 *            the document to be updated/stored in the collection
	 */
	public default void upsertDocument(T document) {
		getMongoTemplate().save(document);
	}

	/**
	 * A method that finds document based on Id
	 * 
	 * @param id
	 *            the id of the document
	 * @return the document that matches the given Id
	 */
	public default T findById(E id) {
		return getMongoTemplate().findById(id, getCollectionType());
	}

	/**
	 * A method that search the collection based on the given query
	 * 
	 * @param query
	 *            a {@link Query} object
	 * @return a list of filtered documents
	 */
	public default List<T> search(Query query) {
		return getMongoTemplate().find(query, getCollectionType());
	}

	/**
	 * A method that removes a document from the collection
	 * 
	 * @param document
	 *            the document to be stored in the collection
	 */
	public default void removeDocument(T document) {
		getMongoTemplate().remove(document);
	}

	/**
	 * A method that returns all documents of a collection
	 * 
	 * @return a list of documents
	 */
	public default List<T> findAll() {
		return getMongoTemplate().findAll(getCollectionType());
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
	public Class<T> getCollectionType();
}
