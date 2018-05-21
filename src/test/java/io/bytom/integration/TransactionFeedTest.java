package io.bytom.integration;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import io.bytom.api.TransactionFeed;
import io.bytom.exception.BytomException;
import io.bytom.http.Client;

/**
 * TransactionFeedApiTest
 * 
 * @author niyuelin1990
 *
 */
public class TransactionFeedTest {

	private static Client client;

	@Test
	public void run() {
		try {
			String alias = "test1";
			testCreatetransactionFeed(alias);
			testGetTransactionFeed(alias);
			testUpdateTransactionFeed(alias);
			testListTransactionFeed();
			testDeleteTransactionFeed(alias);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testCreatetransactionFeed(String alias) throws Exception {
		client = TestUtils.generateClient();
		try {
			String filter = "asset_id='84778a666fe453cf73b2e8c783dbc9e04bc4fd7cbb4f50caeaee99cf9967ebed' AND amount_lower_limit = 50 AND amount_upper_limit = 100";
			boolean flag = new TransactionFeed.Builder().setAlias(alias).setFilter(filter).create(client);
			Assert.assertEquals(true, flag);
		} catch (BytomException e) {
			e.printStackTrace();
		}
	}

	public void testGetTransactionFeed(String alias) throws Exception {
		client = TestUtils.generateClient();
		try {
			TransactionFeed txfeed = TransactionFeed.get(client, alias);
			System.out.println(txfeed);
			Assert.assertEquals(alias, txfeed.alias);
		} catch (BytomException e) {
			e.printStackTrace();
		}
	}

	public void testUpdateTransactionFeed(String alias) throws Exception {
		client = TestUtils.generateClient();
		try {
			String filter = "asset_id='84778a666fe453cf73b2e8c783dbc9e04bc4fd7cbb4f50caeaee99cf9967ebed' AND amount_lower_limit = 50 AND amount_upper_limit = 80";
			boolean flag = TransactionFeed.update(client, alias, filter);
			Assert.assertEquals(true, flag);
		} catch (BytomException e) {
			e.printStackTrace();
		}
	}

	public void testListTransactionFeed() throws Exception {
		client = TestUtils.generateClient();
		TransactionFeed.Items items = TransactionFeed.list(client);
		assertEquals(1, items.data.size());
		System.out.println(items.data.get(0));
	}

	public void testDeleteTransactionFeed(String alias) throws Exception {
		client = TestUtils.generateClient();
		try {
			boolean flag = TransactionFeed.delete(client, alias);
			Assert.assertEquals(true, flag);
		} catch (BytomException e) {
			e.printStackTrace();
		}
	}

}
