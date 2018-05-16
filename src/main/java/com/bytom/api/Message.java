package com.bytom.api;

import java.util.HashMap;
import java.util.Map;

import com.bytom.exception.BytomException;
import com.bytom.http.Client;
import com.google.gson.annotations.SerializedName;

/**
 * @author https://github.com/JackyKen
 */
public class Message {

	@SerializedName("derived_xpub")
	public String derivedXpub;
	public String signature;

	public static class SignBuilder {

		public String address;
		public String message;
		public String password;

		public SignBuilder setAddress(String address) {
			this.address = address;
			return this;
		}

		public SignBuilder setMessage(String message) {
			this.message = message;
			return this;
		}

		public SignBuilder setPassword(String password) {
			this.password = password;
			return this;
		}

		public Message sign(Client client) throws BytomException {
			return client.request("sign-message", this, Message.class);
		}

	}
	
	/**
	 * 
	 * @param client
	 * @param address address, address for account.
	 * @param derived_xpub erived xpub.
	 * @param message  message for signature by derived_xpub.
	 * @param signature signature for message.
	 * @return
	 * @throws BytomException
	 */
	public static boolean verifyMessage(Client client, String address, String derived_xpub, String message,
			String signature) throws BytomException {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("address", address);
		req.put("derived_xpub", derived_xpub);
		req.put("message", message);
		req.put("signature", signature);
		return client.requestGet("verify-message", req, "result", Boolean.class);
	}

}
