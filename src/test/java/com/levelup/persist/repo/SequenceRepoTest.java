package com.levelup.persist.repo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.levelup.spring.config.SpringBootConfig;
import com.mongodb.DBCollection;

/**
 * Unit test for Sequence Repository
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringBootConfig.class })
public class SequenceRepoTest {

	@Autowired
	private SequenceRepo sequenceRepo;
	private static final String COLLECTION_NAME = "Sequence";

	private DBCollection sequenceCollection;

	@Before
	public void testCreateCollection() {
		sequenceRepo.createCollection();

		Assert.assertEquals(true, sequenceRepo.collectionExists());
		// This check should be in the repo implementation of sequence!!
		sequenceCollection = sequenceRepo.getCollection(COLLECTION_NAME);
	}

	@Test
	public void testGetInvalidSequenceId() {
		long expectedId = -1;
		long actualId = sequenceRepo.getNextSequenceId("invalid");

		Assert.assertEquals(expectedId, actualId);
	}

	@Test
	public void testGetNextSequenceId() {

	}

	@After
	public void testDropCollection() {
		Assert.assertEquals(true, sequenceRepo.collectionExists());

		sequenceRepo.dropCollection();

		Assert.assertEquals(false, sequenceRepo.collectionExists());
	}

}