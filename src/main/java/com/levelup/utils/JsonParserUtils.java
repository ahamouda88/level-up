package com.levelup.utils;

import static org.springframework.util.StringUtils.isEmpty;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A utility class that converts Java Object to/from JSON
 * 
 * @author ahamouda
 *
 */
public final class JsonParserUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonParserUtils.class);

	private final static ObjectMapper mapper;

	static {
		mapper = new ObjectMapper();
	}

	/**
	 * A method that converts a Java Object to a String JSON
	 * 
	 * @param javaObject
	 *            a java object
	 * @return a valid JSON String, or <b>null</b> if input is invalid or JSON parsing went wrong
	 */
	public static <T> String convertObjectToJson(T javaObject) {
		try {
			if (javaObject == null) {
				LOGGER.error("Java Object can't be null!");
				return null;
			}

			return mapper.writeValueAsString(javaObject);
		} catch (JsonProcessingException e) {
			LOGGER.error("Unable to convert the following Java Object {} to JSON String", javaObject);
			return null;
		}
	}

	/**
	 * A method that converts a String JSON to a Java Object
	 * 
	 * @param jsonString
	 *            a JSON String
	 * @param clazz
	 *            the Java Object class type
	 * @return a Java Object based on the given type
	 */
	public static <T> T convertJsonToObject(String jsonString, Class<T> clazz) {
		try {
			if (clazz == null || isEmpty(jsonString)) {
				LOGGER.error("JSON String and the class type can't be null!");
				return null;
			}

			return mapper.readValue(jsonString, clazz);
		} catch (IOException e) {
			LOGGER.error("Unable to convert the following JSON String {} to a Java Object", jsonString, e);
			return null;
		}
	}
}
