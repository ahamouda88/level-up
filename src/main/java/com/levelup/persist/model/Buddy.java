package com.levelup.persist.model;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class Buddy {

	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	@Lob
	protected byte[] imageFile;

}
