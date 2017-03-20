package com.levelup.persist.repo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.levelup.persist.model.Sequence;
import com.levelup.persist.repo.SequenceRepo;

/**
 * A class that implements {@link SequenceRepo}
 * 
 * @author ahamouda
 *
 */
@Repository
public class SequenceRepoImpl implements SequenceRepo {

	private static final Logger LOGGER = LoggerFactory.getLogger(SequenceRepoImpl.class);

	@Autowired
	private MongoOperations mongoOperation;

	/**
	 * @see SequenceRepo#getNextSequenceId(String)
	 */
	@Override
	public long getNextSequenceId(String seqKey) {
		Query query = new Query(Criteria.where("_id").is(seqKey));

		Update update = new Update();
		update.inc("seq", 1);

		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		Sequence seqId = mongoOperation.findAndModify(query, update, options, Sequence.class);

		if (seqId == null) {
			LOGGER.error("Failed to generate new sequence Id!");
		}
		return seqId.getSeq();
	}

}
