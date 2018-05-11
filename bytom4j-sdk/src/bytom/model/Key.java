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
public class Key implements Serializable {

	private static final long serialVersionUID = 5805192801504544739L;

	private String alias;// name of the key.
	private String xpub;// pubkey of the key.
	private String file;// path to the file of key.

	public Key(String data) throws BytomException {
		try {
			JSONObject json = new JSONObject(data);
			this.alias = json.getString("alias");
			this.xpub = json.getString("xpub");
			this.file = json.getString("file");
		}
		catch (JSONException je) {
			throw new BytomException(je.getMessage() + ":" + data.toString(), je);
		}
	}

	public Key(JSONObject json) throws BytomException {
		try {
			this.alias = json.getString("alias");
			this.xpub = json.getString("xpub");
			this.file = json.getString("file");
		}
		catch (JSONException je) {
			throw new BytomException(je.getMessage() + ":" + json.toString(), je);
		}
	}

	public static List<Key> constructKeys(JSONObject json) throws BytomException {
		try {
			JSONArray data = json.getJSONArray("data");
			int size = data.length();
			List<Key> keys = new ArrayList<Key>(size);
			for (int i = 0; i < size; i++) {
				keys.add(new Key(data.getJSONObject(i)));
			}
			return keys;
		}
		catch (JSONException jsone) {
			throw new BytomException(jsone);
		}
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
	 * @return the xpub
	 */
	public String getXpub() {
		return xpub;
	}

	/**
	 * @param xpub the xpub to set
	 */
	public void setXpub(String xpub) {
		this.xpub = xpub;
	}

	/**
	 * @return the file
	 */
	public String getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(String file) {
		this.file = file;
	}

}
