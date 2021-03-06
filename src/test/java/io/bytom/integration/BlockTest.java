package io.bytom.integration;

import org.junit.Test;

import io.bytom.api.Block;
import io.bytom.exception.BytomException;
import io.bytom.http.Client;

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
			Block block = new Block.QueryBuilder().setBlockHeight(blockHeight).getBlock(client);

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
			Block.BlockHeader blockHeader = new Block.QueryBuilder().setBlockHeight(blockHeight).getBlockHeader(client);
			System.out.println("blockHeader Header:" + blockHeader.blockHeader);
		} catch (BytomException e) {
			e.printStackTrace();
		}
	}

	private void testGetBlockDifficulty(int blockHeight) throws BytomException {
		client = TestUtils.generateClient();
		try {
			Block.BlockDifficulty blockDifficulty = new Block.QueryBuilder().setBlockHeight(blockHeight)
					.getBlockDifficulty(client);
			System.out.println("blockDifficulty Difficulty:" + blockDifficulty.difficulty);
		} catch (BytomException e) {
			e.printStackTrace();
		}
	}

	private void testGetBlockHashRate(int blockHeight) throws BytomException {
		client = TestUtils.generateClient();
		try {
			Block.BlockHashRate blockHashRate = new Block.QueryBuilder().setBlockHeight(blockHeight)
					.getHashRate(client);

			System.out.println("blockHashRate HashRate:" + blockHashRate.hash_rate);
		} catch (BytomException e) {
			e.printStackTrace();
		}
	}

}
