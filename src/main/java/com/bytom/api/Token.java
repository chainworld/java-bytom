package com.bytom.api;

import java.util.HashMap;
import java.util.Map;

import com.bytom.exception.BytomException;
import com.bytom.http.BytomResponse;
import com.bytom.http.Client;
import com.google.gson.annotations.SerializedName;

/**
 * token api create, get, list, check
 * 
 * @author niyuelin1990
 *
 */
public class Token {
	private String id;
	private String token;
	private String type;
	@SerializedName(value = "created_at", alternate = { "create" })
	private String create;

	/**
	 * create token by id 该接口远程连接不可用
	 * 
	 * @param client
	 * @param id
	 * @return
	 * @throws BytomException
	 */
	public static Token create(Client client, String id, String type) throws BytomException {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("id", id);
		req.put("type", type);
		return client.request("create-access-token", req, Token.class);
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
		return client.requestStatus("check-access-token", req);
	}
	
	
	/**
	 * 删除token
	 * @param client
	 * @param id
	 * @return
	 * @throws BytomException
	 */
	public static boolean delete(Client client, String id) throws BytomException {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("id", id);
		return client.requestStatus("delete-access-token", req);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreate() {
		return create;
	}

	public void setCreate(String create) {
		this.create = create;
	}

}
