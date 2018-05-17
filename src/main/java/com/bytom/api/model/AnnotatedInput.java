package com.bytom.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * A single input included in a transaction.
 */
public class AnnotatedInput {
    /**
     * The type of the input.<br>
     * Possible values are "issue" and "spend".
     */
    private String type;

    /**
     * The id of the asset being issued or spent.
     */
    @SerializedName("asset_id")
    private String assetId;

    /**
     * The alias of the asset being issued or spent (possibly null).
     */
    @SerializedName("asset_alias")
    private String assetAlias;

    /**
     * The definition of the asset being issued or spent (possibly null).
     */
    @SerializedName("asset_definition")
    private Map<String, Object> assetDefinition;

    /**
     * The number of units of the asset being issued or spent.
     */
    private long amount;
    /**
     * A program specifying a predicate for issuing an asset (possibly null
     * if input is not an issuance).
     */
    @SerializedName("issuance_program")
    private String issuanceProgram;
    /**
     * The control program which must be satisfied to transfer this output.
     */
    @SerializedName("control_program")
    private String controlProgram;

    /**
     * address
     */
    private String address;

    /**
     * The id of the output consumed by this input. Null if the input is an
     * issuance.
     */
    @SerializedName("spent_output_id")
    private String spentOutputId;

    /**
     * The id of the account transferring the asset (possibly null if the
     * input is an issuance or an unspent output is specified).
     */
    @SerializedName("account_id")
    private String accountId;

    /**
     * The alias of the account transferring the asset (possibly null if the
     * input is an issuance or an unspent output is specified).
     */
    @SerializedName("account_alias")
    private String accountAlias;

    private String arbitrary;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getIssuanceProgram() {
        return issuanceProgram;
    }

    public void setIssuanceProgram(String issuanceProgram) {
        this.issuanceProgram = issuanceProgram;
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

    public String getSpentOutputId() {
        return spentOutputId;
    }

    public void setSpentOutputId(String spentOutputId) {
        this.spentOutputId = spentOutputId;
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

    public String getArbitrary() {
        return arbitrary;
    }

    public void setArbitrary(String arbitrary) {
        this.arbitrary = arbitrary;
    }
}
