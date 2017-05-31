package com.levelup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levelup.persist.dao.AccountDao;
import com.levelup.persist.model.Account;
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
	 * @see AccountService#save(Account)
	 */
	@Override
	public void save(Account account) {
		if (account == null) throw new NullPointerException("Account object must not be null");

		accountDao.insertDocument(account);
	}

	/**
	 * @see AccountService#delete(Account)
	 */
	@Override
	public void delete(Account account) {
		if (account == null) throw new NullPointerException("Account object must not be null");

		accountDao.removeDocument(account);
	}

	/**
	 * @see AccountService#update(Account)
	 */
	@Override
	public void update(Account account) {
		if (account == null) throw new NullPointerException("Account object must not be null");

		accountDao.upsertDocument(account);
	}

	/**
	 * @see AccountService#find(String)
	 */
	@Override
	public Account find(String id) {
		if (id == null) return null;

		return accountDao.findById(id);
	}

	/**
	 * @see AccountService#findAll()
	 */
	@Override
	public List<Account> findAll() {
		return accountDao.findAll();
	}
}
