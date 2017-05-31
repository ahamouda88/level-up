package com.levelup.persist.dao.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

	@Override
	public Account findByBuddyId(Long buddyId) {
		if (buddyId == null) return null;

		Query query = new Query(Criteria.where("buddy.id").is(buddyId));

		return getMongoTemplate().findOne(query, Account.class);
	}

}
