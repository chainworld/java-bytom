package bytom.api;

import java.util.List;

import bytom.model.BytomException;
import bytom.model.BytomResponse;
import bytom.model.Key;
import bytom.org.json.JSONException;
import bytom.org.json.JSONObject;
import bytom.util.BytomConfig;

/**
 * 
 * @author https://github.com/JackyKen
 *
 */
public class KeysApi extends Bytom {

	private static final long serialVersionUID = 4690521388498850502L;

	/**
	 * 
	 * @param alias name of the key.
	 * @param password password of the key.
	 * @return
	 * @throws BytomException
	 * @throws JSONException
	 * 
	 */
	public BytomResponse createKey(String alias, String password) throws BytomException,
			JSONException {
		JSONObject body = new JSONObject();
		body.put("alias", alias);
		body.put("password", password);
		return new BytomResponse(client.post(
				BytomConfig.getValue("baseURL") + "create-key", body).asJSONObject());
	}

	/**
	 * @return keys owned by the client.
	 * @throws BytomException
	 */
	public List<Key> listKeys() throws BytomException {
		return Key.constructKeys(client.post(
				BytomConfig.getValue("baseURL") + "list-keys", null).asJSONObject());
	}

	/**
	 * 
	 * @param xpub
	 * @param password
	 * @return none if the key is deleted successfully.
	 * @throws BytomException
	 * @throws JSONException
	 */
	public BytomResponse deleteKey(String xpub, String password) throws BytomException,
			JSONException {
		JSONObject body = new JSONObject();
		body.put("xpub", xpub);
		body.put("password", password);
		return new BytomResponse(client.post(
				BytomConfig.getValue("baseURL") + "delete-key", body).asJSONObject());
	}

	/**
	 * @param xpub pubkey of the key.
	 * @param old_password old password of the key.
	 * @param new_password new password of the key.
	 * @return
	 * @throws BytomException
	 * @throws JSONException
	 */
	public BytomResponse resetKeyPassword(String xpub, String old_password,
			String new_password) throws BytomException, JSONException {
		JSONObject body = new JSONObject();
		body.put("xpub", xpub);
		body.put("old_password", old_password);
		body.put("new_password", new_password);
		return new BytomResponse(client.post(
				BytomConfig.getValue("baseURL") + "reset-key-password", body)
				.asJSONObject());
	}

}
