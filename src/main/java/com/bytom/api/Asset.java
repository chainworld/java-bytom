package com.bytom.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bytom.exception.BytomException;
import com.bytom.http.BytomResponse;
import com.bytom.http.Client;
import com.google.gson.annotations.SerializedName;

/**
 * @author https://github.com/JackyKen
 */
public class Asset {
	/**
	 * Globally unique identifier of the asset.<br>
	 * Asset version 1 specifies the asset id as the hash of:<br>
	 * - the asset version<br>
	 * - the asset's issuance program<br>
	 * - the core's VM version<br>
	 * - the hash of the network's initial block
	 */
	public String id;

	/**
	 * User specified, unique identifier.
	 */
	public String alias;

	/**
	 * A program specifying a predicate to be satisfied when issuing the asset.
	 */
	@SerializedName(value = "issuance_program", alternate = { "issue_program" })
	public String issuanceProgram;

	/**
	 * The list of keys used to create the issuance program for the asset.<br>
	 * Signatures from these keys are required for issuing units of the asset.
	 */
	public Key[] keys;

	@SerializedName("key_index")
	public Integer keyIndex;

	@SerializedName("xpubs")
	public List<String> xpubs;

	/**
	 * The number of keys required to sign an issuance of the asset.
	 */
	public int quorum;

	/**
	 * User-specified, arbitrary/unstructured data visible across blockchain networks.<br>
	 * Version 1 assets specify the definition in their issuance programs, rendering the
	 * definition immutable.
	 */
	public Map<String, Object> definition;

	public int vm_version;

	public String type;

	public String raw_definition_byte;

	public static class Key {
		/**
		 * Hex-encoded representation of the root extended public key
		 */
		@SerializedName("root_xpub")
		public String rootXpub;

		/**
		 * The derived public key, used in the asset's issuance program.
		 */
		@SerializedName("asset_pubkey")
		public String assetPubkey;

		/**
		 * The derivation path of the derived key.
		 */
		@SerializedName("asset_derivation_path")
		public String[] assetDerivationPath;

	}

	public static class Builder {
		/**
		 * User specified, unique identifier.
		 */
		public String alias;

		/**
		 * User-specified, arbitrary/unstructured data visible across blockchain networks.<br>
		 * Version 1 assets specify the definition in their issuance programs, rendering
		 * the definition immutable.
		 */
		public Map<String, Object> definition;

		/**
		 * The list of keys used to create the issuance program for the asset.<br>
		 * Signatures from these keys are required for issuing units of the asset.<br>
		 * <strong>Must set with {@link #addRootXpub(String)} or
		 * {@link #setRootXpubs(List)} before calling {@link #create(Client)}.</strong>
		 */
		@SerializedName("root_xpubs")
		public List<String> rootXpubs;

		/**
		 * The number of keys required to sign an issuance of the asset.<br>
		 * <strong>Must set with {@link #setQuorum(int)} before calling
		 * {@link #create(Client)}.</strong>
		 */
		public int quorum;

		/**
		 * Unique identifier used for request idempotence.
		 */
		@SerializedName("access_token")
		private String access_token;

		/**
		 * Default constructor initializes the list of keys.
		 */
		public Builder() {
			this.rootXpubs = new ArrayList<String>();
		}

		/**
		 * Creates an asset object.
		 * @param client client object that makes request to the core
		 * @return an asset object
		 */
		public Asset create(Client client) throws BytomException {
			return client.request("create-asset", this, Asset.class);
		}

		/**
		 * Sets the alias on the builder object.
		 * @param alias alias
		 * @return updated builder object
		 */
		public Builder setAlias(String alias) {
			this.alias = alias;
			return this;
		}

		/**
		 * Adds a field to the existing definition object (initializing the object if it
		 * doesn't exist).
		 * @param key key of the definition field
		 * @param value value of the definition field
		 * @return updated builder object
		 */
		public Builder addDefinitionField(String key, Object value) {
			if (this.definition == null) {
				this.definition = new HashMap<>();
			}
			this.definition.put(key, value);
			return this;
		}

		/**
		 * Sets the asset definition object.<br>
		 * <strong>Note:</strong> any existing asset definition fields will be replaced.
		 * @param definition asset definition object
		 * @return updated builder object
		 */
		public Builder setDefinition(Map<String, Object> definition) {
			this.definition = definition;
			return this;
		}

		/**
		 * Sets the quorum of the issuance program. <strong>Must be called before
		 * {@link #create(Client)}.</strong>
		 * @param quorum proposed quorum
		 * @return updated builder object
		 */
		public Builder setQuorum(int quorum) {
			this.quorum = quorum;
			return this;
		}

		/**
		 * Adds a key to the builder's list.<br>
		 * <strong>Either this or {@link #setRootXpubs(List)} must be called before
		 * {@link #create(Client)}.</strong>
		 * @param xpub key
		 * @return updated builder object.
		 */
		public Builder addRootXpub(String xpub) {
			this.rootXpubs.add(xpub);
			return this;
		}

		/**
		 * Sets the builder's list of keys.<br>
		 * <strong>Note:</strong> any existing keys will be replaced.<br>
		 * <strong>Either this or {@link #addRootXpub(String)} must be called before
		 * {@link #create(Client)}.</strong>
		 * @param xpubs list of xpubs
		 * @return updated builder object
		 */
		public Builder setRootXpubs(List<String> xpubs) {
			this.rootXpubs = new ArrayList<>(xpubs);
			return this;
		}

	}

	public static class QueryBuilder {
		/**
		 * assetids
		 */
		public String id;

		public QueryBuilder setAssetId(String assetId) {
			this.id = assetId;
			return this;
		}

		/**
		 * 
		 * @param client
		 * @param assetId
		 * @return The Asset Object
		 * @throws BytomException
		 */
		public Asset get(Client client) throws BytomException {
			return client.request("get-asset", this, Asset.class);
		}

		public Items list(Client client) throws BytomException {
			Items items = new Items();
			items.setClient(client);
			return items.query();
		}
	}

	public static class Items extends BytomResponse<Asset> {
		public Items query() throws BytomException {
			Items items = this.client.requestList("list-assets", null, Items.class);
			items.setClient(this.client);
			return items;
		}
	}

	public static class UpdateBuilder {
		/**
		 * id of asset.
		 */
		public String id;
		/**
		 * new alias of asset
		 */
		public String alias;

		public UpdateBuilder setAssetId(String assetId) {
			this.id = assetId;
			return this;
		}

		public UpdateBuilder setAlias(String alias) {
			this.alias = alias;
			return this;
		}

		public void update(Client client) throws BytomException {
			client.request("update-asset-alias", this);
		}

	}

}
