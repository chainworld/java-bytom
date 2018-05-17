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
//			testCreateAcount();
			testListAcounts();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testCreateAcount() throws Exception {
		client = TestUtils.generateClient();
		Account account = new Account();
		Key key = Key.create(client, "keytest0033", "123456");
		if (key != null && key.xpub != null) {
			account = account.setAlias("hello_bytom9").addXpub(key.xpub).setQuorum(1)
					.create(client);
			assertNotNull(account.id);
			assertNotNull(account.alias);
			assertNotNull(account.keyIndex);
			assertEquals(1, account.xpubs.size());
			assertNotNull(account.xpubs.get(0));
		}
	}

	public void testListAcounts() throws Exception {
		client = TestUtils.generateClient();
		Account account = new Account();
		Account.Items accounts = account.list(client);
		assertEquals(1, accounts.data.size());
		System.out.println(accounts.data.get(0).alias);

	}

}
