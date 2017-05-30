package com.levelup.persist.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A simple POJO class, that represents the Mongo sequences, and it is used for implementing an auto-sequence Id
 * 
 * @author ahamouda
 *
 */
@Document(collection = "sequence")
public class Sequence {

	@Id
	private String id;
	private long seq;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	@Override
	public String toString() {
		return "Sequence [id=" + id + ", seq=" + seq + "]";
	}
}
