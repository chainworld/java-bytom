package com.bytom.integration;


import org.junit.Test;

import com.bytom.api.Message;
import com.bytom.exception.BytomException;
import com.bytom.http.Client;

import static org.junit.Assert.*;

public class MessageTest {
	private static Client client;

	@Test
	public void run() {
		try {
			testSignMessage();
		}
		catch (BytomException e) {
			e.printStackTrace();
		}
	}
	
	public void testSignMessage() throws BytomException {
		client = TestUtils.generateClient();
		Message message = new Message.SignBuilder().setAddress("bm1q9jxex8dyh7y4efsrckpqgsmk0jcu9wup684a9y").setMessage("this is a test message")
				.setPassword("bytom04241521@163.com").sign(client);  
		assertNotNull(message.derivedXpub);
		assertNotNull(message.signature); 
	}
}