package com.levelup.persist.model;

import javax.persistence.Lob;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A POJO class that represents a buddy/user in the system
 * 
 * @author ahamouda
 *
 */
@Document(collection = "buddies")
public class Buddy {

	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private Address address;
	@Lob
	protected byte[] imageFile;
}
