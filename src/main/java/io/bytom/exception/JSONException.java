package io.bytom.exception;

/**
 * JSONException wraps errors due to marshaling/unmarshaling json payloads.
 */
public class JSONException extends BytomException {

	private static final long serialVersionUID = -3448839588666268399L;

	/**
	 * Default constructor.
	 * @param message message
	 */
	public JSONException(String message) {
		super(message);
	}

	public String getMessage() {
		String message = "Message: " + super.getMessage();
		return message;
	}
}
