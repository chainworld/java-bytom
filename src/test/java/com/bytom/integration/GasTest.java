package com.bytom.integration;

import org.junit.Test;

import com.bytom.api.Gas;
import com.bytom.exception.BytomException;
import com.bytom.http.Client;

public class GasTest {

	private static Client client;

	@Test
	public void run() throws Exception {
		testGasRate();
	}

	public void testGasRate() throws BytomException {
		client = TestUtils.generateClient();
		try {
			Gas gas = Gas.gaRate(client);
			System.out.println(gas.getGasRate());
		}
		catch (BytomException e) {
			e.printStackTrace();
		}
	}
}
