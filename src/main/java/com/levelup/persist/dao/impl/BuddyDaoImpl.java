package com.levelup.persist.dao.impl;

import org.springframework.stereotype.Repository;

import com.levelup.persist.dao.BuddyDao;
import com.levelup.persist.model.Buddy;

/**
 * A class that extends {@link CollectionDaoImpl}, and implements {@link BuddyDao}. It manages the CRUD operations for a
 * {@link Buddy} object
 * 
 * @author ahamouda
 *
 */
@Repository
public class BuddyDaoImpl extends CollectionDaoImpl<Buddy, String> implements BuddyDao {

	public BuddyDaoImpl() {
		super(Buddy.class);
	}

}
