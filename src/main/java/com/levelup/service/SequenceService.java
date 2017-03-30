package com.levelup.service;

import com.levelup.persist.model.Sequence;

/**
 * An interface to manage and apply the logic for performing operations on a {@link Sequence} entity
 * 
 * @author ahamouda
 *
 */
public interface SequenceService extends GenericService<Sequence, String> {

	/**
	 * A method that returns the next sequence Id
	 * 
	 * @param seqKey
	 *            the key of the sequence needed
	 * @return the next sequence Id based on the given sequence key, or <b>-1</b> if the key is invalid
	 */
	public long getNextSequenceId(String seqKey);
}
