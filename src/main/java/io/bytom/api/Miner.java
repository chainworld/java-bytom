package io.bytom.api;

import java.util.HashMap;
import java.util.Map;

import io.bytom.exception.BytomException;
import io.bytom.http.Client;
import com.google.gson.annotations.SerializedName;

public class Miner {

	/**
	 *  设置是否挖矿
	 * 
	 * @param client
	 * @param isMining
	 *            是否挖矿
	 * @return 返回设置是否成功
	 * @throws BytomException
	 */
	public static boolean setMining(Client client, boolean isMining) throws BytomException {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("is_mining", isMining);
		return client.request("set-mining", req);
	}

	/**
	 * 是否挖矿
	 * 
	 * @param client
	 * @return true挖矿 false没挖矿
	 * @throws BytomException
	 */
	public static boolean istMining(Client client) throws BytomException {
		return client.requestGet("is-mining", null, "is_mining", Boolean.class);
	}

	/**
	 * get-work
	 * @param client
	 * @return
	 * @throws BytomException
	 */
	public static MinerWork getWork(Client client) throws BytomException {
		return client.request("get-work", null, MinerWork.class);
	}
	
	/**
	 * submit-work
	 * @param client
	 * @param blockHeader
	 * @return
	 * @throws BytomException
	 */
	public static boolean submiWork(Client client, String blockHeader) throws BytomException {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("block_header", blockHeader);
		return client.request("submit-work", req); 
	}

	public static class MinerWork {
		/**
		 * block_header, raw block header.
		 */
		@SerializedName("block_header")
		public String blockHeader;
		/**
		 *  seed, seed.
		 */
		public String seed;

	}
}
