package com.levelup.persist.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.levelup.persist.repo.CollectionRepo;

public abstract class CollectionRepoImpl<T> implements CollectionRepo<T> {

	@Autowired
	protected MongoTemplate mongoTemplate;
	private Class<T> classType;

	public CollectionRepoImpl(Class<T> classType) {
		this.classType = classType;
	}

	/**
	 * @see CollectionRepo#getMongoTemplate()
	 */
	@Override
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	/**
	 * @see CollectionRepo#getCollectionType()
	 */
	@Override
	public Class<T> getCollectionType() {
		return classType;
	}
}
