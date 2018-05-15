package com.bytom.exception;

/**
 * 
 * @author https://github.com/JackyKen
 *
 */
public class BytomException extends Exception {
	private static final long serialVersionUID = 4308636092690186281L;
	private int statusCode = -1;
	private int errorCode = -1;
	private String error;

	public BytomException(String msg) {
		super(msg);
	}

	public BytomException(Exception cause) {
		super(cause);
	}

	public BytomException(String msg, int statusCode) {
		super(msg);
		this.statusCode = statusCode;
	}

	public BytomException(String msg, String data, int statusCode) {
		super(msg + "\n error reponse:" + data); 
		this.statusCode = statusCode;
	}

	public BytomException(String msg, Exception cause) {
		super(msg, cause);
	}

	public BytomException(String msg, Exception cause, int statusCode) {
		super(msg, cause);
		this.statusCode = statusCode;

	}

	public int getStatusCode() {
		return this.statusCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getError() {
		return error;
	}

}
