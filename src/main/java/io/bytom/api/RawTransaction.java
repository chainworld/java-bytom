package io.bytom.api;

import io.bytom.exception.BytomException;
import io.bytom.http.Client;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RawTransaction  decode
 *  * @author niyuelin1990
 */
public class RawTransaction {

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
    public long fee;

    /**
     * List of specified inputs for a transaction.
     */
    public List<Transaction.Input> inputs;

    /**
     * List of specified outputs for a transaction.
     */
    public List<Transaction.Output> outputs;

    /**
     * decode-raw-transaction  raw-transaction
     * @param client
     * @param txId
     * @return
     * @throws BytomException
     */
    public static RawTransaction decode(Client client, String txId) throws BytomException {
        Map<String, Object> req = new HashMap<String, Object>();
        req.put("raw_transaction", txId);
        return client.request("decode-raw-transaction", req, RawTransaction.class);
    }
}
