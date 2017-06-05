package com.levelup.service;

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

import com.levelup.persist.model.Buddy;
import com.levelup.spring.config.SpringBootConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringBootConfig.class })
public class BuddyServiceTest {

	@Autowired
	private BuddyService buddyService;

	@Before
	public void testSave() {
		// Clear collection
		buddyService.deleteAll();
		
		testSaveAndFindBuddy("Lionel", "messi@barca.com", 1L);

		testSaveAndFindBuddy("Sahar", "elkaffash@gmail.com", 2L);

		testSaveAndFindBuddy("Ibrahim", "hamouda@hotmail.com", 3L);
	}

	@Test
	public void testValidMethods() {
		// Test number of buddies
		List<Buddy> buddies = buddyService.findAll();
		assertEquals(3, buddies.size());

		// Test update method
		String city = "Barcelona";
		Buddy savedBuddy = buddyService.find(1L);
		savedBuddy.setCity(city);

		buddyService.update(savedBuddy);
		assertEquals(city, buddyService.find(1L).getCity());

		// Test delete method
		Buddy toBeRemovedBuddy = buddyService.find(2L);
		buddyService.delete(toBeRemovedBuddy);

		List<Buddy> buddiesAfterDeletion = buddyService.findAll();
		assertEquals(2, buddiesAfterDeletion.size());
	}

	@After
	public void testInvalidMethods() {
		// Test invalid save method
		testInvalidNullParameter(buddy -> buddyService.save(buddy), "Buddy object must not be null");

		// Test invalid delete method
		testInvalidNullParameter(buddy -> buddyService.delete(buddy), "Buddy object must not be null");

		// Test invalid update method
		testInvalidNullParameter(buddy -> buddyService.update(buddy), "Buddy object must not be null");

		// Test invalid find method
		assertNull(buddyService.find(1222L));

		// Test remove all
		buddyService.deleteAll();
		List<Buddy> accounts = buddyService.findAll();
		assertEquals(0, accounts.size());
	}

	private void testInvalidNullParameter(Consumer<Buddy> consumer, String expectedMessage) {
		try {
			consumer.accept(null);
			fail("Expecting a NullPointerException!");
		} catch (NullPointerException ex) {
			assertEquals(ex.getMessage(), expectedMessage);
		}
	}

	private void testSaveAndFindBuddy(String firstname, String email, long id) {
		Buddy buddy = new Buddy();
		buddy.setFirstName(firstname);
		buddy.setEmail(email);
		buddyService.save(buddy);

		Buddy returnedBuddy = buddyService.find(id);
		assertEquals(Long.valueOf(id), returnedBuddy.getId());
		assertEquals(buddy.getFirstName(), returnedBuddy.getFirstName());
		assertEquals(buddy.getEmail(), returnedBuddy.getEmail());
	}
}
