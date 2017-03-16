package com.levelup.persist.entity;

import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

@Entity
public class Buddy {

	private String firstName;
	private String lastName;
	private String phoneNumber;
	@NotNull
	private String email;
	@Lob
	protected byte[] imageFile;

}
