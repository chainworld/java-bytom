package com.bytom.integration;

import com.bytom.api.RawTransaction;
import com.bytom.exception.BytomException;
import com.bytom.http.Client;
import org.junit.Assert;
import org.junit.Test;

public class RawTransactionTest {

    private static Client client;

    @Test
    public void run() {
        try {
            String txId = "test1";
            testDecodeTransaction(txId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void testDecodeTransaction(String txId) throws BytomException {
        client = TestUtils.generateClient();
        try {
            RawTransaction transaction = RawTransaction.decode(client, txId);
            Assert.assertNotEquals(null, transaction);
        } catch (BytomException e) {
            e.printStackTrace();
        }
    }
}
