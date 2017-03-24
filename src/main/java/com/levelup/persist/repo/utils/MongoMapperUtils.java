package com.levelup.persist.repo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.levelup.utils.JsonParserUtils;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

/**
 * A utility class that contains operations for mapping between a mongo db objects and Java objects
 * 
 * @author ahamouda
 *
 */
public final class MongoMapperUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(MongoMapperUtils.class);

	/**
	 * A method that converts a mongo {@link DBObject} object to a Java object
	 * 
	 * @param dbObject
	 *            a mongo {@link DBObject} object
	 * @param clazz
	 *            the Java Object class type
	 * @return a Java Object based on the given type
	 */
	public static <T> T convertDBObjectToJava(DBObject dbObject, Class<T> clazz) {
		if (clazz == null || dbObject == null) {
			LOGGER.error("DB Object and the class type can't be null!");
			return null;
		}

		// Remove unwanted field
		dbObject.removeField("_id");

		String json = JSON.serialize(dbObject);
		return JsonParserUtils.convertJsonToObject(json, clazz);
	}
}
