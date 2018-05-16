package com.bytom.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * A single output included in a transaction.
 * @author niyuelin1990
 *
 */
public class AnnotatedOutput {
    /**
     * The id of the output.
     */
    @SerializedName("id")
    private String id;

    /**
     * The type the output.<br>
     * Possible values are "control" and "retire".
     */
    private String type;

    /**
     * transaction_id
     */
    @SerializedName("transaction_id")
    private String transactionId;

    /**
     * The output's position in a transaction's list of outputs.
     */
    private int position;

    /**
     * The id of the asset being controlled.
     */
    @SerializedName("asset_id")
    public String assetId;

    /**
     * The alias of the asset being controlled.
     */
    @SerializedName("asset_alias")
    private String assetAlias;

    /**
     * The definition of the asset being controlled (possibly null).
     */
    @SerializedName("asset_definition")
    private Map<String, Object> assetDefinition;

    /**
     * The number of units of the asset being controlled.
     */
    private long amount;

    /**
     * The id of the account controlling this output (possibly null if a
     * control program is specified).
     */
    @SerializedName("account_id")
    private String accountId;

    /**
     * The alias of the account controlling this output (possibly null if a
     * control program is specified).
     */
    @SerializedName("account_alias")
    private String accountAlias;

    /**
     * The control program which must be satisfied to transfer this output.
     */
    @SerializedName("control_program")
    private String controlProgram;

    /**
     * address
     */
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getAssetAlias() {
        return assetAlias;
    }

    public void setAssetAlias(String assetAlias) {
        this.assetAlias = assetAlias;
    }

    public Map<String, Object> getAssetDefinition() {
        return assetDefinition;
    }

    public void setAssetDefinition(Map<String, Object> assetDefinition) {
        this.assetDefinition = assetDefinition;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountAlias() {
        return accountAlias;
    }

    public void setAccountAlias(String accountAlias) {
        this.accountAlias = accountAlias;
    }

    public String getControlProgram() {
        return controlProgram;
    }

    public void setControlProgram(String controlProgram) {
        this.controlProgram = controlProgram;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

	@Override
	public String toString() {
		return "AnnotatedOutput [id=" + id + ", type=" + type + ", transactionId=" + transactionId + ", position="
				+ position + ", assetId=" + assetId + ", assetAlias=" + assetAlias + ", assetDefinition="
				+ assetDefinition + ", amount=" + amount + ", accountId=" + accountId + ", accountAlias=" + accountAlias
				+ ", controlProgram=" + controlProgram + ", address=" + address + "]";
	}
}
