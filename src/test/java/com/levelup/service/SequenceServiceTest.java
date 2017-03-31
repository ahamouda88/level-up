package com.levelup.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

	private final String[] sequenceIds = new String[] { "personId" };

	@Test
	public void testSave() {
		sequenceService.save(createSequenceDocument(sequenceIds[0]));

		assertNotNull(sequenceService.find(sequenceIds[0]));
	}

	@Test
	public void testUpdate() {
		Sequence sequence = sequenceService.find(sequenceIds[0]);
		sequence.setSeq(99);

		sequenceService.update(sequence);

		assertEquals(99, sequenceService.find("personId").getSeq());
	}

	private Sequence createSequenceDocument(String key) {
		Sequence sequence = new Sequence();
		sequence.setId(key);
		return sequence;
	}
}
