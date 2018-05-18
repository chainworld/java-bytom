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
	public Integer keyIndex;
	/**
	 * threshold of keys that must sign a transaction to spend asset
	 */
	public Integer quorum;

	/**
	 * xpubs
	 */
	@SerializedName(value = "root_xpubs", alternate = { "xpubs" })
	public List<String> xpubs;

	public Account() {
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
	 * list-accounts
	 * @return
	 * @throws BytomException
	 */
	public Items list(Client client) throws BytomException {
		Items items = new Items();
		items.setClient(client);
		return items.query();
	}

	public class Items extends BytomResponse<Account> {
		public Items query() throws BytomException {
			Items items = this.client.requestList("list-accounts", null, Items.class);
			return items;
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

	/**
	 * @param id the id to set
	 */
	public Account setId(String id) {
		this.id = id;
		return this;
	}

	/**
	 * @param alias the alias to set
	 */
	public Account setAlias(String alias) {
		this.alias = alias;
		return this;
	}

	/**
	 * @param key_index the key_index to set
	 */
	public Account setKeyIndex(Integer keyIndex) {
		this.keyIndex = keyIndex;
		return this;
	}

	/**
	 * @param quorom the quorum to set
	 */
	public Account setQuorum(Integer quorum) {
		this.quorum = quorum;
		return this;
	}

	/**
	 * @param xpubs the xpubs to set
	 */
	public Account setXpubs(List<String> xpubs) {
		this.xpubs = new ArrayList<String>(xpubs);
		return this;
	}

	/**
	 * @param xpub
	 * @return
	 */
	public Account addXpub(String xpub) {
		this.xpubs.add(xpub);
		return this;
	}

	public static class ReceiverBuilder {
		@SerializedName("account_alias")
		public String accountAlias;

		@SerializedName("account_id")
		public String accountId;

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

		/**
		 * 
		 * @param client the client object providing access to an instance of Bytom Core
		 * @return a new Receiver object
		 * @throws BytomException
		 */
		public Receiver create(Client client) throws BytomException {
			return client.request("create-account-receiver", this, Receiver.class);
		}

	}

	public static class AddressBuilder {

		@SerializedName("account_alias")
		public String accountAlias;

		@SerializedName("account_id")
		public String accountId;

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

	}
}
