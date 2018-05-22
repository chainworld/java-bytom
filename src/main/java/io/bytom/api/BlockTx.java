package io.bytom.api;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * BlockTx is the tx struct for getBlock func
 * @author niyue
 *
 */
public class BlockTx {
	 /**
     * Unique identifier, or transaction hash, of a transaction.
     */
    public String id;

    /**
     * version
     */
    public long version;
    /**
     * size
     */
    public long size;
    /**
     * time_range
     */
    @SerializedName("time_range")
    public long timeRange;

    /**
     * status
     */
    @SerializedName("status_fail")
    public boolean statusFail;

    /**
     * List of specified inputs for a transaction.
     */
    public List<Transaction.Input> inputs;

    /**
     * List of specified outputs for a transaction.
     */
    public List<Transaction.Output> outputs;


	@Override
	public String toString() {
		return "BlockTx [id=" + id + ", version=" + version + ", size=" + size + ", timeRange=" + timeRange
				+ ", statusFail=" + statusFail + ", inputs=" + inputs + ", outputs=" + outputs + "]";
	}
    
    
}
