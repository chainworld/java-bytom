package com.bytom.api;

import java.util.HashMap;
import java.util.Map;

import com.bytom.exception.BytomException;
import com.bytom.http.BytomResponse;
import com.bytom.http.Client;

/**
 * @author https://github.com/JackyKen
 */
public class Key {

	/**
	 * name of the key.
	 */
	public String alias;

	/**
	 * pubkey of the key.
	 */
	public String xpub;

	/**
	 * path to the file of key.
	 */
	public String file;

	/**
	 * @param alias name of the key.
	 * @param password password of the key.
	 * @return
	 * @throws BytomException
	 * @throws JSONException
	 */
	public static Key create(Client client, String alias, String password)
			throws BytomException {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("alias", alias);
		req.put("password", password);
		return client.request("create-key", req, Key.class);
	}

	public static class Items extends BytomResponse<Key> {
		public Items query() throws BytomException {
			Items items = this.client.request("list-keys", null, Items.class);
			return items;
		}
	}

	public static class QueryBuilder {
		/**
		 * @return keys owned by the client.
		 * @throws BytomException
		 */
		public Items list(Client client) throws BytomException {
			Items items = new Items();
			items.setClient(client);
			return items.query();
		}
	}

	/**
	 * return none if the key is deleted successfully
	 * @param xpub
	 * @param password
	 * @throws BytomException
	 * @throws JSONException
	 */
	public void delete(Client client, String xpub, String password) throws BytomException {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("xpub", xpub);
		req.put("password", password);
		client.request("delete-key", req);
	}

	/**
	 * @param xpub pubkey of the key
	 * @param old_password old password of the key
	 * @param new_password new password of the key
	 * @return none if the key password is reset successfully
	 * @throws BytomException
	 * @throws JSONException
	 */
	public void resetPassword(Client client, String xpub, String old_password,
			String new_password) throws BytomException {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("xpub", xpub);
		req.put("old_password", old_password);
		req.put("new_password", new_password);
		client.request("reset-key-password", req);
	}
}
