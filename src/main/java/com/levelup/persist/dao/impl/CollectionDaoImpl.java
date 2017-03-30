package com.levelup.persist.dao.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.levelup.persist.dao.CollectionDao;

public abstract class CollectionDaoImpl<T, E extends Serializable> implements CollectionDao<T, E> {

	@Autowired
	protected MongoTemplate mongoTemplate;
	private Class<T> classType;

	public CollectionDaoImpl(Class<T> classType) {
		this.classType = classType;
	}

	/**
	 * @see CollectionDao#getMongoTemplate()
	 */
	@Override
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	/**
	 * @see CollectionDao#getCollectionType()
	 */
	@Override
	public Class<T> getCollectionType() {
		return classType;
	}
}
