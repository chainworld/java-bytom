package com.bytom.api;

import com.bytom.exception.BytomException;
import com.bytom.http.Client;
import com.google.gson.annotations.SerializedName;

/**
 * Gas gas-rate
 * 
 * @author niyue
 *
 */
public class Gas {
	@SerializedName("gas_rate")
	private long gasRate;

	public static Gas gaRate(Client client) throws BytomException {
		return client.request("gas-rate", null, Gas.class);
	}

	public long getGasRate() {
		return gasRate;
	}

	public void setGasRate(long gasRate) {
		this.gasRate = gasRate;
	}

}
