package io.bytom.api.model;

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
    private String id;

    /**
     * version
     */
    private long version;
    /**
     * size
     */
    private long size;
    /**
     * time_range
     */
    @SerializedName("time_range")
    private long timeRange;

    /**
     * status
     */
    @SerializedName("status_fail")
    private boolean statusFail;

    /**
     * List of specified inputs for a transaction.
     */
    private List<AnnotatedInput> inputs;

    /**
     * List of specified outputs for a transaction.
     */
    private List<AnnotatedOutput> outputs;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getTimeRange() {
		return timeRange;
	}

	public void setTimeRange(long timeRange) {
		this.timeRange = timeRange;
	}

	public boolean isStatusFail() {
		return statusFail;
	}

	public void setStatusFail(boolean statusFail) {
		this.statusFail = statusFail;
	}

	public List<AnnotatedInput> getInputs() {
		return inputs;
	}

	public void setInputs(List<AnnotatedInput> inputs) {
		this.inputs = inputs;
	}

	public List<AnnotatedOutput> getOutputs() {
		return outputs;
	}

	public void setOutputs(List<AnnotatedOutput> outputs) {
		this.outputs = outputs;
	}

	@Override
	public String toString() {
		return "BlockTx [id=" + id + ", version=" + version + ", size=" + size + ", timeRange=" + timeRange
				+ ", statusFail=" + statusFail + ", inputs=" + inputs + ", outputs=" + outputs + "]";
	}
    
    
}
