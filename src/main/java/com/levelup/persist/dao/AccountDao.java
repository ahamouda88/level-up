package com.levelup.persist.dao;

import com.levelup.persist.model.Account;

/**
 * An interface that defines the persistence operations to be performed on a {@link Account}
 * 
 * @author ahamouda
 *
 */
public interface AccountDao extends CollectionDao<Account, String> {

	/**
	 * A method that returns the account associated to a buddy/user given the buddy Id
	 * 
	 * @param buddyId
	 *            the user/buddy id
	 * @return the account associated to the given buddy, or <i><b>null</b></i> if buddy Id is null
	 */
	public Account findByBuddyId(Long buddyId);

}
