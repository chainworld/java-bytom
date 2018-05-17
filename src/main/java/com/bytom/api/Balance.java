package com.bytom.api;

import java.util.Map;

import com.bytom.exception.BytomException;
import com.bytom.http.BytomResponse;
import com.bytom.http.Client;
import com.google.gson.annotations.SerializedName;

/**
 * @author https://github.com/JackyKen
 */
public class Balance {

	@SerializedName("account_alias")
	public String accountAlias;

	@SerializedName("account_id")
	public String accountId;

	/**
	 * Sum of the unspent outputs.
	 */
	public long amount;

	@SerializedName("asset_alias")
	public String assetAlias;

	@SerializedName("asset_id")
	public String assetId;

	@SerializedName("asset_definition")
	public Map<String, Object> definition;

	public static class QueryBuilder {
		public class Items extends BytomResponse<Balance> {
			public Items query() throws BytomException {
				Items items = this.client.requestList("list-balances", null, Items.class);
				items.setClient(this.client);
				return items;
			}
		}

		public Items list(Client client) throws BytomException {
			Items items = new Items();
			items.setClient(client);
			return items.query();
		}
	}

}
