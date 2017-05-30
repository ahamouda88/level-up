package com.levelup.service.impl;

import static com.levelup.utils.DocumentFactoryUtils.createSequence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levelup.persist.dao.BuddyDao;
import com.levelup.persist.model.Buddy;
import com.levelup.persist.model.Sequence;
import com.levelup.service.BuddyService;
import com.levelup.service.SequenceService;

/**
 * An implementation of the {@link BuddyService} interface
 * 
 * @author ahamouda
 *
 */
@Service
public class BuddyServiceImpl implements BuddyService {

	@Autowired
	private SequenceService sequenceService;

	@Autowired
	private BuddyDao buddyDao;

	/**
	 * @see BuddyService#save(Buddy)
	 */
	@Override
	public void save(Buddy buddy) {
		if (buddy == null) throw new NullPointerException("Buddy object must not be null");
		
		sequenceCheck();
		long id = sequenceService.getNextSequenceId(SEQ_KEY);

		if (id == -1) return;

		buddy.setId(id);
		buddyDao.insertDocument(buddy);
	}

	/**
	 * @see BuddyService#delete(Buddy)
	 */
	@Override
	public void delete(Buddy buddy) {
		if (buddy == null) throw new NullPointerException("Buddy object must not be null");

		buddyDao.removeDocument(buddy);
	}

	/**
	 * @see BuddyService#update(Buddy)
	 */
	@Override
	public void update(Buddy buddy) {
		if (buddy == null) throw new NullPointerException("Buddy object must not be null");

		buddyDao.upsertDocument(buddy);
	}

	/**
	 * @see BuddyService#find(Long)
	 */
	@Override
	public Buddy find(Long id) {
		if (id == null) return null;

		return buddyDao.findById(id);
	}

	/**
	 * @see BuddyService#findAll()
	 */
	@Override
	public List<Buddy> findAll() {
		return buddyDao.findAll();
	}

	/**
	 * A method that creates the sequence key for this model if not exists
	 */
	private void sequenceCheck() {
		Sequence sequence = sequenceService.find(SEQ_KEY);

		if (sequence == null) sequenceService.save(createSequence(SEQ_KEY));
	}
}
