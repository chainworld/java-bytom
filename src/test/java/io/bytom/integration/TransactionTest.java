package io.bytom.integration;

import static org.junit.Assert.*;

import org.junit.Test;

import io.bytom.api.Transaction;
import io.bytom.api.Transaction.TransactionGas;
import io.bytom.exception.BytomException;
import io.bytom.http.Client;

/**
 * @author https://github.com/JackyKen
 */
public class TransactionTest {
	static Client client;

	@Test
	public void run() {

		try {
			//testBasicTransaction();
			testListTransaction();
			//testGetTransaction();
			//TestEstimateGas();
		}
		catch (BytomException e) {
			e.printStackTrace();
		}

	}

	public void TestEstimateGas() throws BytomException {
		client = TestUtils.generateClient();

		Transaction.Template controlAddress = new Transaction.Builder()
				.addAction(
						new Transaction.Action.SpendFromAccount()
								.setAccountId("0D00V8OUG0A02")
								.setAssetId(
										"ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff")
								.setAmount(300000000))
				.addAction(
						new Transaction.Action.ControlWithAddress()
								.setAddress("bm1qu5j0c0mcnzn6sy0um53t847wtkqa0nt785x4nh")
								.setAssetId(
										"ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff")
								.setAmount(200000000)).build(client);	
		TransactionGas gas= Transaction.estimateGas(client, controlAddress);
		assertNotNull(gas.storageNeu);
		assertNotNull(gas.totalNeu);
		assertNotNull(gas.vmNeu);
		
	}

	public void testGetTransaction() throws BytomException {
		client = TestUtils.generateClient();
		Transaction tran = new Transaction.QueryBuilder().setTxId(
				"f4f1cd86b75c74159b32a70f3b6d486fa1ee6d7c3fd654a01b351f38236da32b").get(
				client);

		Transaction.Input input = tran.inputs.get(0);
		Transaction.Output output = tran.outputs.get(0);

		assertNotNull(tran.outputs);
		assertNotNull(tran.inputs);

		assertEquals(1, tran.inputs.size());
		assertEquals(1, tran.outputs.size());
		assertEquals("coinbase", input.type);

		assertNotNull(output.assetId);
		assertNotNull(output.amount);
		assertNotNull(output.accountId);
		assertNotNull(output.accountAlias);

	}

	public void testListTransaction() throws BytomException {
		client = TestUtils.generateClient();
		Transaction.Items items = new Transaction.QueryBuilder().setTxId("b49278a5488c824e93f322657cba7bc58548d577ae380998f733f714c368b50a").list(client);
		assertNotNull(items.data);
	}

	/**
	 * build + sign +submit Transaction
	 * @throws BytomException
	 */
	public void testBasicTransaction() throws BytomException {
		client = TestUtils.generateClient();

		Transaction.Template controlAddress = new Transaction.Builder()
				.addAction(
						new Transaction.Action.SpendFromAccount()
								.setAccountId("0D00V8OUG0A02")
								.setAssetId(
										"ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff")
								.setAmount(300000000))
				.addAction(
						new Transaction.Action.ControlWithAddress()
								.setAddress("bm1qu5j0c0mcnzn6sy0um53t847wtkqa0nt785x4nh")
								.setAssetId(
										"ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff")
								.setAmount(200000000)).build(client);

		Transaction.Template singer = new Transaction.SignerBuilder().sign(client,
				controlAddress, "xxxxx@163.com");

		Transaction.SubmitResponse txs = Transaction.submit(client, singer); 
		assertNotNull(txs.tx_id);

	}
}
