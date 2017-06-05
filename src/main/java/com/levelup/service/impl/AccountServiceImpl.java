package com.levelup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levelup.persist.dao.AccountDao;
import com.levelup.persist.model.Account;
import com.levelup.persist.model.exception.AccountAlreadyExitsException;
import com.levelup.service.AccountService;

/**
 * An implementation of the {@link AccountService} interface
 * 
 * @author ahamouda
 *
 */
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;

	/**
	 * @throws AccountAlreadyExitsException
	 *             if buddy already has an account
	 */
	@Override
	public void save(Account account) {
		if (account == null) throw new NullPointerException("Account object must not be null");

		if (account.getBuddy() == null || account.getBuddy().getId() == null) {
			throw new IllegalArgumentException("Buddy object, and buddy Id must not be null");
		}

		// Check if buddy/user has an account already if so then throw an exception
		long id = account.getBuddy().getId();
		Account buddyAccount = accountDao.findByBuddyId(id);

		if (buddyAccount != null)
			throw new AccountAlreadyExitsException(String.format("Buddy with Id %s already has an account", id));

		accountDao.insertDocument(account);
	}

	@Override
	public void delete(Account account) {
		if (account == null) throw new NullPointerException("Account object must not be null");

		accountDao.removeDocument(account);
	}

	@Override
	public void deleteAll() {
		accountDao.removeAll();
	}

	/**
	 * @throws AccountAlreadyExitsException
	 *             if buddy already has an account
	 */
	@Override
	public void update(Account account) {
		if (account == null) throw new NullPointerException("Account object must not be null");

		accountDao.upsertDocument(account);
	}

	@Override
	public Account find(String id) {
		if (id == null) return null;

		return accountDao.findById(id);
	}

	@Override
	public List<Account> findAll() {
		return accountDao.findAll();
	}
}
