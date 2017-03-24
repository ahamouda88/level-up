package com.levelup.persist.repo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.levelup.persist.model.Sequence;
import com.levelup.spring.config.SpringBootConfig;
import com.levelup.utils.JsonParserUtils;

/**
 * Unit test for Sequence Repository
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringBootConfig.class })
public class SequenceRepoTest {

	@Autowired
	private SequenceRepo sequenceRepo;

	@Before
	public void testCreateCollection() {
		sequenceRepo.createCollection();

		Assert.assertEquals(true, sequenceRepo.collectionExists());
		
		// Insert a sequence object
		Sequence sequence = new Sequence();
		sequence.setId("carId");
		sequence.setSeq(0);
		sequenceRepo.insertDocument(JsonParserUtils.convertObjectToJson(sequence));
		
		sequenceRepo.findAll();
	}

	@Test
	public void testGetInvalidSequenceId() {
		long expectedId = -1;
		long actualId = sequenceRepo.getNextSequenceId("invalid");

		Assert.assertEquals(expectedId, actualId);
	}

	@Test
	public void testGetNextSequenceId() {
		long expectedId = -1;
		long actualId = sequenceRepo.getNextSequenceId("carId");

		Assert.assertEquals(expectedId, actualId);
	}

	@After
	public void testDropCollection() {
		Assert.assertEquals(true, sequenceRepo.collectionExists());

		sequenceRepo.dropCollection();

		Assert.assertEquals(false, sequenceRepo.collectionExists());
	}

}