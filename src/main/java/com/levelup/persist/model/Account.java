package com.levelup.persist.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A POJO class that represents an account in the system
 * 
 * @author ahamouda
 *
 */
@Document(collection = "accounts")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String username;
	private String password;
	private Buddy buddy;
	private List<BuddyRole> roles = new ArrayList<>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Buddy getBuddy() {
		return buddy;
	}

	public void setBuddy(Buddy buddy) {
		this.buddy = buddy;
	}

	public List<BuddyRole> getRoles() {
		return roles;
	}

	public void setRoles(List<BuddyRole> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buddy == null) ? 0 : buddy.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Account other = (Account) obj;
		if (buddy == null) {
			if (other.buddy != null) return false;
		} else if (!buddy.equals(other.buddy)) return false;
		if (password == null) {
			if (other.password != null) return false;
		} else if (!password.equals(other.password)) return false;
		if (roles == null) {
			if (other.roles != null) return false;
		} else if (!roles.equals(other.roles)) return false;
		if (username == null) {
			if (other.username != null) return false;
		} else if (!username.equals(other.username)) return false;
		return true;
	}

}
