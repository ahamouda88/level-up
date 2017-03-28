package com.levelup.persist.repo;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
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

		assertEquals(true, sequenceRepo.collectionExists());

		// Test Insert a sequence object
		Sequence sequence = createSequenceDocument("carId");
		sequenceRepo.insertDocument(sequence);
	}

	@Test
	public void testGetInvalidSequenceId() {
		long expectedId = -1;
		long actualId = sequenceRepo.getNextSequenceId("invalid");

		assertEquals(expectedId, actualId);
	}

	@Test
	public void testGetNextSequenceId() {
		long actualId = sequenceRepo.getNextSequenceId("carId");
		assertEquals(1, actualId);

		actualId = sequenceRepo.getNextSequenceId("carId");
		assertEquals(2, actualId);
	}

	@Test
	public void testUpsertSequence() {
		Sequence sequence = new Sequence();
		sequence.setId("carId");
		sequence.setSeq(99);
		sequenceRepo.upsertDocument(sequence);

		Sequence newSequence = sequenceRepo.findById("carId");
		assertEquals(99, newSequence.getSeq());
	}

	@Test
	public void testRemoveSequence() {
		Sequence sequence = createSequenceDocument("carId");
		sequenceRepo.removeDocument(sequence);

		assertEquals(0, sequenceRepo.findAll().size());
	}

	@Test
	public void testRemoveInvalidSequence() {
		Sequence sequence = createSequenceDocument(null);
		sequenceRepo.removeDocument(sequence);

		assertEquals(1, sequenceRepo.findAll().size());
	}

	@Test(expected = DuplicateKeyException.class)
	public void testDuplicateKeySequence() {
		Sequence sequence = createSequenceDocument("carId");
		sequenceRepo.insertDocument(sequence);
	}

	@Test
	public void testFindAll() {
		List<Sequence> list = sequenceRepo.findAll();
		assertEquals(1, list.size());

		// Insert a user sequence object
		Sequence sequence = createSequenceDocument("userId");
		sequenceRepo.insertDocument(sequence);

		list = sequenceRepo.findAll();
		assertEquals(2, list.size());
	}

	@After
	public void testDropCollection() {
		assertEquals(true, sequenceRepo.collectionExists());

		sequenceRepo.dropCollection();

		assertEquals(false, sequenceRepo.collectionExists());
	}

	private Sequence createSequenceDocument(String key) {
		Sequence sequence = new Sequence();
		sequence.setId(key);
		sequence.setSeq(0);
		return sequence;
	}

}