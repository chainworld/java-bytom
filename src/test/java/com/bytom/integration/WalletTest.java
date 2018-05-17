package com.bytom.integration;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.bytom.api.Wallet;
import com.bytom.exception.BytomException;
import com.bytom.http.Client;

public class WalletTest {

	private static Client client;

	@Test
	public void run() {
		try {
			//testBackupWallet();
			testRestoreWallet();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testBackupWallet() throws BytomException {
		client = TestUtils.generateClient(); 
		Wallet w = Wallet.backupWallet(client);
		Wallet.restoreWallet(client, w.accountImage, w.assetImage, w.keyImages);
		assertNotNull(w.accountImage);
	}

	public void testRestoreWallet() throws BytomException {
		/*client = TestUtils.generateClient();
		Wallet w = Wallet.backupWallet(client);
		Wallet.restoreWallet(client, w.accountImage, w.assetImage, w.keyImages);*/
	}
    
}
