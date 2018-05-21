package io.bytom.api;

import io.bytom.exception.BytomException;
import io.bytom.http.Client;
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
	
	
	public static class VerifyBuilder {

		/**
		 * address, address for account.
		 */
		public String address;
		/**
		 * derived_xpub, derived xpub.
		 */
		public String derived_xpub;
		/**
		 * message, message for signature by derived_xpub.
		 */
		public String message;
		/**
		 * signature, signature for message.
		 */
		public String signature;


		public VerifyBuilder setAddress(String address) {
			this.address = address;
			return this;
		}


		public VerifyBuilder setDerived_xpub(String derived_xpub) {
			this.derived_xpub = derived_xpub;
			return this;
		}


		public VerifyBuilder setMessage(String message) {
			this.message = message;
			return this;
		}


		public VerifyBuilder setSignature(String signature) {
			this.signature = signature;
			return this;
		}


		/**
		 * verify-message
		 * @param client httpclient
		 * @return  boolean
		 * @throws BytomException 异常
		 */
		public boolean verifyMessage(Client client) throws BytomException {
			return  client.requestGet("verify-message", this, "result", Boolean.class);
		}

	}

}
