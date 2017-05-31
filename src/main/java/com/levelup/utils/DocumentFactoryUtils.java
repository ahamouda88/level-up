package com.levelup.utils;

import static org.apache.commons.lang.StringUtils.isBlank;

import java.util.List;

import com.levelup.persist.model.Account;
import com.levelup.persist.model.Buddy;
import com.levelup.persist.model.BuddyRole;
import com.levelup.persist.model.Sequence;

/**
 * A utility class for creating documents
 * 
 * @author ahamouda
 *
 */
public final class DocumentFactoryUtils {

	/**
	 * Private constructor
	 */
	private DocumentFactoryUtils() {
	}

	/**
	 * A static method that creates a {@link Sequence} object, using the given key
	 * 
	 * @param key
	 *            the key of the sequence object
	 * @return a new created sequence object or <i><b>null</b></i> if the key param is invalid
	 */
	public static Sequence createSequence(String key) {
		if (isBlank(key)) return null;

		Sequence sequence = new Sequence();
		sequence.setId(key);
		sequence.setSeq(0);
		return sequence;
	}

	/**
	 * A static method that creates an empty or null fields {@link Buddy} object, using the given Id
	 * 
	 * @param id
	 *            the id of the buddy object
	 * @return a new created buddy object or <i><b>null</b></i> if the id param is invalid
	 */
	public static Buddy createEmptyBuddy(Long id) {
		if (id == null) return null;

		Buddy buddy = new Buddy();
		buddy.setId(id);
		return buddy;
	}

	/**
	 * A static method that creates an {@link Account} object, using the given username, password, {@link Buddy}, and
	 * list of roles
	 * 
	 * @param username
	 *            the username for the account
	 * @param password
	 *            the password for the account
	 * @param buddy
	 *            the buddy/user associated with the account
	 * @param roles
	 *            list of {@link BuddyRole}
	 * @return a new created Account
	 */
	public static Account createAccount(String username, String password, Buddy buddy, List<BuddyRole> roles) {
		if (isBlank(username) || isBlank(password) || buddy == null) return null;
		
		Account account = new Account();
		account.setBuddy(buddy);
		account.setUsername(username);
		account.setPassword(password);
		return account;
	}

}
