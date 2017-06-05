package com.levelup.response.model;

import java.io.Serializable;

/**
 * An abstract class that represents a response object following the JSend model http://labs.omniti.com/labs/jsend
 * 
 * @author ahamouda
 *
 * @param <T>
 *            the datatype of the response data
 */
public abstract class BaseResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Status status;
	private T data;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	/**
	 * A method that returns a standard status string
	 * 
	 * @return a standard status string, or <i><b>null</b></i> if {@link #status} is null
	 */
	public String getStatusString() {
		StringBuilder sb = new StringBuilder("{status: ");
		if (status != null) {
			sb.append(status.toString());
		}
		sb.append('}');
		return sb.toString();
	}
}
