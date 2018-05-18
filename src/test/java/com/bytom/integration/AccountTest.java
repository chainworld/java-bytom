package com.bytom.integration;

import org.junit.Test;

import com.bytom.api.Account;
import com.bytom.api.Key;
import com.bytom.http.Client;

import static org.junit.Assert.*;

public class AccountTest {

	private static Client client;

	@Test
	public void run() {
		try {
			// testCreateAcount();
			testListAcounts();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testCreateAcount() throws Exception {
		client = TestUtils.generateClient();

		Key key = Key.create(client, "keytest0033", "123456");
		if (key != null && key.xpub != null) {
			Account account = new Account.Builder().setAlias("hello_bytom9")
					.addXpub(key.xpub).setQuorum(1).create(client);
			assertNotNull(account.id);
			assertNotNull(account.alias);
			assertNotNull(account.keyIndex);
			assertEquals(1, account.xpubs.size());
			assertNotNull(account.xpubs.get(0));
		}
	}

	public void testListAcounts() throws Exception {
		client = TestUtils.generateClient();
		Account.Items items = new Account.QueryBuilder().list(client);
		assertEquals(1, items.data.size());
		System.out.println(items.data.get(0).alias);
	}

}
