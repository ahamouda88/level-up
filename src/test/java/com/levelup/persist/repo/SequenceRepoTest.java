package com.levelup.persist.repo;

import java.util.List;

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

		// Test Insert a sequence object
		Sequence sequence = new Sequence();
		sequence.setId("carId");
		sequence.setSeq(0);
		sequenceRepo.insertDocument(sequence);
	}

	@Test
	public void testGetInvalidSequenceId() {
		long expectedId = -1;
		long actualId = sequenceRepo.getNextSequenceId("invalid");

		Assert.assertEquals(expectedId, actualId);
	}

	@Test
	public void testGetNextSequenceId() {
		long actualId = sequenceRepo.getNextSequenceId("carId");
		Assert.assertEquals(1, actualId);

		actualId = sequenceRepo.getNextSequenceId("carId");
		Assert.assertEquals(2, actualId);
	}

	@Test
	public void testFindAll() {
		List<Sequence> list = sequenceRepo.findAll();
		Assert.assertEquals(1, list.size());

		// Insert a user sequence object
		Sequence sequence = new Sequence();
		sequence.setId("userId");
		sequence.setSeq(0);
		sequenceRepo.insertDocument(sequence);

		list = sequenceRepo.findAll();
		Assert.assertEquals(2, list.size());
	}

	@After
	public void testDropCollection() {
		Assert.assertEquals(true, sequenceRepo.collectionExists());

		sequenceRepo.dropCollection();

		Assert.assertEquals(false, sequenceRepo.collectionExists());
	}

}