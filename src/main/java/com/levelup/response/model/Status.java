package com.levelup.response.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Response Status model POJO
 * 
 * @author ahamouda
 *
 */
public class Status {

	/**
	 * The response status code
	 */
	public enum Code {
		/**
		 * All went well, and (usually) some data was returned.
		 */
		SUCCESS,
		/**
		 * There was a problem with the data submitted, or some pre-condition of the API call wasn't satisfied
		 */
		FAIL,
		/**
		 * An error occurred in processing the request, i.e. an exception was thrown
		 */
		ERROR;
	}

	private Code code;
	private String message;
	private List<Error> errors;

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	public void addError(Error error) {
		if (this.errors == null) this.errors = new ArrayList<>(1);
		this.errors.add(error);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{code : ");
		sb.append(code);
		sb.append(", message : ");
		sb.append(message);
		sb.append(", errors : [");

		if (errors != null && !errors.isEmpty()) {
			String errStr = errors.stream().map(err -> err.toString()).collect(Collectors.joining(","));
			sb.append(errStr);
		}
		sb.append("]}");
		return sb.toString();
	}
}