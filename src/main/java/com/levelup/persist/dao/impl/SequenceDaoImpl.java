package com.levelup.persist.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.levelup.persist.dao.SequenceDao;
import com.levelup.persist.model.Sequence;

/**
 * A class that extends {@link CollectionDaoImpl}, and implements {@link SequenceDao}. It manages the CRUD operations
 * for a {@link Sequence} object
 * 
 * @author ahamouda
 *
 */
@Repository
public class SequenceDaoImpl extends CollectionDaoImpl<Sequence, String> implements SequenceDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(SequenceDaoImpl.class);

	public SequenceDaoImpl() {
		super(Sequence.class);
	}

	/**
	 * @see SequenceDao#getNextSequenceId(String)
	 */
	@Override
	public long getNextSequenceId(String seqKey) {
		Query query = new Query(Criteria.where("id").is(seqKey));

		Update update = new Update();
		update.inc("seq", 1);

		FindAndModifyOptions options = FindAndModifyOptions.options().returnNew(true);

		Sequence seqId = mongoTemplate.findAndModify(query, update, options, Sequence.class);

		if (seqId == null) {
			LOGGER.error("Failed to generate new sequence Id!");
			return -1;
		}
		return seqId.getSeq();
	}
}
