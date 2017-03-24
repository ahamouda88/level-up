package com.levelup.persist.repo;

import static com.levelup.persist.repo.utils.MongoMapperUtils.convertDBObjectToJava;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

/**
 * An interface to manage collections
 * 
 * @author ahamouda
 *
 */
public interface CollectionRepo<T> {

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
	 * A method that inserts a document to the database
	 * 
	 * @param jsongString
	 *            the JSON format of the document
	 */
	public default void insertDocument(String jsongString) {
		DBCollection dbCollection = getCollection(getCollectionType().getName());
		DBObject dbObject = (DBObject) JSON.parse(jsongString);
		dbCollection.insert(dbObject);
	}

	/**
	 * A method that returns all documents of a collection
	 * 
	 * @return a list of documents
	 */
	public default List<T> findAll() {
		DBCollection dbCollection = getCollection(getCollectionType().getName());
		DBCursor cursor = dbCollection.find();

		List<T> documents = new ArrayList<>();
		while (cursor.hasNext()) {
			T javaObj = convertDBObjectToJava(cursor.next(), getCollectionType());
			documents.add(javaObj);
		}
		return documents;
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
