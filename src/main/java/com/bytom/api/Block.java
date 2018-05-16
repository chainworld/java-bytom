package com.bytom.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bytom.api.model.BlockTx;
import com.bytom.exception.BytomException;
import com.bytom.http.Client;
import com.google.gson.annotations.SerializedName;

/**
 * Block
 * 
 * @author niyuelin1990
 */
public class Block {
	/**
	 * Block hash
	 */
	private String hash;

	/**
	 * size
	 */
	private long size;

	/**
	 * 版本
	 */
	private long version;

	/**
	 * 高度
	 */
	private long height;

	/**
	 * 前一块hash
	 */
	@SerializedName("previous_block_hash")
	private String previousBlockHash;

	/**
	 * 时间
	 */
	private long timestamp;

	/**
	 * 
	 */
	private long nonce;

	/**
	 * 
	 */
	private long bits;

	/**
	 * 难度值
	 */
	private String difficulty;

	/**
	 * merkle_root
	 */
	@SerializedName("transaction_merkle_root")
	private String transactionsMerkleRoot;
	/**
	 * 
	 */
	@SerializedName("transaction_status_hash")
	private String transactionStatusHash;

	private List<BlockTx> transactions;

	/**
	 * get-block-count
	 * 
	 * @return
	 * @throws BytomException
	 */
	public static int getBlockCount(Client client) throws BytomException {
		return client.requestGet("get-block-count", null, "block_count", Integer.class);
	}

	/**
	 * get-block-count
	 * 
	 * @return
	 * @throws BytomException
	 */
	public static String getBlockHash(Client client) throws BytomException {
		return client.requestGet("get-block-hash", null, "block_hash", String.class);
	}

	/**
	 * 获取块
	 * 
	 * @param client
	 * @return
	 * @throws BytomException
	 */
	public static Block getBlock(Client client, int blockHeight, String blockHash) throws BytomException {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("block_height", blockHeight);
		req.put("block_hash", blockHash);
		return client.request("get-block", req, Block.class);
	}

	/**
	 * 获取块头
	 * 
	 * @param client
	 * @param blockHeight
	 * @param blockHash
	 * @return
	 * @throws BytomException
	 */
	public static BlockHeader getBlockHeader(Client client, int blockHeight, String blockHash) throws BytomException {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("block_height", blockHeight);
		req.put("block_hash", blockHash);
		return client.request("get-block-header", req, BlockHeader.class);
	}

	/**
	 * 获取块Difficulty
	 * @param client
	 * @param blockHeight
	 * @param blockHash
	 * @return
	 * @throws BytomException
	 */
	public static BlockDifficulty getBlockDifficulty(Client client, int blockHeight, String blockHash)
			throws BytomException {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("block_height", blockHeight);
		req.put("block_hash", blockHash);
		return client.request("get-difficulty", req, BlockDifficulty.class);
	}
	
	/**
	 * 获取块HashRate
	 * @param client
	 * @param blockHeight
	 * @param blockHash
	 * @return
	 * @throws BytomException
	 */
	public static BlockHashRate getHashRate(Client client, int blockHeight, String blockHash)
			throws BytomException {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("block_height", blockHeight);
		req.put("block_hash", blockHash);
		return client.request("get-hash-rate", req, BlockHashRate.class);
	}


	public static class BlockHeader {
		@SerializedName("block_header")
		private String blockHeader;
		private long reward;

		public String getBlockHeader() {
			return blockHeader;
		}

		public void setBlockHeader(String blockHeader) {
			this.blockHeader = blockHeader;
		}

		public long getReward() {
			return reward;
		}

		public void setReward(long reward) {
			this.reward = reward;
		}

	}

	public static class BlockDifficulty {
		private String hash;
		private long height;
		private long bits;
		private long difficulty;

		public String getHash() {
			return hash;
		}

		public void setHash(String hash) {
			this.hash = hash;
		}

		public long getHeight() {
			return height;
		}

		public void setHeight(long height) {
			this.height = height;
		}

		public long getBits() {
			return bits;
		}

		public void setBits(long bits) {
			this.bits = bits;
		}

		public long getDifficulty() {
			return difficulty;
		}

		public void setDifficulty(long difficulty) {
			this.difficulty = difficulty;
		}

	}

	public static class BlockHashRate {
		private String hash;
		private long height;
		private long hash_rate;

		public String getHash() {
			return hash;
		}

		public void setHash(String hash) {
			this.hash = hash;
		}

		public long getHeight() {
			return height;
		}

		public void setHeight(long height) {
			this.height = height;
		}

		public long getHash_rate() {
			return hash_rate;
		}

		public void setHash_rate(long hash_rate) {
			this.hash_rate = hash_rate;
		}

	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public long getHeight() {
		return height;
	}

	public void setHeight(long height) {
		this.height = height;
	}

	public String getPreviousBlockHash() {
		return previousBlockHash;
	}

	public void setPreviousBlockHash(String previousBlockHash) {
		this.previousBlockHash = previousBlockHash;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getNonce() {
		return nonce;
	}

	public void setNonce(long nonce) {
		this.nonce = nonce;
	}

	public long getBits() {
		return bits;
	}

	public void setBits(long bits) {
		this.bits = bits;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getTransactionsMerkleRoot() {
		return transactionsMerkleRoot;
	}

	public void setTransactionsMerkleRoot(String transactionsMerkleRoot) {
		this.transactionsMerkleRoot = transactionsMerkleRoot;
	}

	public String getTransactionStatusHash() {
		return transactionStatusHash;
	}

	public void setTransactionStatusHash(String transactionStatusHash) {
		this.transactionStatusHash = transactionStatusHash;
	}

	public List<BlockTx> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<BlockTx> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "Block [hash=" + hash + ", size=" + size + ", version=" + version + ", height=" + height
				+ ", previousBlockHash=" + previousBlockHash + ", timestamp=" + timestamp + ", nonce=" + nonce
				+ ", bits=" + bits + ", difficulty=" + difficulty + ", transactionsMerkleRoot=" + transactionsMerkleRoot
				+ ", transactionStatusHash=" + transactionStatusHash + ", transactions=" + transactions + "]";
	}

}
