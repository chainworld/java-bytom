package io.bytom.integration;


import org.junit.Test;

import io.bytom.api.Message;
import io.bytom.exception.BytomException;
import io.bytom.http.Client;

import static org.junit.Assert.*;

public class MessageTest {
	private static Client client;

	@Test
	public void run() {
		try {
			testSignMessage();
			testVerifyMessage();
		}
		catch (BytomException e) {
			e.printStackTrace();
		}
	}
	
	public void testSignMessage() throws BytomException {
		client = TestUtils.generateClient();
		Message message = new Message.SignBuilder().setAddress("bm1q9jxex8dyh7y4efsrckpqgsmk0jcu9wup684a9y").setMessage("this is a test message")
				.setPassword("xxxxx@163.com").sign(client);  
		assertNotNull(message.derivedXpub);
		assertNotNull(message.signature); 
	}
	
	private void testVerifyMessage () throws BytomException {
		client = TestUtils.generateClient();
		try {
			boolean flag =  new Message.VerifyBuilder().verifyMessage(client);  
			System.out.println(flag);
		} catch (BytomException e) {
			e.printStackTrace();
		}
	}
}
