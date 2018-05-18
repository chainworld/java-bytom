package com.bytom.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bytom.exception.BytomException;
import com.bytom.http.Client;
import com.google.gson.annotations.SerializedName;

public class Wallet {

	@SerializedName("account_image")
	public AccountImage accountImage;

	@SerializedName("asset_image")
	public AssetImage assetImage;

	@SerializedName("key_images")
	public KeyImages keyImages;

	public static Wallet backupWallet(Client client) throws BytomException {
		return client.request("backup-wallet", null, Wallet.class);
	}
	
	public static boolean restoreWallet(Client client ,Object accountImage, Object assetImage , Object keyImages) throws BytomException{
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("account_image", accountImage);
		body.put("asset_image", assetImage);
		body.put("key_images", keyImages);
		return client.request("restore-wallet", body);   
	}

	public static class AccountImage {

		public Slices[] slices;

		public static class Slices {

			@SerializedName("contract_index")
			public int contractIndex;

			public Account account;

			public static class Account {

				public String type;

				public List<String> xpubs;

				public int quorum;

				@SerializedName("key_index")
				public int keyIndex;

				public String id;

				public String alias;

			}

		}
	}

	public static class AssetImage {

		public Assets[] assets;

		public static class Assets {
			public String type;
			public List<String> xpubs;
			public int quorum;
			public String id;
			public String alias;
			public Map<String, Object> definition;
			@SerializedName("key_index")
			public int keyIndex;
			@SerializedName("vm_version")
			public int vmVersion;
			@SerializedName("asset_image")
			public String issueProgram;
			@SerializedName("raw_definition_byte")
			public String rawDefinitionByte;
		}
	}

	public static class KeyImages {

		public Xkeys[] xkeys;

		public static class Xkeys {

			public Crypto crypto;
			public String id;
			public String type;
			public int version;
			public String alias;
			public String xpub;

			public static class Crypto {
				public String cipher;
				public String ciphertext;
				public Map<String, Object> cipherparams;
				public String kdf;
				public Map<String, Object> kdfparams;
				public String mac;
			}

		}
	}

}
