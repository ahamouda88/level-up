package com.levelup.service;

import static com.levelup.utils.DocumentFactoryUtils.createSequence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.function.Consumer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.levelup.persist.model.Sequence;
import com.levelup.spring.config.SpringBootConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringBootConfig.class })
public class SequenceServiceTest {

	@Autowired
	private SequenceService sequenceService;

	private final String[] sequenceIds = new String[] { "personId", "autoId", "aptId" };

	@Before
	public void testSave() {
		// Test Save and Find methods
		sequenceService.save(createSequence(sequenceIds[0]));
		assertNotNull(sequenceService.find(sequenceIds[0]));

		sequenceService.save(createSequence(sequenceIds[1]));
		assertNotNull(sequenceService.find(sequenceIds[1]));

		sequenceService.save(createSequence(sequenceIds[2]));
		assertNotNull(sequenceService.find(sequenceIds[2]));
	}

	@Test
	public void testValidMethods() {
		// Test Update method
		Sequence sequence = sequenceService.find(sequenceIds[0]);
		sequence.setSeq(99);

		sequenceService.update(sequence);
		assertEquals(99, sequenceService.find(sequenceIds[0]).getSeq());

		// Test Remove and findAll methods
		assertEquals(3, sequenceService.findAll().size());
		sequenceService.delete(sequence);
		assertEquals(2, sequenceService.findAll().size());

		// Test getNextSequenceId method
		assertEquals(1, sequenceService.getNextSequenceId(sequenceIds[1]));
	}

	@After
	public void testInvalidMethods() {
		// Test invalid save method
		testInvalidNullParameter(seq -> sequenceService.save(seq), "Sequence object and the sequence Id can't be null");

		// Test invalid delete method
		testInvalidNullParameter(seq -> sequenceService.delete(seq),
				"Sequence object and the sequence Id can't be null");

		// Test invalid update method
		testInvalidNullParameter(seq -> sequenceService.update(seq),
				"Sequence object and the sequence Id can't be null");

		// Test invalid find method
		assertNull(sequenceService.find("invalid"));

		// Test invalid getNextSequenceId method
		assertEquals(-1, sequenceService.getNextSequenceId(sequenceIds[0]));
	}

	private void testInvalidNullParameter(Consumer<Sequence> consumer, String expectedMessage) {
		try {
			consumer.accept(null);
			fail("Expecting a NullPointerException!");
		} catch (NullPointerException ex) {
			assertEquals(ex.getMessage(), expectedMessage);
		}
	}
}
