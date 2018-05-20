package com.bytom.integration;

import org.junit.Assert;
import org.junit.Test;

import com.bytom.api.Key;
import com.bytom.exception.BytomException;
import com.bytom.http.Client;

public class KeyTest {

	private static Client client;

	@Test
	public void run() throws Exception {
		testCreateKey();
		testListKeys();
		testDeleteKey();
		testResetKeyPwd();
	}

	public void testCreateKey() throws BytomException {
		client = TestUtils.generateClient();
		Key key = Key.create(client, "testkey004", "123456");
		Assert.assertNotEquals(null, key);
	}

	public void testListKeys() throws Exception {
		client = TestUtils.generateClient();
		Key.Items keys = new Key.QueryBuilder().list(client);
		Assert.assertNotNull(keys.data);
		Assert.assertNotNull(keys.data.get(0).alias);
		Assert.assertNotNull(keys.data.get(0).file);
	}

	public void testDeleteKey() throws Exception {
		client = TestUtils.generateClient();
		Key key = Key.create(client, "testdelkey", "123456");
		String password = "123456";
		Key.delete(client, key.xpub, password);
	}

	public void testResetKeyPwd() throws Exception {
		client = TestUtils.generateClient();
		Key key=Key.create(client, "testresetkey1", "123456");
		Key.resetPassword(client, key.xpub, "123456", "111111");
	}
}
