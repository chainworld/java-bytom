package com.bytom.api;

import java.util.HashMap;
import java.util.Map;

import com.bytom.exception.BytomException;
import com.bytom.http.Client;

public class Miner {

	/**
	 * 设置是否挖矿
	 * @param client
	 * @param isMining 是否挖矿
	 * @return 返回设置是否成功
	 * @throws BytomException
	 */
	public static boolean setMining(Client client, boolean isMining) throws BytomException {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("is_mining",  isMining);
		return client.requestStatus("set-mining", req);
	}
	
	/**
	 * 是否挖矿
	 * @param client
	 * @return true挖矿 false没挖矿
	 * @throws BytomException
	 */
	public static boolean istMining(Client client) throws BytomException {
		return client.requestGet("is-mining", null, "is_mining", Boolean.class);
	}
}
