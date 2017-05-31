package com.levelup.persist.dao;

import java.util.List;

import com.levelup.persist.model.Account;

/**
 * An interface that defines the persistence operations to be performed on a {@link Account}
 * 
 * @author ahamouda
 *
 */
public interface AccountDao extends CollectionDao<Account, String> {

	/**
	 * A method that returns the list of accounts that are associated to a buddy/user given the buddy Id
	 * 
	 * @param buddyId
	 *            the user/buddy id
	 * @return a list of accounts, or <i><b>null</b></i> if buddy Id is null
	 */
	public List<Account> getAccounts(Long buddyId);

}
