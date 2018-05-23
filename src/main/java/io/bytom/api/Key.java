package io.bytom.api;

import java.util.HashMap;
import java.util.Map;

import io.bytom.exception.BytomException;
import io.bytom.http.BytomResponse;
import io.bytom.http.Client;

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
	 * create
	 * @param client client
	 * @param alias name of the key.
	 * @param password password of the key.
	 * @return Key
	 * @throws BytomException Exception
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
			Items items = this.client.requestList("list-keys", null, Items.class);
			return items;
		}
	}

	public static class QueryBuilder {

		/**
		 * list
		 * @param client client
		 * @return keys owned by the client.
		 * @throws BytomException Exception
		 */
		public Items list(Client client) throws BytomException {
			Items items = new Items();
			items.setClient(client);
			return items.query();
		}
	}

	/**
	 * return none if the key is deleted successfully
	 * @param client client
	 * @param xpub xpub
	 * @param password password
	 * @return flag
	 * @throws BytomException Exception
	 */
	public static boolean delete(Client client, String xpub, String password) throws BytomException {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("xpub", xpub);
		req.put("password", password);
		return client.request("delete-key", req); 
	}

	/**
	 * 
	 * @param client vclient
	 * @param xpub xpub pubkey of the key
	 * @param old_password old password of the key
	 * @param new_password new password of the key
	 * @return none if the key password is reset successfully
	 * @throws BytomException BytomException
	 */
	public static boolean resetPassword(Client client, String xpub, String old_password,
			String new_password) throws BytomException {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("xpub", xpub);
		req.put("old_password", old_password);
		req.put("new_password", new_password);
		return client.request("reset-key-password", req);
	}
}
