package com.levelup.persist.dao;

import static com.levelup.utils.DocumentFactoryUtils.createEmptyBuddy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;

import com.levelup.persist.model.Buddy;
import com.levelup.spring.config.SpringBootConfig;

/**
 * Unit test for Sequence DAO class
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringBootConfig.class })
public class BuddyDaoTest {

	@Autowired
	private BuddyDao buddyDao;

	@Before
	public void testCreateCollection() {
		buddyDao.createCollection();
		assertEquals(true, buddyDao.collectionExists());

		// Test upsert buddy object
		Buddy buddy = createEmptyBuddy(1L);
		buddy.setFirstName("Lionel");
		buddy.setLastName("Messi");
		buddyDao.upsertDocument(buddy);
	}

	@Test
	public void testFindAll() {
		Buddy buddy1 = createEmptyBuddy(2L);
		buddy1.setFirstName("Xavi");
		buddy1.setLastName("Hernandez");
		buddyDao.insertDocument(buddy1);

		Buddy buddy2 = createEmptyBuddy(3L);
		buddy2.setFirstName("Neymar");
		buddy2.setLastName("JR");
		buddyDao.insertDocument(buddy2);

		Buddy buddy3 = createEmptyBuddy(4L);
		buddy3.setFirstName("David");
		buddy3.setLastName("Silva");
		buddyDao.insertDocument(buddy3);

		List<Buddy> buddies = buddyDao.findAll();
		assertEquals(4, buddies.size());

		IntStream.range(0, buddies.size()).forEach(i -> assertEquals(Long.valueOf(i + 1), buddies.get(i).getId()));
	}

	@Test
	public void testFindById() {
		Buddy savedBuddy = buddyDao.findById(1L);
		assertEquals("Lionel", savedBuddy.getFirstName());
	}

	@Test(expected = DuplicateKeyException.class)
	public void testInvalidInsert() {
		// Test inserting buddy with an existing Id
		Buddy buddy = createEmptyBuddy(1L);
		buddyDao.insertDocument(buddy);
	}

	@Test
	public void testFindByNullId() {
		Buddy savedBuddy = buddyDao.findById(null);
		assertNull(savedBuddy);
	}

	@Test
	public void testFindByInvalidId() {
		Buddy buddy = buddyDao.findById(999L);
		assertNull(buddy);
	}

	@Test
	public void testRemoveDocument() {
		testFindAllSize(1);
		Buddy buddy = createEmptyBuddy(1L);
		buddyDao.removeDocument(buddy);
		testFindAllSize(0);
	}

	@Test
	public void testInvalidRemoveDocument() {
		testFindAllSize(1);
		buddyDao.removeDocument(null);
		testFindAllSize(1);
	}

	@After
	public void testDropCollection() {
		assertEquals(true, buddyDao.collectionExists());

		buddyDao.dropCollection();

		assertEquals(false, buddyDao.collectionExists());
	}

	private void testFindAllSize(int expectedSize) {
		List<Buddy> buddies = buddyDao.findAll();
		assertEquals(expectedSize, buddies.size());
	}

}