package com.bytom.api;

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

}
