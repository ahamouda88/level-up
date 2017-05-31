package com.levelup.utils;

import java.util.Arrays;
import java.util.List;

/**
 * A class that contains static methods that operate on collections
 * 
 * @author ahamouda
 *
 */
public final class CollectionUtils {

	private CollectionUtils() {
	}

	/**
	 * A static method that returns a list of elements using the given array of elements
	 * 
	 * @param elements
	 * @return a new created list of elements, or <i><b>null</b></i> if elements array is null
	 */
	@SafeVarargs
	public static <T> List<T> listOf(T... elements) {
		if (elements == null) return null;

		return Arrays.asList(elements);
	}
}
