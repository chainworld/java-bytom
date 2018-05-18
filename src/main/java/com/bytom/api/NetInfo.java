package com.bytom.api;

import com.bytom.exception.BytomException;
import com.bytom.http.Client;
import com.google.gson.annotations.SerializedName;

/**
 * 网络状态消息
 * @author niyuelin1990
 *
 */
public class NetInfo {

	private boolean listening;

	private boolean syncing;

	private boolean mining;

	@SerializedName("peer_count")
	private int peerCount;

	@SerializedName("current_block")
	private long currentBlock;

	@SerializedName("highest_block")
	private long highestBlock;

	@SerializedName("network_id")
	private String netWorkID;

	@SerializedName("version")
	private String version;

	public static NetInfo getNetInfo(Client client) throws BytomException {
		return client.request("net-info", null, NetInfo.class);
	}

	public boolean isListening() {
		return listening;
	}

	public void setListening(boolean listening) {
		this.listening = listening;
	}

	public boolean isSyncing() {
		return syncing;
	}

	public void setSyncing(boolean syncing) {
		this.syncing = syncing;
	}

	public boolean isMining() {
		return mining;
	}

	public void setMining(boolean mining) {
		this.mining = mining;
	}

	public int getPeerCount() {
		return peerCount;
	}

	public void setPeerCount(int peerCount) {
		this.peerCount = peerCount;
	}

	public long getCurrentBlock() {
		return currentBlock;
	}

	public void setCurrentBlock(long currentBlock) {
		this.currentBlock = currentBlock;
	}

	public long getHighestBlock() {
		return highestBlock;
	}

	public void setHighestBlock(long highestBlock) {
		this.highestBlock = highestBlock;
	}

	public String getNetWorkID() {
		return netWorkID;
	}

	public void setNetWorkID(String netWorkID) {
		this.netWorkID = netWorkID;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "NetInfo [listening=" + listening + ", syncing=" + syncing + ", mining=" + mining + ", peerCount="
				+ peerCount + ", currentBlock=" + currentBlock + ", highestBlock=" + highestBlock + ", netWorkID="
				+ netWorkID + ", version=" + version + "]";
	}

}
