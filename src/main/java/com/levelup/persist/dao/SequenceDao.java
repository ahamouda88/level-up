package com.levelup.persist.dao;

import com.levelup.persist.model.Sequence;

/**
 * An interface that defines the persistence operations to be performed on a {@link Sequence}
 * 
 * @author ahamouda
 *
 */
public interface SequenceDao extends CollectionDao<Sequence, String> {

	/**
	 * A method to return the next sequence Id, from the sequences collection using the given sequence key or name
	 * 
	 * @param seqKey
	 *            the key/name of the sequence
	 * @return the next sequence Id, or -1 if seqKey is invalid
	 */
	public long getNextSequenceId(String seqKey);
}
