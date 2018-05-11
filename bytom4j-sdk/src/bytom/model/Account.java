package bytom.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bytom.org.json.JSONArray;
import bytom.org.json.JSONException;
import bytom.org.json.JSONObject;

/**
 * 
 * @author https://github.com/JackyKen
 *
 */
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7335146438112866346L;
	private String id;// account id.
	private String alias;// name of account.
	private Integer key_index;// key index of account.
	private Integer quorom;// threshold of keys that must sign a transaction to spend
							// asset
	private JSONArray xpubs;// pubkey array.

	public Account(String data) throws BytomException {
		try {
			JSONObject json = new JSONObject(data);
			this.id = json.getString("id");
			this.alias = json.getString("alias");
			this.key_index = json.getInt("key_index");
			this.quorom = json.getInt("quorom");
			this.xpubs = json.getJSONArray("xpubs");
		}
		catch (JSONException je) {
			throw new BytomException(je.getMessage() + ":" + data.toString(), je);
		}
	}

	public Account(JSONObject json) throws BytomException {
		try {
			this.id = json.getString("id");
			this.alias = json.getString("alias");
			this.key_index = json.getInt("key_index");
			this.quorom = json.getInt("quorom");
			this.xpubs = json.getJSONArray("xpubs");
		}
		catch (JSONException je) {
			throw new BytomException(je.getMessage() + ":" + json.toString(), je);
		}
	}

	public static List<Account> constructAccounts(JSONObject json) throws BytomException {
		try {
			JSONArray data = json.getJSONArray("data"); 
			int size = data.length();
			List<Account> accounts = new ArrayList<Account>(size);
			for (int i = 0; i < size; i++) {
				accounts.add(new Account(data.getJSONObject(i)));
			}
			return accounts;
		}
		catch (JSONException jsone) {
			throw new BytomException(jsone);
		}
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @return the key_index
	 */
	public Integer getKey_index() {
		return key_index;
	}

	/**
	 * @param key_index the key_index to set
	 */
	public void setKey_index(Integer key_index) {
		this.key_index = key_index;
	}

	/**
	 * @return the quorom
	 */
	public Integer getQuorom() {
		return quorom;
	}

	/**
	 * @param quorom the quorom to set
	 */
	public void setQuorom(Integer quorom) {
		this.quorom = quorom;
	}

	/**
	 * @return the xpubs
	 */
	public JSONArray getXpubs() {
		return xpubs;
	}

	/**
	 * @param xpubs the xpubs to set
	 */
	public void setXpubs(JSONArray xpubs) {
		this.xpubs = xpubs;
	}

}
