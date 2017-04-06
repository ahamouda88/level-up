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

	/**
	 * @see SequenceService#save(Sequence)
	 */
	@Override
	public void save(Sequence sequence) {
		if (!isValidSequence(sequence)) {
			throw new NullPointerException("Sequence object and the sequence Id can't be null");
		}
		sequenceDao.insertDocument(sequence);
	}

	/**
	 * @see SequenceService#delete(Sequence)
	 */
	@Override
	public void delete(Sequence sequence) {
		if (!isValidSequence(sequence)) {
			throw new NullPointerException("Sequence object and the sequence Id can't be null");
		}
		sequenceDao.removeDocument(sequence);
	}

	/**
	 * @see SequenceService#update(Sequence)
	 */
	@Override
	public void update(Sequence sequence) {
		if (!isValidSequence(sequence)) {
			throw new NullPointerException("Sequence object and the sequence Id can't be null");
		}
		sequenceDao.upsertDocument(sequence);
	}

	/**
	 * @see SequenceService#find(String)
	 */
	@Override
	public Sequence find(String id) {
		if (isEmpty(id)) return null;

		return sequenceDao.findById(id);
	}

	/**
	 * @see SequenceService#getNextSequenceId(String)
	 */
	@Override
	public long getNextSequenceId(String seqKey) {
		if (isEmpty(seqKey)) return -1;

		return sequenceDao.getNextSequenceId(seqKey);
	}

	/**
	 * @see SequenceService#findAll()
	 */
	@Override
	public List<Sequence> findAll() {
		return sequenceDao.findAll();
	}

	private boolean isValidSequence(Sequence sequence) {
		return sequence != null && !isEmpty(sequence.getId());
	}

}
