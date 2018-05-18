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
	}

	public void testCreateKey() throws BytomException {
		client = TestUtils.generateClient();
		try {
			Key key = Key.create(client, "keytest004", "123456");
			Assert.assertNotEquals(null, key);
		}
		catch (BytomException e) {
			e.printStackTrace();
		}
	}

	public void testListKeys() throws Exception {
		client = TestUtils.generateClient();
		try {
			Key.Items keys = new Key.QueryBuilder().list(client);
			Assert.assertEquals(19, keys.data.size());
		}
		catch (BytomException e) {
			e.printStackTrace();
		}
	}

	public void testDeleteKey() throws Exception {
		client = TestUtils.generateClient();
		Key key = new Key();
		try {
			String keytest = "357540568a5159023e2de1962851f65f4e1c027b52d4fea202d206bc535ca78d6376fd46d8a290efd63c3dffee4f9a04f6953f08442d57eae8ca73f356e23676";
			String password = "123456";
			key.delete(client, keytest, password);
		}
		catch (BytomException e) {
			e.printStackTrace();
		}
	}

}
