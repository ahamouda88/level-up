package com.levelup.service.impl;

import static org.springframework.util.StringUtils.isEmpty;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levelup.persist.dao.SequenceDao;
import com.levelup.persist.model.Sequence;
import com.levelup.service.SequenceService;

/**
 * An implementation of the {@link SequenceService} interface
 * 
 * @author ahamouda
 *
 */
@Service
public class SequenceServiceImpl implements SequenceService {

	@Autowired
	private SequenceDao sequenceDao;

	@Override
	public void save(Sequence sequence) {
		if (!isValidSequence(sequence)) {
			throw new NullPointerException("Sequence object and the sequence Id can't be null");
		}
		sequenceDao.insertDocument(sequence);
	}

	@Override
	public void delete(Sequence sequence) {
		if (!isValidSequence(sequence)) {
			throw new NullPointerException("Sequence object and the sequence Id can't be null");
		}
		sequenceDao.removeDocument(sequence);
	}

	@Override
	public void deleteAll() {
		sequenceDao.removeAll();
	}

	@Override
	public void update(Sequence sequence) {
		if (!isValidSequence(sequence)) {
			throw new NullPointerException("Sequence object and the sequence Id can't be null");
		}
		sequenceDao.upsertDocument(sequence);
	}

	@Override
	public Sequence find(String id) {
		if (isEmpty(id)) return null;

		return sequenceDao.findById(id);
	}

	@Override
	public long getNextSequenceId(String seqKey) {
		if (isEmpty(seqKey)) return -1;

		return sequenceDao.getNextSequenceId(seqKey);
	}

	@Override
	public List<Sequence> findAll() {
		return sequenceDao.findAll();
	}

	private boolean isValidSequence(Sequence sequence) {
		return sequence != null && !isEmpty(sequence.getId());
	}

}
