package com.bytom.integration;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.bytom.api.Account;
import com.bytom.api.Asset;
import com.bytom.api.Key;
import com.bytom.exception.BytomException;
import com.bytom.http.Client;

import static org.junit.Assert.*;

public class AssetTest {

	private static Client client;

	@Test
	public void run() {
		try {
			testAssetCreate();
			testAssetGet();
			testAssetList();
			updateAssetAlias();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testAssetCreate() throws Exception {
		client = TestUtils.generateClient();
		Key key = Key.create(client, "keytest0038", "123456");
		String asset = "AssetTest.testAssetCreate.asset05";
		String test = "AssetTest.testAssetCreate.test05";
		Map<String, Object> def = new HashMap<>();
		def.put("name", asset);
		Asset testAsset = new Asset.Builder().setAlias(asset).addRootXpub(key.xpub)
				.setQuorum(1).setDefinition(def).addDefinitionField("test", test)
				.create(client);
		assertNotNull(testAsset.id, testAsset.issuanceProgram);
		assertNotNull(testAsset.issuanceProgram);
		assertNotNull(testAsset.keys);
		assertEquals(1, testAsset.keys.length);
		assertNotNull(testAsset.keys[0].assetPubkey);
		assertNotNull(testAsset.keys[0].rootXpub);
		assertNotNull(testAsset.keys[0].assetDerivationPath);
		assertEquals(StringUtils.upperCase(asset), testAsset.alias);
		assertEquals(1, testAsset.quorum);
		assertEquals(asset, testAsset.definition.get("name"));
		assertEquals(test, testAsset.definition.get("test"));
	}
	
	public void testAssetGet() throws BytomException {
		client = TestUtils.generateClient();
		Key key = Key.create(client, "keytest0043", "123456");
		String asset = "AssetTest.testAssetCreate.asset10";
		String test = "AssetTest.testAssetCreate.test10";
		Map<String, Object> def = new HashMap<>();
		def.put("name", asset);
		Asset testAsset = new Asset.Builder().setAlias(asset).addRootXpub(key.xpub)
				.setQuorum(1).setDefinition(def).addDefinitionField("test", test)
				.create(client);
		Asset queryAsset = new Asset.QueryBuilder().setAssetId(testAsset.id).get(client);
		assertNotNull(queryAsset.id, testAsset.issuanceProgram);
		assertEquals(StringUtils.upperCase(asset), queryAsset.alias);
		assertNotNull(queryAsset.issuanceProgram);
		assertNotNull(queryAsset.keyIndex);
		assertEquals(1, queryAsset.xpubs.size());
		assertEquals(1, queryAsset.quorum);
		assertEquals(asset, queryAsset.definition.get("name"));
		assertEquals(test, queryAsset.definition.get("test"));
		assertEquals("asset", queryAsset.type);
		assertEquals(1, queryAsset.vm_version);
		assertNotNull(queryAsset.raw_definition_byte);
	}

	public void updateAssetAlias() throws BytomException {
		client = TestUtils.generateClient();
		String assetid = "9332642500887ad148901134308c0a766277808e07888ac5fe05c80f60c0658c";
		String newAlias = "BTM-NEW";
		new Asset.UpdateBuilder().setAssetId(assetid).setAlias(newAlias).update(client);
	}

	public void testAssetList() throws BytomException {
		client = TestUtils.generateClient();
		Asset.Items items = new Asset.QueryBuilder().list(client);
		assertEquals(11, items.data.size());
		assertNotNull(items.data.get(0).alias);
	}

	

	

	public void testListAcounts() throws Exception {
		client = TestUtils.generateClient();
		Account.Items items = new Account.QueryBuilder().list(client);
		assertEquals(1, items.data.size());
		System.out.println(items.data.get(0).alias);

	}

}
