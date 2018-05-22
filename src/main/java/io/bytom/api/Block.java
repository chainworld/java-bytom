package io.bytom.api;

import java.util.List;

import io.bytom.exception.BytomException;
import io.bytom.http.Client;
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
	public String hash;

	/**
	 * size
	 */
	public long size;

	/**
	 * 版本
	 */
	public long version;

	/**
	 * 高度
	 */
	public long height;

	/**
	 * 前一块hash
	 */
	@SerializedName("previous_block_hash")
	public String previousBlockHash;

	/**
	 * 时间
	 */
	public long timestamp;

	/**
	 * nonce
	 */
	public long nonce;

	/**
	 * bits
	 */
	public long bits;

	/**
	 * 难度值ֵ
	 */
	public String difficulty;

	/**
	 * merkle_root
	 */
	@SerializedName("transaction_merkle_root")
	public String transactionsMerkleRoot;
	/**
	 * 
	 */
	@SerializedName("transaction_status_hash")
	public String transactionStatusHash;

	public List<BlockTx> transactions;

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
	 * get-block-hash
	 * 
	 * @return
	 * @throws BytomException
	 */
	public static String getBlockHash(Client client) throws BytomException {
		return client.requestGet("get-block-hash", null, "block_hash", String.class);
	}

	public static class QueryBuilder {

		/**
		 * block_height, height of block.
		 */
		@SerializedName("block_height")
		public int blockHeight;
		/**
		 * block_hash, hash of block.
		 */
		public String blockHash;

		public QueryBuilder setBlockHeight(int blockHeight) {
			this.blockHeight = blockHeight;
			return this;
		}

		public QueryBuilder setBlockHash(String blockHash) {
			this.blockHash = blockHash;
			return this;
		}

		/**
		 * get-block
		 * 
		 * @param client
		 *            httpclient
		 * @return block
		 * @throws BytomException
		 *             异常
		 */
		public Block getBlock(Client client) throws BytomException {
			return client.request("get-block", this, Block.class);
		}

		/**
		 * get-block-header
		 * 
		 * @param client
		 *            httpclient
		 * @return BlockHeader
		 * @throws BytomException
		 *             异常
		 */
		public BlockHeader getBlockHeader(Client client) throws BytomException {
			return client.request("get-block-header", this, BlockHeader.class);
		}

		/**
		 * get-difficulty
		 * 
		 * @param client
		 *            httpclient
		 * @return BlockHeader
		 * @throws BytomException
		 *             异常
		 */
		public BlockDifficulty getBlockDifficulty(Client client) throws BytomException {
			return client.request("get-difficulty", this, BlockDifficulty.class);
		}

		/**
		 * get-hash-rate
		 * 
		 * @param client
		 *            httpclient
		 * @return BlockHeader
		 * @throws BytomException
		 *             异常
		 */
		public BlockHashRate getHashRate(Client client) throws BytomException {
			return client.request("get-hash-rate", this, BlockHashRate.class);
		}
	}

	/**
	 * block_header
	 * 
	 * @author niyue
	 *
	 */
	public static class BlockHeader {
		@SerializedName("block_header")
		public String blockHeader;
		public long reward;
	}

	/**
	 * block_difficulty
	 * 
	 * @author niyue
	 *
	 */
	public static class BlockDifficulty {
		public String hash;
		public long height;
		public long bits;
		public long difficulty;

	}

	/**
	 * block_hashRate
	 * 
	 * @author niyue
	 *
	 */
	public static class BlockHashRate {
		public String hash;
		public long height;
		public long hash_rate;

	}

	@Override
	public String toString() {
		return "Block [hash=" + hash + ", size=" + size + ", version=" + version + ", height=" + height
				+ ", previousBlockHash=" + previousBlockHash + ", timestamp=" + timestamp + ", nonce=" + nonce
				+ ", bits=" + bits + ", difficulty=" + difficulty + ", transactionsMerkleRoot=" + transactionsMerkleRoot
				+ ", transactionStatusHash=" + transactionStatusHash + ", transactions=" + transactions + "]";
	}

}
