package com.bytom.api;

import java.util.ArrayList;
import java.util.List;

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
	public Integer key_index;
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
	 * @return
	 * @throws BytomException
	 * @throws JSONException
	 */
	public Account create(Client client) throws BytomException {
		return client.request("create-account", this, Account.class);
	}

	public class Items extends BytomResponse<Account> {
		public Items query() throws BytomException {
			Items items = this.client.requestList("list-accounts", null, Items.class);
			return items;
		}
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
	public Account setKey_index(Integer key_index) {
		this.key_index = key_index;
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

	public Account addXpub(String xpub) {
		this.xpubs.add(xpub);
		return this;
	}
}
