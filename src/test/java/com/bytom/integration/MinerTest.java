package com.bytom.integration;

import org.junit.Assert;
import org.junit.Test;

import com.bytom.api.Miner;
import com.bytom.exception.BytomException;
import com.bytom.http.Client;

public class MinerTest {
	private static Client client;

	
	@Test
	public void run() {
		try {
			testSetMining(true);
			testIsMining();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void testIsMining() throws BytomException {
		client = TestUtils.generateClient();
		try {
			boolean flag = Miner.istMining(client);
			System.out.println("isMining:" + flag);
			Assert.assertEquals(true, flag);
		} catch (BytomException e) {
			e.printStackTrace();
		}
	}

	private void testSetMining(boolean isMining) throws BytomException {
		client = TestUtils.generateClient();
		try {
			boolean flag = Miner.setMining(client, isMining);
			System.out.println("setMining:" + flag);
			Assert.assertEquals(true, flag);
		} catch (BytomException e) {
			e.printStackTrace();
		}
	}

}
