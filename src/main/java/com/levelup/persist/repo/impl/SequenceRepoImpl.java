package com.levelup.persist.repo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
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
public class SequenceRepoImpl extends CollectionRepoImpl<Sequence> implements SequenceRepo {

	private static final Logger LOGGER = LoggerFactory.getLogger(SequenceRepoImpl.class);

	public SequenceRepoImpl() {
		super(Sequence.class);
	}

	/**
	 * @see SequenceRepo#getNextSequenceId(String)
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