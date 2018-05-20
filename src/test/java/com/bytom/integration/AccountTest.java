package com.bytom.integration;

import org.junit.Test;

import com.bytom.api.Account;
import com.bytom.api.Address;
import com.bytom.api.Key;
import com.bytom.http.Client;

import static org.junit.Assert.*;

public class AccountTest {

	private static Client client;

	@Test
	public void run() {
		try {
			// testCreateAcount();
			// testListAcounts();
			// testDeleteAccount();
			// testCreateAccountReceiver();
			//testListAddresses();
			testValidateAddress();
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
		assertNotNull(items);
		System.out.println(items.data.get(0).alias);
		System.out.println(items.data.get(0).id);
	}

	public void testDeleteAccount() throws Exception {
		client = TestUtils.generateClient();
		new Account().delete(client, "hello_bytom9");
	}

	public void testCreateAccountReceiver() throws Exception {
		client = TestUtils.generateClient();
		new Account.ReceiverBuilder().setAccountId("0DSPTV6T00A08").create(client);
	}

	public void testListAddresses() throws Exception {
		client = TestUtils.generateClient();
		Account.AddressBuilder.Items items = new Account.AddressBuilder().setAccountId(
				"0DSPTV6T00A08").list(client);
		assertNotNull(items.data);
	}

	public void testValidateAddress() throws Exception {
		client = TestUtils.generateClient();
		Address address = new Account.AddressBuilder().validate(client,
				"bm1qg3v9awlqh530fv7aqh230pkf62rpp35lap3unt");
		assertNotNull(address);
	}

}
