package com.bytom.integration;

import org.junit.Test;

import com.bytom.api.Block;
import com.bytom.exception.BytomException;
import com.bytom.http.Client;

public class BlockTest {

	private static Client client;

	@Test
	public void run() {
		try {
			testGetBlockCount();
			testGetBlockHash();
			testGetBlock(1);
			testGetBlockHeader(1);
			testGetBlockDifficulty(1);
			testGetBlockHashRate(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	private void testGetBlock(int blockHeight) throws BytomException {
		client = TestUtils.generateClient();
		try {
			Block block = Block.getBlock(client, blockHeight, null);
			System.out.println("Block:" + block);
		} catch (BytomException e) {
			e.printStackTrace();
		}
	}

	private void testGetBlockCount() throws BytomException {
		client = TestUtils.generateClient();
		try {
			int blockCount = Block.getBlockCount(client);
			System.out.println("blockCount:" + blockCount);
		} catch (BytomException e) {
			e.printStackTrace();
		}
	}

	private void testGetBlockHash() throws BytomException {
		client = TestUtils.generateClient();
		try {
			String blockHash = Block.getBlockHash(client);
			System.out.println("blockHash:" + blockHash);
		} catch (BytomException e) {
			e.printStackTrace();
		}
	}

	private void testGetBlockHeader(int blockHeight) throws BytomException {
		client = TestUtils.generateClient();
		try {
			Block.BlockHeader blockHeader = Block.getBlockHeader(client, blockHeight, null);
			System.out.println("blockHeader Header:" + blockHeader.getBlockHeader());
		} catch (BytomException e) {
			e.printStackTrace();
		}
	}

	private void testGetBlockDifficulty(int blockHeight) throws BytomException {
		client = TestUtils.generateClient();
		try {
			Block.BlockDifficulty blockDifficulty = Block.getBlockDifficulty(client, blockHeight, null);
			System.out.println("blockDifficulty Difficulty:" + blockDifficulty.getDifficulty());
		} catch (BytomException e) {
			e.printStackTrace();
		}
	}
	private void testGetBlockHashRate(int blockHeight) throws BytomException{
		client = TestUtils.generateClient();
		try {
			Block.BlockHashRate blockHashRate = Block.getHashRate(client, blockHeight, null);
			System.out.println("blockHashRate HashRate:" + blockHashRate.getHash_rate());
		} catch (BytomException e) {
			e.printStackTrace();
		}
	}
	
}
