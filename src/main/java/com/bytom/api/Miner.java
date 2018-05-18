package com.bytom.api;

import java.util.HashMap;
import java.util.Map;

import com.bytom.exception.BytomException;
import com.bytom.http.Client;
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
		return client.requestStatus("set-mining", req);
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
	public static void submiWork(Client client, String blockHeader) throws BytomException {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("block_header", blockHeader);
		client.request("submit-work", req);
	}

	public static class MinerWork {
		@SerializedName("block_header")
		private String blockHeader;
		private String seed;

		public String getBlockHeader() {
			return blockHeader;
		}

		public void setBlockHeader(String blockHeader) {
			this.blockHeader = blockHeader;
		}

		public String getSeed() {
			return seed;
		}

		public void setSeed(String seed) {
			this.seed = seed;
		}

	}
}
