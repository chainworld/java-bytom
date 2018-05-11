package bytom.api;

import bytom.model.BytomException;
import bytom.model.BytomResponse;
import bytom.org.json.JSONException;
import bytom.org.json.JSONObject;
import bytom.util.BytomConfig;
/**
 * 
 * @author https://github.com/JackyKen
 *
 */
public class AccountsApi extends Bytom {

	private static final long serialVersionUID = 4267067392499482288L;

	/**
	 * 
	 * @param root_xpubs,pubkey array.
	 * @param alias,name of the account.
	 * @param quorum ,the default value is 1, threshold of keys that must sign a
	 * transaction to spend asset units controlled by the account.
	 * @param accesstoken ,if optional when creating account locally. However, if you want
	 * to create account remotely, it's indispensable.
	 * @return
	 * @throws BytomException
	 * @throws JSONException
	 */
	public BytomResponse createAccount(String[] root_xpubs, String alias, Integer quorum,
			String access_token) throws BytomException, JSONException {
		JSONObject body = new JSONObject();
		body.put("root_xpubs", root_xpubs);
		body.put("alias", alias);
		body.put("quorum", quorum);
		body.put(access_token, access_token);
		return new BytomResponse(client.post(
				BytomConfig.getValue("baseURL") + "create-account", body).asJSONObject());
	}

	public BytomResponse getListAccounts() throws BytomException {
		return new BytomResponse(client.post(
				BytomConfig.getValue("baseURL") + "list-accounts", null).asJSONObject());
	}
}
