package com.bytom.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bytom.api.model.AnnotatedInput;
import com.bytom.api.model.AnnotatedOutput;
import com.bytom.exception.BytomException;
import com.bytom.http.Client;
import com.google.gson.annotations.SerializedName;

/**
 * UnconfirmedTransaction  list get
 * @author niyuelin1990
 */
public class UnconfirmedTransaction {
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
    public List<AnnotatedInput> inputs;

    /**
     * List of specified outputs for a transaction.
     */
    public List<AnnotatedOutput> outputs;

    /**
     * list unconfirmed-transactions ids
     *
     * @param client
     * @return
     */
    public static Items list(Client client) throws BytomException {
        Items items = new Items();
        items.setClient(client);
        return items.query();
    }

    /**
     * get-unconfirmed-transaction by txId
     *
     * @param client
     * @param txId
     * @return
     * @throws BytomException
     */
    public static UnconfirmedTransaction get(Client client, String txId) throws BytomException {
        Map<String, Object> req = new HashMap<String, Object>();
        req.put("tx_id", txId);
        return client.request("get-unconfirmed-transaction", req, UnconfirmedTransaction.class);
    }

    public static class Items extends TransactionsResponse<String> {
        public Items query() throws BytomException {
            Items items = this.client.requestList("list-unconfirmed-transactions", null, Items.class);
            return items;
        }
    }

    public static class TransactionsResponse<T> {
        @SerializedName("total")
        public int total;
        @SerializedName("tx_ids")
        public List<T> data;

        protected Client client;

        public void setClient(Client client) {
            this.client = client;
        }
    }

}
