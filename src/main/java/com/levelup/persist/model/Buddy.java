package com.levelup.persist.model;

import java.util.Arrays;

import javax.persistence.Lob;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A POJO class that represents a buddy/user in the system
 * 
 * @author ahamouda
 *
 */
@Document(collection = "buddy")
public class Buddy {

	@Id
	@Indexed
	private Long id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String address1;
	private String address2;
	private Integer zipcode;
	private String city;
	private String state;
	private String country;
	@Lob
	protected byte[] imageFile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public byte[] getImageFile() {
		return imageFile;
	}

	public void setImageFile(byte[] imageFile) {
		this.imageFile = imageFile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address1 == null) ? 0 : address1.hashCode());
		result = prime * result + ((address2 == null) ? 0 : address2.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Arrays.hashCode(imageFile);
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Buddy other = (Buddy) obj;
		if (address1 == null) {
			if (other.address1 != null) return false;
		} else if (!address1.equals(other.address1)) return false;
		if (address2 == null) {
			if (other.address2 != null) return false;
		} else if (!address2.equals(other.address2)) return false;
		if (city == null) {
			if (other.city != null) return false;
		} else if (!city.equals(other.city)) return false;
		if (country == null) {
			if (other.country != null) return false;
		} else if (!country.equals(other.country)) return false;
		if (email == null) {
			if (other.email != null) return false;
		} else if (!email.equals(other.email)) return false;
		if (firstName == null) {
			if (other.firstName != null) return false;
		} else if (!firstName.equals(other.firstName)) return false;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		if (!Arrays.equals(imageFile, other.imageFile)) return false;
		if (lastName == null) {
			if (other.lastName != null) return false;
		} else if (!lastName.equals(other.lastName)) return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null) return false;
		} else if (!phoneNumber.equals(other.phoneNumber)) return false;
		if (state == null) {
			if (other.state != null) return false;
		} else if (!state.equals(other.state)) return false;
		if (zipcode == null) {
			if (other.zipcode != null) return false;
		} else if (!zipcode.equals(other.zipcode)) return false;
		return true;
	}

}
