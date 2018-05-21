package io.bytom.integration;

import org.junit.Assert;
import org.junit.Test;

import io.bytom.api.UnconfirmedTransaction;
import io.bytom.exception.BytomException;
import io.bytom.http.Client;

public class UnconfirmedTransactionTest {
	private static Client client;

	@Test
	public void run() {
		try {
			UnconfirmedTransaction.Items items = testUnconfirmedTransactionList();
			if (items.total > 0) {
				testGetUnconfirmedTransaction(items.data.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void testGetUnconfirmedTransaction(String txId) throws BytomException {
		client = TestUtils.generateClient();
		try {
			UnconfirmedTransaction transaction = UnconfirmedTransaction.get(client, txId);
			Assert.assertNotEquals(null, transaction);
		} catch (BytomException e) {
			e.printStackTrace();
		}
	}

	private UnconfirmedTransaction.Items testUnconfirmedTransactionList() throws BytomException {
		client = TestUtils.generateClient();
		UnconfirmedTransaction.Items items = UnconfirmedTransaction.list(client);
		System.out.println(items.total);
		System.out.println(items.data);
		return items;
	}
}
