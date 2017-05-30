package com.levelup.utils;

import static org.apache.commons.lang3.StringUtils.isBlank;

import com.levelup.persist.model.Buddy;
import com.levelup.persist.model.Sequence;

/**
 * A utility class for creating documents
 * 
 * @author ahamouda
 *
 */
public final class DocumentFactoryUtils {

	/**
	 * Private constructor
	 */
	private DocumentFactoryUtils() {
	}

	/**
	 * A method that creates a {@link Sequence} object, using the given key
	 * 
	 * @param key
	 *            the key of the sequence object
	 * @return a new created sequence object or <i><b>null</b></i> if the key param is invalid
	 */
	public static Sequence createSequence(String key) {
		if (isBlank(key)) return null;

		Sequence sequence = new Sequence();
		sequence.setId(key);
		sequence.setSeq(0);
		return sequence;
	}

	/**
	 * A method that creates an empty or null fields {@link Buddy} object, using the given Id
	 * 
	 * @param id
	 *            the id of the buddy object
	 * @return a new created buddy object or <i><b>null</b></i> if the id param is invalid
	 */
	public static Buddy createEmptyBuddy(String id) {
		if (isBlank(id)) return null;

		Buddy buddy = new Buddy();
		buddy.setId(id);
		return buddy;
	}
}
