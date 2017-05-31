package com.levelup.persist.dao;

import static com.levelup.utils.DocumentFactoryUtils.createAccount;
import static com.levelup.utils.DocumentFactoryUtils.createEmptyBuddy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;

import com.levelup.persist.model.Account;
import com.levelup.persist.model.BuddyRole;
import com.levelup.spring.config.SpringBootConfig;
import com.levelup.utils.CollectionUtils;

/**
 * Unit test for Account DAO class
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringBootConfig.class })
public class AccountDaoTest {

	@Autowired
	private AccountDao accountDao;

	@Before
	public void testCreateCollection() {
		accountDao.createCollection();
		assertEquals(true, accountDao.collectionExists());

		// Test upsert account object
		Account account1 = createAccount("ahamouda", "password1", createEmptyBuddy(1L), null);
		accountDao.upsertDocument(account1);

		Account account2 = createAccount("leo", "password2", createEmptyBuddy(2L), null);
		accountDao.insertDocument(account2);
	}

	@Test
	public void testFindAll() {
		Account account1 = createAccount("quinni", "password3", createEmptyBuddy(3L), null);
		accountDao.insertDocument(account1);

		Account account2 = createAccount("admin", "password4", createEmptyBuddy(4L),
				CollectionUtils.listOf(BuddyRole.ADMIN, BuddyRole.NORMAL));
		accountDao.insertDocument(account2);

		List<Account> accounts = accountDao.findAll();
		assertEquals(4, accounts.size());
	}

	@Test
	public void testFindById() {
		Account savedAccount = accountDao.findById("leo");
		assertEquals("password2", savedAccount.getPassword());
		assertEquals(Long.valueOf(2), savedAccount.getBuddy().getId());
	}

	@Test(expected = DuplicateKeyException.class)
	public void testInvalidInsert() {
		// Test inserting account with an existing username
		Account account = createAccount("ahamouda", "anypassword", createEmptyBuddy(5L), null);
		accountDao.insertDocument(account);
	}

	@Test
	public void testFindByNullId() {
		Account savedAccount = accountDao.findById(null);
		assertNull(savedAccount);
	}

	@Test
	public void testFindByInvalidId() {
		Account account = accountDao.findById("invalidUsername");
		assertNull(account);
	}

	@Test
	public void testRemoveDocument() {
		testFindAllSize(2);
		Account account = createAccount("ahamouda", "password1", createEmptyBuddy(1L), null);
		accountDao.removeDocument(account);
		testFindAllSize(1);
	}

	@Test
	public void testInvalidRemoveDocument() {
		testFindAllSize(2);
		accountDao.removeDocument(null);
		testFindAllSize(2);
	}

	@Test
	public void testFindByBuddyId() {
		Account account = accountDao.findByBuddyId(1L);
		assertNotNull(account);
		assertEquals("ahamouda", account.getUsername());
		assertEquals("password1", account.getPassword());

		// Test invalid parameter
		assertNull(accountDao.findByBuddyId(null));
	}

	@After
	public void testDropCollection() {
		assertEquals(true, accountDao.collectionExists());

		accountDao.dropCollection();

		assertEquals(false, accountDao.collectionExists());
	}

	private void testFindAllSize(int expectedSize) {
		List<Account> buddies = accountDao.findAll();
		assertEquals(expectedSize, buddies.size());
	}

}