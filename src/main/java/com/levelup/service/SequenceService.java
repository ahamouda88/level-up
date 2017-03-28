package com.levelup.service;

import com.levelup.persist.model.Sequence;

/**
 * An interface to manage and apply the logic for performing operations on a {@link Sequence} entity
 * 
 * @author ahamouda
 *
 */
public interface SequenceService {

	public void save(Sequence sequene);

	public void delete(Sequence sequene);

	public void update(Sequence sequene);

}
