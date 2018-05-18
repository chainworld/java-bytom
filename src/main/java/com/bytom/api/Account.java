package com.bytom.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bytom.exception.BytomException;
import com.bytom.http.BytomResponse;
import com.bytom.http.Client;
import com.google.gson.annotations.SerializedName;

/**
 * @author https://github.com/JackyKen
 */
public class Account {

	/**
	 * account id
	 */
	public String id;

	/**
	 * name of account
	 */
	public String alias;

	/**
	 * key index of account
	 */
	@SerializedName("key_index")
	public int keyIndex;
	/**
	 * threshold of keys that must sign a transaction to spend asset
	 */
	public int quorum;

	public List<String> xpubs;

	public static class Builder {

		@SerializedName(value = "root_xpubs")
		public List<String> xpubs;
		/**
		 * name of account
		 */
		public String alias;
		/**
		 * threshold of keys that must sign a transaction to spend asset
		 */
		public int quorum;

		public String access_token;

		public Builder() {
			xpubs = new ArrayList<String>();
		}

		/**
		 * create-account
		 * @return Account
		 * @throws BytomException
		 * @throws JSONException
		 */
		public Account create(Client client) throws BytomException {
			return client.request("create-account", this, Account.class);
		}

		/**
		 * @param quorom the quorum to set
		 */
		public Builder setQuorum(int quorum) {
			this.quorum = quorum;
			return this;
		}

		public Builder setAlias(String alias) {
			this.alias = alias;
			return this;
		}

		/**
		 * 
		 * @param access_token the access_token to set
		 * @return
		 */
		public Builder setAccessToken(String access_token) {
			this.access_token = access_token;
			return this;
		}

		/**
		 * @param xpubs the xpubs to set
		 */
		public Builder setXpubs(List<String> xpubs) {
			this.xpubs = new ArrayList<String>(xpubs);
			return this;
		}

		/**
		 * @param xpub
		 * @return
		 */
		public Builder addXpub(String xpub) {
			this.xpubs.add(xpub);
			return this;
		}
	}

	public static class Items extends BytomResponse<Account> {
		public Items query() throws BytomException {
			Items items = this.client.requestList("list-accounts", null, Items.class);
			return items;
		}
	}

	public static class QueryBuilder {

		/**
		 * list-accounts
		 * @return
		 * @throws BytomException
		 */
		public Items list(Client client) throws BytomException {
			Items items = new Items();
			items.setClient(client);
			return items.query();
		}

	}

	public static class ReceiverBuilder {
		@SerializedName("account_alias")
		public String accountAlias;

		@SerializedName("account_id")
		public String accountId;

		/**
		 * 
		 * @param client the client object providing access to an instance of Bytom Core
		 * @return a new Receiver object
		 * @throws BytomException
		 */
		public Receiver create(Client client) throws BytomException {
			return client.request("create-account-receiver", this, Receiver.class);
		}

		/**
		 * Specifies the account under which the receiver is created. You must use this
		 * method or @{link ReceiverBuilder#setAccountId}, but not both.
		 *
		 * @param alias the unique alias of the account
		 * @return this ReceiverBuilder object
		 */
		public ReceiverBuilder setAccountAlias(String alias) {
			this.accountAlias = alias;
			return this;
		}

		/**
		 * Specifies the account under which the receiver is created. You must use this
		 * method or @{link ReceiverBuilder#setAccountAlias}, but not both.
		 *
		 * @param id the unique ID of the account
		 * @return this ReceiverBuilder object
		 */
		public ReceiverBuilder setAccountId(String id) {
			this.accountId = id;
			return this;
		}

	}

	public static class AddressBuilder {

		@SerializedName("account_alias")
		public String accountAlias;

		@SerializedName("account_id")
		public String accountId;

		public class Items extends BytomResponse<Address> {
			public Items query() throws BytomException {
				Items items = this.client
						.requestList("list-addresses", this, Items.class);
				return items;
			}
		}

		public Items list(Client client) throws BytomException {
			Items items = new Items();
			items.setClient(client);
			return items.query();
		}

		/**
		 * check whether the address is vaild or not.
		 * @param client
		 * @param address
		 * @return
		 * @throws BytomException
		 */
		public Address validate(Client client, String address) throws BytomException {
			Map<String, Object> req = new HashMap<String, Object>();
			req.put("address", address);
			return client.request("validate-address", req, Address.class);
		}

		/**
		 * Specifies the account under which the receiver is created. You must use this
		 * method or @{link AddressBuilder#setAccountId}, but not both.
		 *
		 * @param alias the unique alias of the account
		 * @return this AddressBuilder object
		 */
		public AddressBuilder setAccountAlias(String alias) {
			this.accountAlias = alias;
			return this;
		}

		/**
		 * Specifies the account under which the receiver is created. You must use this
		 * method or @{link AddressBuilder#setAccountId}, but not both.
		 *
		 * @param alias the unique alias of the account
		 * @return this AddressBuilder object
		 */
		public AddressBuilder setAccountId(String id) {
			this.accountId = id;
			return this;
		}

	}

	/**
	 * @param client
	 * @param account_info
	 * @throws BytomException
	 */
	public void delete(Client client, String account_info) throws BytomException {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("account_info", account_info);
		client.request("delete-account", req, Account.class);
	}

}
