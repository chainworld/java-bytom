package io.bytom.integration;

import static org.junit.Assert.*;

import org.junit.Test;

import io.bytom.api.UnspentOutput;
import io.bytom.exception.BytomException;
import io.bytom.http.Client;

public class UnspentOutputTest {
	private static Client client;

	@Test
	public void run() {
		try {
			testListUnspentOutputs();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testListUnspentOutputs() throws BytomException {
		client = TestUtils.generateClient();
		UnspentOutput.Items items = new UnspentOutput.QueryBuilder().setId(
				"ffdc597f89349a1a19a74fd0811fd15c8fbbf384ce2bf3d17a718eddf9e79786").list(
				client);
		assertEquals(1, items.data.size());
		assertNotNull(items.data.get(0).accountAlias);

	}

}
