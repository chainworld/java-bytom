package com.bytom.integration;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import com.bytom.api.Token;
import com.bytom.exception.BytomException;
import com.bytom.http.Client;

public class TokenTest {

	private static Client client;

	@Test
	public void run() {
		try {
			String id = "token1";
			String secret = "8fa01fed23de821a5bfc377dc5eb2371ef0c070931478021201d9e0023f0184a";
//			Token t = testCreateToken(id);
//			String token = t.getToken();
//			secret = token.split(":")[1];
			testCheckToken(id, secret);
			
//			testListToken();
			
			testDeleteToken(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testListToken() throws Exception {
		client = TestUtils.generateClient();
		Token.Items items = Token.list(client);
		assertEquals(1, items.data.size());
		System.out.println(items.data.get(0));
	}

	public Token testCreateToken(String id) throws Exception {
		client = TestUtils.generateClient();
		try {
			Token t = Token.create(client, id, "");
			Assert.assertEquals(id, t.getId());
			return t;
		} catch (BytomException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void testCheckToken(String id, String secret) throws BytomException {
		client = TestUtils.generateClient();
		try {
			boolean flag = Token.check(client, id, secret);
			Assert.assertEquals(true, flag);
		} catch (BytomException e) {
			e.printStackTrace();
		}
	}
	
	
	public void testDeleteToken(String id) throws Exception {
		client = TestUtils.generateClient();
		try {
			boolean flag = Token.delete(client, id);
			Assert.assertEquals(true, flag);
		} catch (BytomException e) {
			e.printStackTrace();
		}
	}
	
}
