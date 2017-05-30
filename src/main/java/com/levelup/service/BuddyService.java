package com.levelup.service;

import com.levelup.persist.model.Buddy;

/**
 * An interface that extends {@link GenericService} and manages the logic for performing operations on a {@link Buddy}
 * entity
 * 
 * @author ahamouda
 *
 */
public interface BuddyService extends GenericService<Buddy, Long> {

	public static final String SEQ_KEY = "buddyId";
}
