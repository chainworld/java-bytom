package com.bytom.api;

import com.bytom.exception.BytomException;
import com.bytom.http.BytomResponse;
import com.bytom.http.Client;
import com.google.gson.annotations.SerializedName;

/**
 * @author https://github.com/JackyKen
 */
public class UnspentOutput {

	/**
	 * The id of the account controlling this output (possibly null if a control program
	 * is specified).
	 */
	@SerializedName("account_id")
	public String accountId;

	/**
	 * The alias of the account controlling this output (possibly null if a control
	 * program is specified).
	 */
	@SerializedName("account_alias")
	public String accountAlias;

	/**
	 * The id of the asset being controlled.
	 */
	@SerializedName("asset_id")
	public String assetId;

	/**
	 * The alias of the asset being controlled.
	 */
	@SerializedName("asset_alias")
	public String assetAlias;

	/**
	 * The number of units of the asset being controlled.
	 */
	public long amount;

	/**
	 * address of account
	 */
	public String address;

	/**
	 * whether the account address is change
	 */
	public Boolean change;

	/**
	 * The ID of the output.
	 */
	@SerializedName("id")
	public String id;

	/**
	 * The control program which must be satisfied to transfer this output.
	 */
	@SerializedName("program")
	public String program;

	@SerializedName("control_program_index")
	public String controlProgramIndex;

	/**
	 * source unspent output id
	 */
	@SerializedName("source_id")
	public String sourceId;

	/**
	 * position of source unspent output id in block
	 */
	@SerializedName("source_pos")
	public int sourcePos;

	/**
	 * The definition of the asset being controlled (possibly null).
	 */
	@SerializedName("valid_height")
	public int validHeight;

	public static class QueryBuilder {

		/**
		 * id of unspent output.
		 */
		public String id;

		public QueryBuilder setId(String id) {
			this.id = id;
			return this;
		}

		public Items list(Client client) throws BytomException {
			Items items = new Items();
			items.setClient(client);
			items.setQuery(this);
			return items.query();
		}

	}

	/**
	 * A paged collection of unspent outputs returned from a query.
	 */
	public static class Items extends BytomResponse<UnspentOutput> {

		QueryBuilder req;

		public Items query() throws BytomException {
			Items items = this.client.requestList("list-unspent-outputs", req,
					Items.class);
			items.setClient(this.client);
			items.setQuery(req);
			return items;
		}

		public void setQuery(QueryBuilder queryBuilder) {
			req = queryBuilder;
		}
	}
}
