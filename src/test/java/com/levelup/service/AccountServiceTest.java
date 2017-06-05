package com.levelup.service;

import static com.levelup.utils.DocumentFactoryUtils.createEmptyBuddy;
import static com.levelup.utils.DocumentFactoryUtils.createAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.function.Consumer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.levelup.config.spring.SpringBootConfig;
import com.levelup.persist.model.Account;
import com.levelup.persist.model.Buddy;
import com.levelup.persist.model.BuddyRole;
import com.levelup.persist.model.exception.AccountAlreadyExitsException;
import com.levelup.utils.CollectionUtils;
import com.levelup.utils.DocumentFactoryUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringBootConfig.class })
public class AccountServiceTest {

	@Autowired
	private AccountService accountService;

	@Before
	public void testSave() {
		// Clear collection
		accountService.deleteAll();

		testSaveAndFindAccount("leo", "password1", 1l, null);

		testSaveAndFindAccount("ahamouda", "password2", 2l, null);

		testSaveAndFindAccount("omar", "password3", 3l, null);
	}

	@Test
	public void testValidMethods() {
		// Test number of buddies
		List<Account> buddies = accountService.findAll();
		assertEquals(3, buddies.size());

		// Test update method
		String updatedUsername = "ahamouda";
		Account savedAccount = accountService.find(updatedUsername);
		List<BuddyRole> roles = CollectionUtils.listOf(BuddyRole.ADMIN);
		savedAccount.setRoles(roles);

		accountService.update(savedAccount);
		assertEquals(1l, accountService.find(updatedUsername).getRoles().size());
		assertEquals(BuddyRole.ADMIN, accountService.find(updatedUsername).getRoles().get(0));

		// Test delete method
		Account toBeRemovedAccount = accountService.find("omar");
		accountService.delete(toBeRemovedAccount);

		List<Account> accountsAfterDeletion = accountService.findAll();
		assertEquals(2, accountsAfterDeletion.size());
	}

	@After
	public void testInvalidMethods() {
		// Test invalid save method
		testInvalidNullParameter(account -> accountService.save(account), "Account object must not be null");

		// Test invalid delete method
		testInvalidNullParameter(account -> accountService.delete(account), "Account object must not be null");

		// Test invalid update method
		testInvalidNullParameter(account -> accountService.update(account), "Account object must not be null");

		// Test buddy who has an existing account
		Account newAccount = createAccount("newAccount", "newPassword", createEmptyBuddy(1l), null);
		testAccountAlreadyExits(account -> accountService.save(newAccount), "Buddy with Id 1 already has an account");

		// Test invalid find method
		assertNull(accountService.find("invalidUsername"));

		// Test remove all
		accountService.deleteAll();
		List<Account> accounts = accountService.findAll();
		assertEquals(0, accounts.size());
	}

	private void testAccountAlreadyExits(Consumer<Account> consumer, String expectedMessage) {
		try {
			consumer.accept(null);
			fail("Expecting an AccountAlreadyExitsException!");
		} catch (AccountAlreadyExitsException ex) {
			assertEquals(ex.getMessage(), expectedMessage);
		}
	}

	private void testInvalidNullParameter(Consumer<Account> consumer, String expectedMessage) {
		try {
			consumer.accept(null);
			fail("Expecting a NullPointerException!");
		} catch (NullPointerException ex) {
			assertEquals(ex.getMessage(), expectedMessage);
		}
	}

	private void testSaveAndFindAccount(String username, String password, long buddyId, List<BuddyRole> roles) {
		Buddy buddy = createEmptyBuddy(buddyId);
		Account account = DocumentFactoryUtils.createAccount(username, password, buddy, roles);
		accountService.save(account);

		Account returnedAccount = accountService.find(username);
		assertEquals(account.getUsername(), returnedAccount.getUsername());
		assertEquals(account.getPassword(), returnedAccount.getPassword());
		assertEquals(Long.valueOf(buddyId), returnedAccount.getBuddy().getId());
	}
}
