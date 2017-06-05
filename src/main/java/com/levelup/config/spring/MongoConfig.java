package com.levelup.config.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

	/* @formatter:off
	*  Created superuser by using the following commands:
	*  mongo --> use admin
	*  db.createUser({user:"ahamouda", pwd:"free", roles: ["userAdminAnyDatabase","dbAdminAnyDatabase","readWriteAnyDatabase"]})
	*  @formatter:on
	*/
	public final static String DB_NAME = "levelupDb";

	@Override
	protected String getDatabaseName() {
		return DB_NAME;
	}

	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient("127.0.0.1", 27017);
	}

	@Override
	protected String getMappingBasePackage() {
		return "com.levelup.persist";
	}

	/*
	 * Configure a mongo template for each database
	 */
	/*
	 * @Bean(name = "sequenceTemplate") public MongoTemplate sequenceTemplate() throws Exception { return new
	 * MongoTemplate(mongo(), "sequence"); }
	 * 
	 * @Bean(name = "addressTemplate") public MongoTemplate addressTemplate() throws Exception { return new
	 * MongoTemplate(mongo(), "address"); }
	 */

}
