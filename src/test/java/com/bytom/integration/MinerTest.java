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
			testGetWork();
			String block_header = "0101870103f2c7495164c8f3af43697e81faa21dcb2d60aa5e10ce4f233491e62420742fbeadfcd50540bef2670a5fade2e58ad4955e2375a04ad1e4cb9c104faddab43f4a79e35be253c9c377e5192668bc0a367e4a4764f11e7c725ecced1d7b6a492974fab1b6d5bc00ffffff838080808020";
			// testSubmiWork(block_header);
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

	private void testGetWork() throws BytomException {
		client = TestUtils.generateClient();
		try {
			Miner.MinerWork minerWork = Miner.getWork(client);
			System.out.println("minerWork, header:" + minerWork.blockHeader + ", seed:" + minerWork.seed);
		} catch (BytomException e) {
			e.printStackTrace();
		}
	}

	private void testSubmiWork(String block_header) throws BytomException {
		client = TestUtils.generateClient();
		Miner.submiWork(client, block_header);
	}
}
