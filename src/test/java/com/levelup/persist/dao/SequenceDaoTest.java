package com.levelup.persist.dao;

import static com.levelup.utils.DocumentFactoryUtils.createSequence;
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

import com.levelup.config.spring.SpringBootConfig;
import com.levelup.persist.dao.SequenceDao;
import com.levelup.persist.model.Sequence;

/**
 * Unit test for Sequence DAO class
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringBootConfig.class })
public class SequenceDaoTest {

	@Autowired
	private SequenceDao sequenceDao;

	@Before
	public void testCreateCollection() {
		// Test Insert a sequence object
		Sequence sequence = createSequence("carId");
		sequenceDao.insertDocument(sequence);
	}

	@Test
	public void testGetInvalidSequenceId() {
		long expectedId = -1;
		long actualId = sequenceDao.getNextSequenceId("invalid");

		assertEquals(expectedId, actualId);
	}

	@Test
	public void testGetNextSequenceId() {
		long actualId = sequenceDao.getNextSequenceId("carId");
		assertEquals(1, actualId);

		actualId = sequenceDao.getNextSequenceId("carId");
		assertEquals(2, actualId);
	}

	@Test
	public void testUpsertSequence() {
		Sequence sequence = new Sequence();
		sequence.setId("carId");
		sequence.setSeq(99);
		sequenceDao.upsertDocument(sequence);

		Sequence newSequence = sequenceDao.findById("carId");
		assertEquals(99, newSequence.getSeq());
	}

	@Test
	public void testRemoveSequence() {
		Sequence sequence = createSequence("carId");
		sequenceDao.removeDocument(sequence);

		assertEquals(0, sequenceDao.findAll().size());
	}

	@Test
	public void testRemoveInvalidSequence() {
		Sequence sequence = createSequence(null);
		sequenceDao.removeDocument(sequence);

		assertEquals(1, sequenceDao.findAll().size());
	}

	@Test(expected = DuplicateKeyException.class)
	public void testDuplicateKeySequence() {
		Sequence sequence = createSequence("carId");
		sequenceDao.insertDocument(sequence);
	}

	@Test
	public void testFindAll() {
		List<Sequence> list = sequenceDao.findAll();
		assertEquals(1, list.size());

		// Insert a user sequence object
		Sequence sequence = createSequence("userId");
		sequenceDao.insertDocument(sequence);

		list = sequenceDao.findAll();
		assertEquals(2, list.size());
	}

	@After
	public void testRemoveAll() {
		sequenceDao.removeAll();
		
		List<Sequence> list = sequenceDao.findAll();
		assertEquals(0, list.size());
	}
}