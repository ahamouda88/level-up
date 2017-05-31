package com.levelup.persist.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.levelup.persist.dao.AccountDao;
import com.levelup.persist.model.Account;

/**
 * A class that extends {@link CollectionDaoImpl}, and implements {@link AccountDao}. It manages the CRUD operations for
 * a {@link Account} object
 * 
 * @author ahamouda
 *
 */
@Repository
public class AccountDaoImpl extends CollectionDaoImpl<Account, String> implements AccountDao {

	public AccountDaoImpl() {
		super(Account.class);
	}

	/**
	 * @see AccountDao#getAccounts(Long)
	 */
	@Override
	public List<Account> getAccounts(Long buddyId) {
		// TODO Auto-generated method stub
		return null;
	}

}
