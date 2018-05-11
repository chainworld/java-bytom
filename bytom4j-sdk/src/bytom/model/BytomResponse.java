package bytom.model;

import bytom.org.json.JSONException;
import bytom.org.json.JSONObject;

/**
 * 
 * @author https://github.com/JackyKen
 *
 */
public class BytomResponse implements java.io.Serializable {

	private static final long serialVersionUID = -2125360936459641769L;

	private String status;
	private String data;
	private String msg;

	public BytomResponse(JSONObject json) throws BytomException {
		try {
			this.status = json.getString("status");
			this.data = json.getString("data");
			this.msg = json.getString("msg");
		}
		catch (JSONException je) {
			throw new BytomException(je.getMessage() + ":" + json.toString(), je);
		}
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
