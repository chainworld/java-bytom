package com.bytom.api;

import com.bytom.common.Utils;
import com.bytom.exception.JSONException;
import com.google.gson.annotations.SerializedName;

public class Receiver {

	@SerializedName("control_program")
	public String controlProgram;

	@SerializedName("address")
	public String address;
	
	/**
	 * Serializes the receiver into a form that is safe to transfer over the wire.
	 *
	 * @return the JSON-serialized representation of the Receiver object
	 */
	public String toJson() {
		return Utils.serializer.toJson(this);
	}

	/**
	 * Deserializes a Receiver from JSON.
	 *
	 * @param json a JSON-serialized Receiver object
	 * @return the deserialized Receiver object
	 * @throws JSONException Raised if the provided string is not valid JSON.
	 */
	public static Receiver fromJson(String json) throws JSONException {
		try {
			return Utils.serializer.fromJson(json, Receiver.class);
		}
		catch (IllegalStateException e) {
			throw new JSONException("Unable to parse serialized receiver: "
					+ e.getMessage());
		}
	}

}
