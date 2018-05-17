package com.bytom.integration;

import static org.junit.Assert.*;
import org.junit.Test;

import com.bytom.api.Account;
import com.bytom.api.Asset;
import com.bytom.api.Key;
import com.bytom.api.Transaction;
import com.bytom.exception.BytomException;
import com.bytom.http.Client;

public class TransactionTest {
	static Client client;

	@Test
	public void run()    {
		
		//testBasicTransaction();
		try {
			testListTransaction(); 
		}
		catch (BytomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * testMultiSigTransaction(); testAtomicSwap(); testReceivers();
		 * testUnspentOutputs();
		 */
	}

	private void testListTransaction() throws BytomException {
		client = TestUtils.generateClient();
		Transaction.Items items= new Transaction.QueryBuilder().list(client);
		assertNotNull(items.data);
	}

	private void testBasicTransaction() throws BytomException {
		client = TestUtils.generateClient();

		Transaction.Template spending = new Transaction.Builder()
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
				spending, "bytom04241521@163.com");

		Transaction.SubmitResponse txs = Transaction.submit(client, singer); 
		
		//txs.tx_id;
		
		
		
		
		
		
		

		/*
		 * Transaction.Items txs = new Transaction.QueryBuilder().setFilter("id=$1")
		 * .addFilterParameter(resp.id).execute(client); Transaction tx = txs.next();
		 * assertEquals(1, txs.list.size()); assertNotNull(tx.inputs); assertEquals(1,
		 * tx.inputs.size()); assertNotNull(tx.outputs); assertEquals(1,
		 * tx.inputs.size());
		 * 
		 * Transaction.Input input = tx.inputs.get(0); assertEquals("issue", input.type);
		 * assertNotNull(input.assetId); assertNotNull(input.assetAlias);
		 * assertNotNull(input.assetDefinition); assertNotNull(input.assetTags);
		 * assertEquals("yes", input.assetIsLocal); assertNull(input.accountId);
		 * assertNull(input.accountAlias); assertNull(input.accountTags);
		 * assertEquals("yes", input.isLocal); assertNull(input.spentOutputId);
		 * assertNotNull(input.issuanceProgram); assertNotNull(input.referenceData);
		 * 
		 * Transaction.Output output = tx.outputs.get(0); assertEquals("control",
		 * output.type); assertEquals("receive", output.purpose);
		 * assertNotNull(output.position); assertNotNull(output.assetId);
		 * assertNotNull(output.assetAlias); assertNotNull(output.assetDefinition);
		 * assertNotNull(output.assetTags); assertEquals("yes", output.assetIsLocal);
		 * assertNotNull(output.accountId); assertNotNull(output.accountAlias);
		 * assertNotNull(output.accountTags); assertNotNull(output.controlProgram);
		 * assertEquals("yes", output.isLocal); assertNotNull(output.referenceData);
		 */

		/*
		 * Transaction.Template spending = new Transaction.Builder() .addAction( new
		 * Transaction.Action.SpendFromAccount().setAccountAlias(alice)
		 * .setAssetAlias(asset).setAmount(10) .addReferenceDataField("test", test))
		 * .addAction( new Transaction.Action.ControlWithAccount().setAccountAlias(bob)
		 * .setAssetAlias(asset).setAmount(10) .addReferenceDataField("test", test))
		 * .addAction( new Transaction.Action.SetTransactionReferenceData()
		 * .addReferenceDataField("test", test)).build(client); resp =
		 * Transaction.submit(client, HsmSigner.sign(spending)); txs = new
		 * Transaction.QueryBuilder().setFilter("id=$1")
		 * .addFilterParameter(resp.id).execute(client); tx = txs.next(); assertEquals(1,
		 * txs.list.size()); assertNotNull(tx.inputs); assertTrue(tx.inputs.size() > 0);
		 * 
		 * input = tx.inputs.get(0); assertNotNull(input.spentOutputId);
		 * 
		 * Transaction.Template retirement = new Transaction.Builder() .addAction( new
		 * Transaction.Action.SpendFromAccount().setAccountAlias(bob)
		 * .setAssetAlias(asset).setAmount(5) .addReferenceDataField("test", test))
		 * .addAction( new Transaction.Action.Retire().setAssetAlias(asset).setAmount(5)
		 * .addReferenceDataField("test", test)) .addAction( new
		 * Transaction.Action.SetTransactionReferenceData() .addReferenceDataField("test",
		 * test)).build(client); Transaction.submit(client, HsmSigner.sign(retirement));
		 * txs = new Transaction.QueryBuilder().setFilter("reference_data.test=$1")
		 * .addFilterParameter(test).execute(client); assertEquals(3, txs.list.size());
		 */

	}
}
