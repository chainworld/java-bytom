package io.bytom.api;

import io.bytom.exception.BytomException;
import io.bytom.http.Client;
import com.google.gson.annotations.SerializedName;

/**
 * Gas gas-rate
 * 
 * @author niyue
 *
 */
public class Gas {
	/**
	 *  gas_rate, gas rate.
	 */
	@SerializedName("gas_rate")
	public long gasRate;

	public static Gas gaRate(Client client) throws BytomException {
		return client.request("gas-rate", null, Gas.class);
	}


}
