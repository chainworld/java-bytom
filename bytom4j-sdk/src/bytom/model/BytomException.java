package bytom.model;

import bytom.org.json.JSONException;
import bytom.org.json.JSONObject;

/**
 * 
 * @author https://github.com/JackyKen
 *
 */
public class BytomException extends Exception {
	private static final long serialVersionUID = 4308636092690186281L;
	private int statusCode = -1;
	private int errorCode = -1;
	private String request;
	private String error;

	public BytomException(String msg) {
		super(msg);
	}

	public BytomException(Exception cause) {
		super(cause);
	}

	public BytomException(String msg, int statusCode) throws JSONException {
		super(msg);
		this.statusCode = statusCode;
	}

	public BytomException(String msg, JSONObject json, int statusCode)
			throws JSONException {
		super(msg + "\n error:" + json.getString("error") + " error_code:"
				+ json.getInt("error_code") + json.getString("request"));
		this.statusCode = statusCode;
		this.errorCode = json.getInt("error_code");
		this.error = json.getString("error");
		this.request = json.getString("request");

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

	public String getRequest() {
		return request;
	}

	public String getError() {
		return error;
	}

}
