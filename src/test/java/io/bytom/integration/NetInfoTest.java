package io.bytom.integration;

import org.junit.Test;

import io.bytom.api.NetInfo;
import io.bytom.exception.BytomException;
import io.bytom.http.Client;

public class NetInfoTest {
	private static Client client;

	@Test
	public void run() {
		try {
			testGetNetInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void testGetNetInfo() throws BytomException {
		client = TestUtils.generateClient();
		try {
			NetInfo netInfo = NetInfo.getNetInfo(client);
			System.out.println("netInfo:" + netInfo);
		} catch (BytomException e) {
			e.printStackTrace();
		}
	}

}
