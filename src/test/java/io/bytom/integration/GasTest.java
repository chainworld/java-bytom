package io.bytom.integration;

import org.junit.Test;

import io.bytom.api.Gas;
import io.bytom.exception.BytomException;
import io.bytom.http.Client;

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
			System.out.println(gas.gasRate);
		}
		catch (BytomException e) {
			e.printStackTrace();
		}
	}
}
