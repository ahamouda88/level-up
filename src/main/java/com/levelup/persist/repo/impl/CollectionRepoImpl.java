package com.levelup.persist.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.levelup.persist.repo.CollectionRepo;

public abstract class CollectionRepoImpl implements CollectionRepo {

	@Autowired
	protected MongoTemplate mongoTemplate;
	private Class<?> classType;

	public CollectionRepoImpl(Class<?> classType) {
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
	public Class<?> getCollectionType() {
		return classType;
	}
}
