package io.bytom.api;

import java.util.HashMap;
import java.util.Map;

import io.bytom.exception.BytomException;
import io.bytom.http.BytomResponse;
import io.bytom.http.Client;
import com.google.gson.annotations.SerializedName;

/**
 * token api create, get, list, check
 * 
 * @author niyuelin1990
 *
 */
public class Token {
	/**
	 * Token id
	 */
	public String id;
	/**
	 * Token token
	 */
	public String token;
	/**
	 * Token type
	 */
	public String type;
	/**
	 * Token create时间
	 */
	@SerializedName(value = "created_at", alternate = { "create" })
	public String create;

	public static class Builder {
		/**
		 * Token id
		 */
		public String id;
		/**
		 * Token type
		 */
		public String type;

		public Builder() {
		}

		/**
		 * 
		 * @param id
		 *            the id to set
		 * @return Builder
		 */
		public Builder setId(String id) {
			this.id = id;
			return this;
		}

		/**
		 * 
		 * @param type
		 *            the type to set
		 * @return Builder
		 */
		public Builder setType(String type) {
			this.type = type;
			return this;
		}

		/**
		 * create token by id 该接口远程连接不可用
		 * 
		 * @param client
		 * @return
		 * @throws BytomException
		 */
		public Token create(Client client) throws BytomException {
			return client.request("create-access-token", this, Token.class);
		}
	}

	/**
	 * check token true or false
	 * 
	 * @param client
	 * @param alias
	 * @param filter
	 * @return
	 * @throws BytomException
	 */
	public static boolean check(Client client, String id, String secret) throws BytomException {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("id", id);
		req.put("secret", secret);
		return client.request("check-access-token", req);
	}

	/**
	 * 删除token
	 * 
	 * @param client
	 * @param id
	 * @return
	 * @throws BytomException
	 */
	public static boolean delete(Client client, String id) throws BytomException {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("id", id);
		return client.request("delete-access-token", req);
	}

	/**
	 * 列表 该接口远程连接不可用
	 * 
	 * @param client
	 * @return
	 * @throws BytomException
	 */
	public static Items list(Client client) throws BytomException {
		Items items = new Items();
		items.setClient(client);
		return items.query();
	}

	public static class Items extends BytomResponse<Token> {
		public Items query() throws BytomException {
			Items items = this.client.requestList("list-access-tokens", null, Items.class);
			return items;
		}
	}

}
