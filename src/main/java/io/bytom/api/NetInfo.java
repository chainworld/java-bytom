package io.bytom.api;

import io.bytom.exception.BytomException;
import io.bytom.http.Client;
import com.google.gson.annotations.SerializedName;

/**
 * 网络状态消息
 * @author niyuelin1990
 *
 */
public class NetInfo {

	/**
	 * listening, whether the node is listening.
	 */
	public boolean listening;

	/**
	 * syncing, whether the node is syncing.
	 */
	public boolean syncing;

	/**
	 * mining, whether the node is mining.
	 */
	public boolean mining;

	/**
	 * peer_count, current count of connected peers.
	 */
	@SerializedName("peer_count")
	public int peerCount;

	/**
	 * current_block, current block height in the node's blockchain.
	 */
	@SerializedName("current_block")
	public long currentBlock;

	/**
	 * highest_block, current highest block of the connected peers.
	 */
	@SerializedName("highest_block")
	public long highestBlock;

	/**
	 * network_id, network id.
	 */
	@SerializedName("network_id")
	public String netWorkID;

	/**
	 * version, bytom version.
	 */
	@SerializedName("version")
	public String version;

	public static NetInfo getNetInfo(Client client) throws BytomException {
		return client.request("net-info", null, NetInfo.class);
	}


	@Override
	public String toString() {
		return "NetInfo [listening=" + listening + ", syncing=" + syncing + ", mining=" + mining + ", peerCount="
				+ peerCount + ", currentBlock=" + currentBlock + ", highestBlock=" + highestBlock + ", netWorkID="
				+ netWorkID + ", version=" + version + "]";
	}

}
