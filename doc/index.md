# SDK documentation

This page will document the API classes and ways to properly use the API.
Subsequent new releases also maintain backward compatibility with this class approach.
Welcome please submit the issue in Github directly.
[Bytom-JAVA-SDK Issues](https://github.com/chainworld/java-bytom/issues)


## Basic Usage

```
   public static Client generateClient() throws BytomException {
		String coreURL = Configuration.getValue("bytom.api.url");
		String accessToken = Configuration.getValue("client.access.token");
		if (coreURL == null || coreURL.isEmpty()) {
			coreURL = "http://127.0.0.1:9888/";
		}
		return new Client(coreURL, accessToken);
	}
	
    Client client = generateClient();

```

## API methods

### available with wallet enable

* [`create-key`](#create-key)
* [`list-keys`](#list-keys)
* [`delete-key`](#delete-key)
* [`reset-key-password`](#reset-key-password)
* [`create-account`](#create-account)
* [`list-accounts`](#list-accounts)
* [`delete-account`](#delete-account)
* [`create-account-receiver`](#create-account-receiver)
* [`list-addresses`](#list-addresses)
* [`validate-address`](#validate-address)
* [`create-asset`](#create-asset)
* [`get-asset`](#get-asset)
* [`list-assets`](#list-assets)
* [`update-asset-alias`](#update-asset-alias)
* [`list-balances`](#list-balances)
* [`list-unspent-outputs`](#list-unspent-outputs)
* [`build-transaction`](#build-transaction)
* [`sign-transaction`](#sign-transaction)
* [`submit-transaction`](#submit-transaction)
* [`estimate-transaction-gas`](#estimate-transaction-gas)
* [`get-transaction`](#get-transaction)
* [`list-transactions`](#list-transactions)
* [`backup-wallet`](#backup-wallet)
* [`restore-wallet`](#restore-wallet)
* [`sign-message`](#sign-message)

### available Whether or not the wallet is open

* [`create-access-token`](#create-access-token)
* [`list-access-tokens`](#list-access-tokens)
* [`delete-access-token`](#delete-access-token)
* [`check-access-token`](#check-access-token)
* [`create-transaction-feed`](#create-transaction-feed)
* [`get-transaction-feed`](#get-transaction-feed)
* [`list-transaction-feeds`](#list-transaction-feeds)
* [`delete-transaction-feed`](#delete-transaction-feed)
* [`update-transaction-feed`](#update-transaction-feed)
* [`get-unconfirmed-transaction`](#get-unconfirmed-transaction)
* [`list-unconfirmed-transactions`](#list-unconfirmed-transactions)
* [`decode-raw-transaction`](#decode-raw-transaction)
* [`get-block-count`](#get-block-count)
* [`get-block-hash`](#get-block-hash)
* [`get-block`](#get-block)
* [`get-block-header`](#get-block-header)
* [`get-difficulty`](#get-difficulty)
* [`get-hash-rate`](#get-hash-rate)
* [`net-info`](#net-info)
* [`is-mining`](#is-mining)
* [`set-mining`](#set-mining)
* [`gas-rate`](#gas-rate)
* [`verify-message`](#verify-message)
* [`get-work`](#get-work)
* [`submit-work`](#submit-work)

----

#### `create-key`

##### Parameters

`Object`:

- `String` - *alias*, name of the key.
- `String` - *password*, password of the key.

##### Returns

`Object`:

- `String` - *alias*, name of the key.
- `String` - *xpub*, pubkey of the key.
- `String` - *file*, path to the file of key.

##### Example

```java
// Request
Key key = Key.create(client, "testkey004", "123456");

// Result
{
  "alias": "alice",
  "xpub": "a7dae957c2d35b42efe7e6871cf5a75ebd2a0d0e51caffe767db42d3e6d69dbe211d1ca492ecf05908fe6fa625ad61b3253375ea744c9442dd5551613ba50aea",
  "file": "/Path/To/Library/Bytom/keystore/UTC--2018-04-22T06-30-27.609315219Z--0e34293c-8856-4f5f-b934-37456a3820fa"
}
```

----

#### `list-keys`

##### Parameters

none

##### Returns

- `Array of Object`, keys owned by the client.
  - `Object`:
    - `String` - *alias*, name of the key.
    - `String` - *xpub*, pubkey of the key.

##### Example

```java
// Request
Key.Items keys = new Key.QueryBuilder().list(client);

// Result
[
  {
    "alias": "alice",
    "xpub": "a7dae957c2d35b42efe7e6871cf5a75ebd2a0d0e51caffe767db42d3e6d69dbe211d1ca492ecf05908fe6fa625ad61b3253375ea744c9442dd5551613ba50aea",
    "file": "/Path/To/Library/Bytom/keystore/UTC--2018-04-21T02-35-15.035935116Z--4f2b8bd7-0576-4b82-8941-6cc6da05efe3"
  },
  {
    "alias": "bob",
    "xpub": "d30a810e88532f73816b7b5007d413cbd21e526ae9159023e5262511893adc1526b8eacd691b27c080201d7d79336a4f3d2cb4c167d997821cad445765916254",
    "file": "/Path/To/Library/Bytom/keystore/UTC--2018-04-22T06-30-27.609315219Z--0e34293c-8856-4f5f-b934-37456a3820fa"
  }
]
```

----

#### `delete-key`

##### Parameters

`Object`:

- `String` - *xpub*, pubkey of the key.
- `String` - *password*, password of the key.

##### Returns

 `Boolen` - *flag*, success return true.

##### Example

```java
// Request
Key key = Key.create(client, "testdelkey", "123456");

// Result
true
```

----

#### `reset-key-password`

##### Parameters

`Object`:

- `String` - *xpub*, pubkey of the key.
- `String` - *old_password*, old password of the key.
- `String` - *new_password*, new password of the key.

##### Returns

 `Boolen` - *flag*, success return true.

##### Example

```java
// Request
Key.resetPassword(client, key.xpub, "123456", "111111");

// Result
true
```

----

#### `create-account`

##### Parameters

`Object`:

- `Array of String` - *root_xpubs*, pubkey array.
- `String` - *alias*, name of the account.
- `Integer` - *quorum*, the default value is `1`, threshold of keys that must sign a transaction to spend asset units controlled by the account.

Optional:

- `String` - *access_token*, if optional when creating account locally. However, if you want to create account remotely, it's indispensable.

##### Returns

`Object`:

- `String` - *id*, account id.
- `String` - *alias*, name of account.
- `Integer` - *key_index*, key index of account.
- `Integer` - *quorom*, threshold of keys that must sign a transaction to spend asset units controlled by the account.
- `Array of Object` - *xpubs*, pubkey array.

##### Example

```java
// Request
Key key = Key.create(client, "keytest0033", "123456");
if (key != null && key.xpub != null) {
	Account account = new Account.Builder()
	                             .setAlias("hello_bytom9") 
				     .addXpub(key.xpub)
				     .setQuorum(1)
				     .create(client);
}

// Result
{
  "alias": "alice",
  "id": "08FO663C00A02",
  "key_index": 1,
  "quorum": 1,
  "xpubs": [
    "02581f1a2099e1696c498901c0049a22cc3e7f85db71c4ebb78f238d3ef8b323d2fd5c33b6f634aacdd25eb5e09c0c803077c521ef0524e4cc64d1a4420c8bc6"
  ]
}
```

----

#### `list-accounts`

##### Parameters

none

##### Returns

- `Array of Object`, account array.
  - `Object`:
    - `String` - *id*, account id.
    - `String` - *alias*, name of account.
    - `Integer` - *key_index*, key index of account.
    - `Integer` - *quorom*, threshold of keys that must sign a transaction to spend asset units controlled by the account.
    - `Array of Object` - *xpubs*, pubkey array.

##### Example

list all the available accounts:

```java
// Request
Account.Items items = new Account.QueryBuilder().list(client);

// Result
[
  {
    "alias": "alice",
    "id": "086KQD75G0A02",
    "key_index": 1,
    "quorum": 1,
    "xpubs": [
      "180aab8bf247932a7cf68da5cc9a873266279155097612f1e5fdda4add88d5e91e2e7ce5b736f3ac933824cdee9effcf1531b90dfcb388e5cc306d14e9a2c85e"
    ]
  },
  {
    "alias": "bob",
    "id": "086KQO67G0A04",
    "key_index": 2,
    "quorum": 1,
    "xpubs": [
      "180aab8bf247932a7cf68da5cc9a873266279155097612f1e5fdda4add88d5e91e2e7ce5b736f3ac933824cdee9effcf1531b90dfcb388e5cc306d14e9a2c85e"
    ]
  }
]
```

----

#### `delete-account`

##### Parameters

`Object`:

- `String` - *account_info*, alias or ID of account.

##### Returns

 `Boolen` - *flag*, success return true.

##### Example

```java
// Request
new Account().delete(client, "hello_bytom9");

// Result
true
```

----

#### `create-account-receiver`

##### Parameters

`Object`: account_alias | account_id

optional:

- `String` - *account_alias*, alias of account.
- `String` - *account_id*, id of account.

##### Returns

`Object`:

- `String` - *address*, address of account.
- `String` - *control_program*, control program of account.

##### Example

//Request
```java
new Account.ReceiverBuilder()
	   .setAccountId("0DSPTV6T00A08")
	   .create(client);
```
// Result
```json
{
    "address": "bm1q5u8u4eldhjf3lvnkmyl78jj8a75neuryzlknk0",
    "control_program": "0014a70fcae7edbc931fb276d93fe3ca47efa93cf064"
}
```

----

#### `list-addresses`

##### Parameters

- `String` - *account_alias*, alias of account.
- `String` - *account_id*, id of account.

##### Returns

- `Array of Object`, account address array.
  - `Object`:
    - `String` - *account_alias*, alias of account.
    - `String` - *account_id*, id of account.
    - `String` - *address*, address of account.
    - `Boolean` - *change*, whether the account address is change.

##### Example

list all the addresses:

```java
// Request
Account.AddressBuilder.Items items = new Account.AddressBuilder()
                                                .setAccountId("0DSPTV6T00A08")
				                .list(client);

// Result
[
  {
    "account_alias": "alice",
    "account_id": "0DSPTV6T00A08",
    "address": "bm1qcn9lf7nxhswratvmg6d78nq7r7yupm36qgsv55",
    "change": false
  },
  {
    "account_alias": "bob",
    "account_id": "0DSPTV6T00A08",
    "address": "bm1qfkf3tyc95m0axv2mfnjsnu7l0h5tyfnpgpud4lsz5m0xkqmhgy9sd4x24h",
    "change": false
  }
]
```

list all the addresses by account_id or account_alias:

```java
// Request
Account.AddressBuilder.Items items = new Account.AddressBuilder()
                                                .setAccountId("0DSPTV6T00A08")
						.list(client);

// Result
[
  {
    "account_alias": "alice",
    "account_id": "0DSPTV6T00A08",
    "address": "bm1qcn9lf7nxhswratvmg6d78nq7r7yupm36qgsv55",
    "change": false
  },
  {
    "account_alias": "alice",
    "account_id": "0DSPTV6T00A08",
    "address": "bm1qew4h5uvt5ssrtg2alms0j77r94c30m78ucrcxy",
    "change": false
  },
  {
    "account_alias": "alice",
    "account_id": "0DSPTV6T00A08",
    "address": "bm1qgnp4lte7wge0rsekevjlrdh39vkzz0c2alheue",
    "change": false
  }
]
```

----

#### `validate-address`

##### Parameters

`Object`:

- `string` - *address*, address of account.

##### Returns

`Object`:

- `Boolean` - *valid*, whether the account address is valid.
- `Boolean` - *is_local*, whether the account address is local.

##### Example

check whether the address is vaild or not.

```js
// Request
Address address = new Account.AddressBuilder()
                             .validate(client, "bm1qg3v9awlqh530fv7aqh230pkf62rpp35lap3unt");

// Result
{
   "valid": true,
   "is_local": true,
}
```

----

#### `create-asset`

##### Parameters

`Object`:

- `Array of String` - *root_xpubs*, pubkey array.
- `String` - *alias*, name of the asset.
- `Integer` - *quorum*, the default value is `1`, threshold of keys that must sign a transaction to spend asset units controlled by the account.

Optional:

- `Object` - *definition*, definition of asset.
- `String` - *access_token*, if optional when creating asset locally. However, if you want to create asset remotely, it's indispensable.

##### Returns

`Object`:

- `String` - *id*, asset id.
- `String` - *alias*, name of the asset.
- `String` - *issuance_program*, control program of the issuance of asset.
- `Array of Object` - *keys*, information of asset pubkey.
- `String` - *definition*, definition of asset.
- `Integer` - *quorum*, threshold of keys that must sign a transaction to spend asset units controlled by the account.

##### Example

create asset.

```java
// Request
Asset testAsset = new Asset.Builder()
                           .setAlias(asset)
			   .addRootXpub(key.xpub)
			   .setQuorum(1)
			   .setDefinition(def)
			   .addDefinitionField("test", test)
			   .create(client);

// Result
{
  "id": "3c1cf4c9436e3f942cb2f1d70a584f1c61df3697698dacccdc89e46f46a003d0",
  "alias": "GOLD",
  "issuance_program": "766baa209683b893483c0a5a317bf9868a8e2a09691f8aa8c1f3e2a7bb62b157e76712e05151ad696c00c0",
  "keys": [
    {
      "root_xpub": "f6a16704f745a168642712060e6c5a69866147e21ec2447ae628f87d756bb68cc9b91405ad0a95f004090e864fde472f62ba97053ea109837bc89d63a64040d5",
      "asset_pubkey": "9683b893483c0a5a317bf9868a8e2a09691f8aa8c1f3e2a7bb62b157e76712e012bd443fa7d56a0627df0a29dffcdc52641672a0f5cba54d104ad76ebeb8dfc3",
      "asset_derivation_path": [
        "000200000000000000"
      ]
    }
  ],
  "quorum": 1,
  "definition": {}
}
```

----

#### `get-asset`

##### Parameters

`Object`:

- `String` - *id*, id of asset.

##### Returns

`Object`:

- `String` - *id*, asset id.
- `String` - *alias*, name of the asset.
- `String` - *issuance_program*, control program of the issuance of asset.
- `Integer` - *key_index*, index of key for xpub.
- `Integer` - *quorum*, threshold of keys that must sign a transaction to spend asset units controlled by the account.
- `Array of Object` - *xpubs*, pubkey array.
- `String` - *type*, type of asset.
- `Integer` - *vm_version*, version of VM.
- `String` - *raw_definition_byte*, byte of asset definition.
- `Object` - *definition*, description of asset.

##### Example

get asset by assetID.

```java
// Request
Asset queryAsset = new Asset.QueryBuilder()
                            .setAssetId(testAsset.id)
			    .get(client);

// Result
{
  "alias": "SILVER",
  "definition": null,
  "id": "50ec80b6bc48073f6aa8fa045131a71213c33f3681203b15ddc2e4b81f1f4730",
  "issue_program": "ae2029cd61d9ef31d40af7541f9a50831d6317fdb0870249d0564fcfa9a8f843589c5151ad",
  "key_index": 1,
  "quorum": 1,
  "raw_definition_byte": "",
  "type": "asset",
  "vm_version": 1,
  "xpubs": [
    "34b16ee500615cd325f8b84099f83c1ebecaca67977c5dc9b71ae32ceaf18207f996b0a9725b901d3792689b2babcb60febe3b81a684d9b56b65f67f307d453d"
  ]
}
```

----

#### `list-assets`

##### Parameters

none

##### Returns

- `Array of Object`, asset array.
  - `Object`:
    - `String` - *id*, asset id.
    - `String` - *alias*, name of the asset.
    - `String` - *issuance_program*, control program of the issuance of asset.
    - `Integer` - *key_index*, index of key for xpub.
    - `Integer` - *quorum*, threshold of keys that must sign a transaction to spend asset units controlled by the account.
    - `Array of Object` - *xpubs*, pubkey array.
    - `String` - *type*, type of asset.
    - `Integer` - *vm_version*, version of VM.
    - `String` - *raw_definition_byte*, byte of asset definition.
    - `Object` - *definition*, description of asset.

##### Example

list all the available assets:

```java
// Request
Asset.Items items = new Asset.QueryBuilder().list(client);

// Result
[
  {
    "alias": "BTM",
    "definition": {
      "decimals": 8,
      "description": "Bytom Official Issue",
      "name": "BTM",
      "symbol": "BTM"
    },
    "id": "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
    "issue_program": "",
    "key_index": 0,
    "quorum": 0,
    "raw_definition_byte": "7b0a202022646563696d616c73223a20382c0a2020226465736372697074696f6e223a20224279746f6d204f6666696369616c204973737565222c0a2020226e616d65223a202262746d222c0a20202273796d626f6c223a202262746d220a7d",
    "type": "internal",
    "vm_version": 1,
    "xpubs": null
  },
  {
    "alias": "SILVER",
    "definition": null,
    "id": "50ec80b6bc48073f6aa8fa045131a71213c33f3681203b15ddc2e4b81f1f4730",
    "issue_program": "ae2029cd61d9ef31d40af7541f9a50831d6317fdb0870249d0564fcfa9a8f843589c5151ad",
    "key_index": 1,
    "quorum": 1,
    "raw_definition_byte": "",
    "type": "asset",
    "vm_version": 1,
    "xpubs": [
      "34b16ee500615cd325f8b84099f83c1ebecaca67977c5dc9b71ae32ceaf18207f996b0a9725b901d3792689b2babcb60febe3b81a684d9b56b65f67f307d453d"
    ]
  }
]
```

----

#### `update-asset-alias`

##### Parameters

`Object`:

- `String` - *id*, id of asset.
- `String` - *alias*, new alias of asset.

##### Returns

none if the asset alias is updated success.

##### Example

update asset alias.

```java
// Request
new Asset.UpdateBuilder()
         .setAssetId(assetid)
         .setAlias(newAlias)
	 .update(client);

// Result
```

----

#### `list-balances`

##### Parameters

none

##### Returns

- `Array of Object`, balances owned by the account.
  - `Object`:
    - `String` - *account_id*, account id.
    - `String` - *account_alias*, name of account.
    - `String` - *asset_id*, asset id.
    - `String` - *asset_alias*, name of asset.
    - `Integer` - *amount*, specified asset balance of account.

##### Example

list all the available account balances.

```java
// Request
Balance.Items items = new Balance.QueryBuilder().list(client);

// Result
[
    {
      "account_alias": "default",
      "account_id": "0BDQ9AP100A02",
      "amount": 35508000000000,
      "asset_alias": "BTM",
      "asset_id": "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"
    },
    {
      "account_alias": "alice",
      "account_id": "0BDQARM800A04",
      "amount": 60000000000,
      "asset_alias": "BTM",
      "asset_id": "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"
    }
]
```

----

#### `list-unspent-outputs`

##### Parameters

`Object`:

optional:

- `String` - *id*, id of unspent output.

##### Returns

- `Array of Object`, unspent output array.
  - `Object`:
    - `String` - *account_id*, account id.
    - `String` - *account_alias*, name of account.
    - `String` - *asset_id*, asset id.
    - `String` - *asset_alias*, name of asset.
    - `Integer` - *amount*, specified asset balance of account.
    - `String` - *address*, address of account.
    - `Boolean` - *change*, whether the account address is change.
    - `String` - *id*, unspent output id.
    - `String` - *program*, program of account.
    - `String` - *control_program_index*, index of program.
    - `String` - *source_id*, source unspent output id.
    - `String` - *source_pos*, position of source unspent output id in block.
    - `String` - *valid_height*, valid height.

##### Example

list all the available unspent outputs:

```java
// Request
UnspentOutput.Items items = new UnspentOutput.QueryBuilder()
                                             .setId("ffdc597f89349a1a19a74fd0811fd15c8fbbf384ce2bf3d17a718eddf9e79786")
					     .list(client);

// Result
[
  {
    "account_alias": "alice",
    "account_id": "0BKBR6VR00A06",
    "address": "bm1qv3htuvug7qdv46ywcvvzytrwrsyg0swltfa0dm",
    "amount": 2000,
    "asset_alias": "GOLD",
    "asset_id": "1883cce6aab82cf9af8cd085a3115dd4a92cdb8e6a9152acd73d7ae4adb9030a",
    "change": false,
    "control_program_index": 2,
    "id": "58f29f0f85f7bd2a91088bcbe536dee41cd0642dfb1480d3a88589bdbfd642d9",
    "program": "0014646ebe3388f01acae88ec318222c6e1c0887c1df",
    "source_id": "5988c1630c1f325e69bb92cb4b19af14286aa107311bc64b8f1a54629a33e0f4",
    "source_pos": 2,
    "valid_height": 0
  },
  {
    "account_alias": "default",
    "account_id": "0BKBR2D2G0A02",
    "address": "bm1qx7ylnhszg24995d5e0nftu9e87kt9vnxcn633r",
    "amount": 624000000000,
    "asset_alias": "BTM",
    "asset_id": "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
    "change": false,
    "control_program_index": 12,
    "id": "5af9d3c9b69470983377c1fc0c9125c4ac3bfd32c8d505f2a6042aade8503bc9",
    "program": "00143789f9de0242aa52d1b4cbe695f0b93facb2b266",
    "source_id": "233d1dd49e591980f98e11f333c6c28a867e78448e272011f045131df5aa260b",
    "source_pos": 0,
    "valid_height": 12
  }
]
```

----

#### `build-transaction`

##### Parameters

`Object`:

- `String` - *base_transaction*, base data for the transaction.
- `Integer` - *ttl*, integer of the time to live in seconds.
- `Arrary of Object` - *actions*:
  - `Object`:
    - `String` - *account_id* | *account_alias*, alias or ID of account.
    - `String` - *asset_id* | *asset_alias*, alias or ID of asset.
    - `Integer` - *amount*, the specified asset of the amount sent with this transaction.
    - `String`- *type*, type of transaction, valid types: 'issue', 'spend', 'address'.
    - `String` - *address*, (type is address) address of receiver.
    - `String` - *receiver*, (type is spend) program of receiver.

##### Returns

- `Object of build-transaction` - *transaction*, builded transaction.

##### Example

- `control_address` - transaction type is control_address
```js
//request
 client = TestUtils.generateClient();

		Transaction.Template controlAddress = new Transaction.Builder()
				.addAction(
						new Transaction.Action.SpendFromAccount()
								.setAccountId("0D00V8OUG0A02")
								.setAssetId(
										"ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff")
								.setAmount(300000000))
				.addAction(
						new Transaction.Action.ControlWithAddress()
								.setAddress("bm1qu5j0c0mcnzn6sy0um53t847wtkqa0nt785x4nh")
								.setAssetId(
										"ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff")
								.setAmount(200000000)).build(client);
```


```js
// Result(this type is address, the other types are similar.)
{
	"raw_transaction" : "070100010161015f7feb07b6e782f3f62c4fbade202fd069299737fc345904faa8dcca0813d8464affffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8099c4d5990100011600142c8d931da4bf895ca603c5820443767cb1c2bb81010002013effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff80d3bdc69801011600143bee3919e645af291efdd914e496aef72af76e8700013cffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8084af5f01160014e524fc3f7898a7a811fcdd22b3d7ce5d81d7cd7e00",
	"signing_instructions" : [ {
		"position" : 0,
		"witness_components" : [
				{
					"type" : "raw_tx_signature",
					"quorum" : 1,
					"keys" : [ {
						"xpub" : "2f3e45ed187bbc4f666f33f58a618af43c8154935312625ea4719734b202a3a20e6e38f266e0e70bff70786d53564e15a5418f51cc990e360425c8657e12c013",
						"derivation_path" : [ "010100000000000000",
								"0100000000000000" ]
					} ],
					"signatures" : null
				},
				{
					"type" : "data",
					"value" : "3f1e3abbf0837b4c52a9caf990b87c3cb2ea97d918970e62fca4453e13da75c0"
				} ]
	} ],
	"allow_additional_actions" : false
}}
```

----

#### `sign-transaction`

##### Parameters

`Object`:

- `String` - *password*, signature of the password.
- `Object` - *transaction*, builded transaction.

##### Returns

`Object`:

- `Boolean` - *sign_complete*, returns true if sign succesfully and false otherwise.
- `Object of sign-transaction` - *transaction*, signed transaction.

##### Example

```js
// Request 
Transaction.Template controlAddress = new Transaction.Builder()
				.addAction(
						new Transaction.Action.SpendFromAccount()
								.setAccountId("0D00V8OUG0A02")
								.setAssetId(
										"ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff")
								.setAmount(300000000))
				.addAction(
						new Transaction.Action.ControlWithAddress()
								.setAddress("bm1qu5j0c0mcnzn6sy0um53t847wtkqa0nt785x4nh")
								.setAssetId(
										"ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff")
								.setAmount(200000000)).build(client);

		Transaction.Template singer = new Transaction.SignerBuilder().sign(client,
				controlAddress, "xxxxxx");

```
```js
// Result(this type is address, the other types are similar.)
{
		"transaction" : {
			"raw_transaction" : "070100010161015f7feb07b6e782f3f62c4fbade202fd069299737fc345904faa8dcca0813d8464affffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8099c4d5990100011600142c8d931da4bf895ca603c5820443767cb1c2bb81630240fc2a00960fad45fd28490a3a45679a6362ee4bd53a9da1d2edf3a354e7269cea32f2e05d1ff62fb14030187a058043dbc57d7a7bafccaa21847dd0ca4d2f980c203f1e3abbf0837b4c52a9caf990b87c3cb2ea97d918970e62fca4453e13da75c002013effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff80d3bdc698010116001402176f5cda77ea8c1a5a833a58f7a7755560cbe900013cffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8084af5f01160014e524fc3f7898a7a811fcdd22b3d7ce5d81d7cd7e00",
			"signing_instructions" : [ {
				"position" : 0,
				"witness_components" : [
						{
							"type" : "raw_tx_signature",
							"quorum" : 1,
							"keys" : [ {
								"xpub" : "2f3e45ed187bbc4f666f33f58a618af43c8154935312625ea4719734b202a3a20e6e38f266e0e70bff70786d53564e15a5418f51cc990e360425c8657e12c013",
								"derivation_path" : [ "010100000000000000",
										"0100000000000000" ]
							} ],
							"signatures" : [ "fc2a00960fad45fd28490a3a45679a6362ee4bd53a9da1d2edf3a354e7269cea32f2e05d1ff62fb14030187a058043dbc57d7a7bafccaa21847dd0ca4d2f980c" ]
						},
						{
							"type" : "data",
							"value" : "3f1e3abbf0837b4c52a9caf990b87c3cb2ea97d918970e62fca4453e13da75c0"
						} ]
			} ],
			"allow_additional_actions" : false
		},
		"sign_complete" : true
	}


```
----

#### `submit-transaction`

##### Parameters

`Object`:

- `Object` - *raw_transaction*, raw_transaction of signed transaction.

##### Returns

`Object`:

- `String` - *tx_id*, transaction id, hash of transaction.

##### Example

```js
// Request

Transaction.Template controlAddress = new Transaction.Builder()
				.addAction(
						new Transaction.Action.SpendFromAccount()
								.setAccountId("0D00V8OUG0A02")
								.setAssetId(
										"ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff")
								.setAmount(300000000))
				.addAction(
						new Transaction.Action.ControlWithAddress()
								.setAddress("bm1qu5j0c0mcnzn6sy0um53t847wtkqa0nt785x4nh")
								.setAssetId(
										"ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff")
								.setAmount(200000000)).build(client);

		Transaction.Template singer = new Transaction.SignerBuilder().sign(client,
				controlAddress, "xxxxx");

		Transaction.SubmitResponse txs = Transaction.submit(client, singer); 

```

```js
// Result
{"tx_id":"cb78aedfdad26cf7aad67f4e5776b916634daac3b66908523b78856bde6c2e6d"}
```

----

#### `estimate-transaction-gas`

##### Parameters

`Object`:

- `Object` - *transaction_template*, builded transaction response.

##### Returns

`Object`:

- `Integer` - *total_neu*, total consumed neu(1BTM = 10^8NEU) for execute transaction.
- `Integer` - *storage_neu*, consumed neu for storage transaction .
- `Integer` - *vm_neu*, consumed neu for execute VM.

##### Example

```js
// Request
Transaction.Template controlAddress = new Transaction.Builder()
				.addAction(
						new Transaction.Action.SpendFromAccount()
								.setAccountId("0D00V8OUG0A02")
								.setAssetId(
										"ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff")
								.setAmount(300000000))
				.addAction(
						new Transaction.Action.ControlWithAddress()
								.setAddress("bm1qu5j0c0mcnzn6sy0um53t847wtkqa0nt785x4nh")
								.setAssetId(
										"ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff")
								.setAmount(200000000)).build(client);	
		TransactionGas gas= Transaction.estimateGas(client, controlAddress);
```
```js
// Result
{
		"total_neu" : 437400,
		"storage_neu" : 153600,
		"vm_neu" : 283800
}
```

----

#### `get-transaction`

##### Parameters

`Object`:

- `String` - *tx_id*, transaction id, hash of transaction.

##### Returns

`Object`:

- `Integer` - *block_height*, block height where this transaction was in.
- `String` - *block_hash*, hash of the block where this transaction was in.
- `String` - *block_transactions_count*, transaction count where this transaction was in the block.
- `String` - *tx_id*, transaction id, hash of the transaction.
- `Integer` - *block_index*, position of the transaction in the block.
- `Boolean` - *status_fail*, whether the state of the request has failed.
- `String` - *block_time*, the unix timestamp for when the requst was responsed.
- `Integer` - *size*, size of transaction.
- `Array of Object` - *inputs*, object of inputs for the transaction.
  - `String` - *type*, the type of input action, available option include: 'spend', 'issue', 'coinbase'.
  - `String` - *asset_id*, asset id.
  - `String` - *asset_alias*, name of asset.
  - `Object` - *asset_definition*, definition of asset(json object).
  - `Integer` - *amount*, amount of asset.
  - `String` - *issuance_program*, issuance program, it only exist when type is 'issue'.
  - `String` - *control_program*, control program, it only exist when type is 'spend'.
  - `String` - *address*, address of account.
  - `String` - *spent_output_id*, the front of SpentOutputID, it only exist when type is 'spend'.
  - `String` - *account_id*, account id.
  - `String` - *account_alias*, name of account.
  - `String` - *arbitrary*, arbitrary infomation can be set by miner, it only exist when type is 'coinbase'.
- `Array of Object` - *outputs*, object of outputs for the transaction.
  - `String` - *type*, the type of output action, available option include: 'retire', 'control'.
  - `String` - *id*, outputid related to utxo.
  - `String` - *position*, position of outputs.
  - `String` - *asset_id*, asset id.
  - `String` - *asset_alias*, name of asset.
  - `Object` - *asset_definition*, definition of asset(json object).
  - `Integer` - *amount*, amount of asset.
  - `String` - *account_id*, account id.
  - `String` - *account_alias*, name of account.
  - `String` - *control_program*, control program of account.
  - `String` - *address*, address of account.

##### Example

```js
// Request
Transaction tran = new Transaction.QueryBuilder()
                                .setTxId("f4f1cd86b75c74159b32a70f3b6d486fa1ee6d7c3fd654a01b351f38236da32b")
				.get(client);

		Transaction.Input input = tran.inputs.get(0);
		Transaction.Output output = tran.outputs.get(0);
```
```js
// Result
{
		"tx_id" : "f4f1cd86b75c74159b32a70f3b6d486fa1ee6d7c3fd654a01b351f38236da32b",
		"block_time" : 1524555187,
		"block_hash" : "a4ab32b3c6daaaec825852653de5d50b249019f226e1755737633fe49e7c1d18",
		"block_height" : 39,
		"block_index" : 0,
		"block_transactions_count" : 1,
		"inputs" : [ {
			"type" : "coinbase",
			"asset_id" : "0000000000000000000000000000000000000000000000000000000000000000",
			"asset_definition" : {},
			"amount" : 0,
			"arbitrary" : "27"
		} ],
		"outputs" : [ {
			"type" : "control",
			"id" : "f8e79f35e7604896eaca878ef4aeb509a041ff7e2859b105a00240ed52dd1025",
			"position" : 0,
			"asset_id" : "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
			"asset_definition" : {},
			"amount" : 41250000000,
			"account_id" : "0D00V8OUG0A02",
			"account_alias" : "bytommaster",
			"control_program" : "00142c8d931da4bf895ca603c5820443767cb1c2bb81",
			"address" : "bm1q9jxex8dyh7y4efsrckpqgsmk0jcu9wup684a9y"
		} ],
		"status_fail" : false
	}
```

----

#### `list-transactions`

##### Parameters

`Object`:

optional:

- `String` - *tx_id*, transaction id, hash of transaction.
- `String` - *account_id*, id of account.
- `Bool`   - *detail* , flag of required transactions data ,default false (only return transaction summary) 

##### Returns

`Array of Object`, transaction array.

optional:

  - `Object`:(summary transaction)
    - `String` - *tx_id*, transaction id, hash of the transaction.
    - `String` - *block_time*, the unix timestamp for when the requst was responsed.
    - `Array of Object` - *inputs*, object of inputs for the transaction.
    - `Array of Object` - *outputs*, object of outputs for the transaction.

  - `Object`:(detail transaction)
    - `Integer` - *block_height*, block height where this transaction was in.
    - `String` - *block_hash*, hash of the block where this transaction was in.
    - `String` - *block_transactions_count*, transaction count where this transaction was in the block.
    - `String` - *tx_id*, transaction id, hash of the transaction.
    - `Integer` - *block_index*, position of the transaction in the block.
    - `Boolean` - *status_fail*, whether the state of the request has failed.
    - `String` - *block_time*, the unix timestamp for when the requst was responsed.
    - `Integer` - *size*, size of transaction.
    - `Array of Object` - *inputs*, object of inputs for the transaction.
    - `Array of Object` - *outputs*, object of outputs for the transaction.

##### Example

list all the available transactions:

```js
// Request
Transaction.Items items = new Transaction.QueryBuilder().list(client);
```
```js
// Result
[
  {
    "block_time": 1521771059,
    "inputs": [
      {
        "arbitrary": "c4a4",
        "asset_id": "0000000000000000000000000000000000000000000000000000000000000000",
        "type": "coinbase"
      }
    ],
    "outputs": [
      {
        "account_alias": "default",
        "account_id": "0BMHBOBVG0A02",
        "amount": 624000000000,
        "asset_alias": "btm",
        "asset_id": "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
        "type": "control"
      }
    ],
    "tx_id": "c631a8de401913a512c338bcf4a61cb2de6cede12a7385d9d11637eaa6578f33"
  },
  {
    "block_time": 1521770515,
    "inputs": [
      {
        "account_alias": "default",
        "account_id": "0BMHBOBVG0A02",
        "amount": 624000000000,
        "asset_alias": "btm",
        "asset_id": "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
        "type": "spend"
      }
    ],
    "outputs": [
      {
        "account_alias": "default",
        "account_id": "0BMHBOBVG0A02",
        "amount": 563980000000,
        "asset_alias": "btm",
        "asset_id": "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
        "type": "control"
      },
      {
        "account_alias": "alice",
        "account_id": "0BMHDI1P00A04",
        "amount": 60000000000,
        "asset_alias": "btm",
        "asset_id": "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
        "type": "control"
      }
    ],
    "tx_id": "1151ce5c7b32b8755b5e48109ec7ed956fb1783eaea9558bf5a2ad957825e4b7"
  }
]
```

#### `backup-wallet`

##### Parameters

none

##### Returns

`Object`:

- `Object` - *account_image*, account image.
- `Object` - *asset_image*, asset image.
- `Object` - *key_images*, key image.

##### Example

```java
// Request
Wallet w = Wallet.backupWallet(client);

// Result
{
  "account_image": {
    "slices": [
      {
        "account": {
          "type": "account",
          "xpubs": [
            "395d6e0ac25978c3f52f9c7bdfdf75ce6af02639fd7875b4b1f40778ab1120c6dcf461b7ab6fd310983afb54a9a0fb3e09b6ec0d4364c4808c94383d50fb0681"
          ],
          "quorum": 1,
          "key_index": 1,
          "ID": "0CQTA3EOG0A02",
          "Alias": "def"
        },
        "contract_index": 2
      }
    ]
  },
  "asset_image": {
    "assets": []
  },
  "key_images": {
    "xkeys": [
      {
        "crypto": {
          "cipher": "aes-128-ctr",
          "ciphertext": "bf44766fec149478af9500e25ce0a6bc50bb2fa04e40465781da6ff64e9b3a4c9af3d214cd92c5a41d8498db5f4376526740f960ff429b16e52876aec6860e1d",
          "cipherparams": {
            "iv": "1b0fc61ae4dacb15f0f77d2b4ba67635"
          },
          "kdf": "scrypt",
          "kdfparams": {
            "dklen": 32,
            "n": 4096,
            "p": 6,
            "r": 8,
            "salt": "e133b1e7caae771ff1ab34b14824d6e27ef399f2b7ded4ad3500f080ede4a1dd"
          },
          "mac": "bc6bf411fb63e61a17bc15b94f29cf0d5a0f084c328955da1f7e2b26757cfc23"
        },
        "id": "1f40be59-7400-4fdc-b46b-15009f65363a",
        "type": "bytom_kd",
        "version": 1,
        "alias": "default",
        "xpub": "c4ec9bfd5df19d175e17ff7fed89193c37a4a64e1c0928387da01387ca76c3bfd99390e3373ec4d438522cc2d4644214cd2ec3b00965f7a1fa3546809583191c"
      },
      {
        "crypto": {
          "cipher": "aes-128-ctr",
          "ciphertext": "f0887c8603cbbafc0a66d5b45f71488e089708c7dea4342625a67858a49d6d08c79cd3f1800627e3c8b4668e8df34fcf0be9df5d9d4503acff05373976c312a9",
          "cipherparams": {
            "iv": "c111b46f9104f49f2c40aedb827e53b5"
          },
          "kdf": "scrypt",
          "kdfparams": {
            "dklen": 32,
            "n": 4096,
            "p": 6,
            "r": 8,
            "salt": "d9ef588b258b111dea1d99a4e4c5a4f968ab69072176bb95b111922e3bbea9e6"
          },
          "mac": "336f5fee643776e139f05ebe5e4f209d992ff97e16b906105fadac9e86133554"
        },
        "id": "611d407c-9e97-4297-a02a-13cd68e47983",
        "type": "bytom_kd",
        "version": 1,
        "alias": "def",
        "xpub": "395d6e0ac25978c3f52f9c7bdfdf75ce6af02639fd7875b4b1f40778ab1120c6dcf461b7ab6fd310983afb54a9a0fb3e09b6ec0d4364c4808c94383d50fb0681"
      }
    ]
  }
}
```

----

#### `restore-wallet`

##### Parameters

`Object`:

- `Object` - *account_image*, account image.
- `Object` - *asset_image*, asset image.
- `Object` - *key_images*, key image.

##### Returns

none if restore wallet success.

##### Example

```java
// Request
Wallet w = Wallet.backupWallet(client);
Wallet.restoreWallet(client, w.accountImage, w.assetImage, w.keyImages);

// Result
```

----

#### `sign-message`

##### Parameters

`Object`:

- `String` - *address*, address for account.
- `String` - *message*, message for signature by address xpub.
- `String` - *password*, password of account.

##### Returns

`Object`:

- `String` - *derived_xpub*, derived xpub.
- `String` - *signature*, signature of message.

##### Example

```java
// Request
Message message = new Message.SignBuilder()
                             .setAddress("bm1q9jxex8dyh7y4efsrckpqgsmk0jcu9wup684a9y")
			     .setMessage("this is a test message")
			     .setPassword("xxxxx@pwd")
			     .sign(client);  

// Result
{
  "signature": "74da3d6572233736e3a439166719244dab57dd0047f8751b1efa2da26eeab251d915c1211dcad77e8b013267b86d96e91ae67ff0be520ef4ec326e911410b609",
  "derived_xpub": "6ff8c3d1321ce39a3c3550f57ba70b67dcbcef821e9b85f6150edb7f2f3f91009e67f3075e6e76ed5f657ee4b1a5f4749b7a8c74c8e7e6a1b0e5918ebd5df4d0"
}
```

----

#### `create-access-token`

##### Parameters

`Object`:

- `String` - *id*, token ID.

optional:

- `String` - *type*, type of token.

##### Returns

`Object`:

- `String` - *token*, access token.
- `String` - *id*, token ID.
- `String` - *type*, type of token.
- `Object` - *created_at*, time to create token.

##### Example

create access token.

```java
// Request
Token t = new Token.Builder().setId(id).create(client);

// Result
{
  "token": "token1:1fee70f537128a201338bd5f25a3adbf33dad02eae4f4c9ac43f336a069df8f3",
  "id": "token1",
  "created_at": "2018-03-20T18:56:01.043919771+08:00"
}
```

----

#### `list-access-tokens`

##### Parameters

none

##### Returns

- `Array of Object`, access token array.
  - `Object`:
    - `String` - *token*, access token.
    - `String` - *id*, token ID.
    - `String` - *type*, type of token.
    - `Object` - *created_at*, time to create token.

##### Example

list all the available access tokens.

```java
// Request
Token.Items items = Token.list(client);

// Result
[
  {
    "token": "token1:1fee70f537128a201338bd5f25a3adbf33dad02eae4f4c9ac43f336a069df8f3",
    "id": "token1",
    "created_at": "2018-03-20T18:56:01.043919771+08:00"
  },
  {
    "token": "alice:78598c6d9fb9e3258d01f78005d4e5725ad0d45e20af90a30b577b407d4a2edd",
    "id": "alice",
    "created_at": "2018-03-20T18:56:01.043919771+08:00"
  }
]
```

----

#### `delete-access-token`

##### Parameters

`Object`:

- `String` - *id*, token ID.

##### Returns

 `Boolen` - *flag*, success return true.

##### Example

delete access token.

```java
// Request
boolean flag = Token.delete(client, id);

// Result
true
```

----

#### `check-access-token`

##### Parameters

`Object`:

- `String` - *id*, token ID.
- `String` - *secret*, secret of token, the second part of the colon division for token.

##### Returns

 `Boolen` - *flag*, success return true.

##### Example

check whether the access token is vaild or not.

```java
// Request
boolean flag = Token.check(client, id, secret);

// Result
true
```

----

#### `create-transaction-feed`

##### Parameters

`Object`:

- `String` - *alias*, name of the transaction feed.
- `String` - *filter*, filter of the transaction feed.

##### Returns

`Boolen` - *flag*, success return true.

##### Example

```java
// Request
boolean flag = new TransactionFeed.Builder().setAlias("test1")
                                            .setFilter("asset_id='84778a666fe453cf73b2e8c783dbc9e04bc4fd7cbb4f50caeaee99cf9967ebed' AND amount_lower_limit = 50 AND amount_upper_limit = 100")
					    .create(client);

// Result
true
```

----

#### `get-transaction-feed`

##### Parameters

`Object`:

- `String` - *alias*, name of the transaction feed.

##### Returns

`Object`:

- `String` - *id*, id of the transaction feed.
- `String` - *alias*, name of the transaction feed.
- `String` - *filter*, filter of the transaction feed.
- `Object` - *param*, param of the transaction feed.
  - `String` - *assetid*, asset id.
  - `Integer` - *lowerlimit*, the lower limit of asset amount.
  - `Integer` - *upperlimit*, the upper limit of asset amount.
  - `String` - *transtype*, type of transaction.

##### Example

list the available txfeed by alias:

```java
// Request
TransactionFeed txfeed = TransactionFeed.get(client, "test1");

// Result
{
  "alias": "test1",
  "filter": "asset_id='84778a666fe453cf73b2e8c783dbc9e04bc4fd7cbb4f50caeaee99cf9967ebed' AND amount_lower_limit = 50 AND amount_upper_limit = 100",
  "param": {}
}
```

----
#### `list-transaction-feeds`

##### Parameters

none

##### Returns

- `Array of Object`, the transaction feeds.
  - `Object`:
    - `String` - *id*, id of the transaction feed.
    - `String` - *alias*, name of the transaction feed.
    - `String` - *filter*, filter of the transaction feed.
    - `Object` - *param*, param of the transaction feed.
      - `String` - *assetid*, asset id.
      - `Integer` - *lowerlimit*, the lower limit of asset amount.
      - `Integer` - *upperlimit*, the upper limit of asset amount.
      - `String` - *transtype*, type of transaction.

##### Example

list all the available txfeed:

```java
// Request
TransactionFeed.Items items = TransactionFeed.list(client);

// Result
[
  {
    "alias": "test1",
    "filter": "asset_id='84778a666fe453cf73b2e8c783dbc9e04bc4fd7cbb4f50caeaee99cf9967ebed' AND amount_lower_limit = 50 AND amount_upper_limit = 100",
    "param": {}
  },
  {
    "alias": "test2",
    "filter": "asset_id='cee6a588cc3fc280749021ef42d5209952a1e6feceada4e69dd8a424ad22b199' AND amount_lower_limit = 30 AND amount_upper_limit = 100",
    "param": {}
  }
]
```

----

#### `delete-transaction-feed`

##### Parameters

`Object`:

- `String` - *alias*, name of the transaction feed.

##### Returns

`Boolen` - *flag*, success return true.

##### Example

```java
// Request
boolean flag = TransactionFeed.delete(client, alias);

// Result
true
```

----

#### `update-transaction-feed`

##### Parameters

`Object`:

- `String` - *alias*, name of the transaction feed.
- `String` - *filter*, filter of the transaction feed.

##### Returns

`Boolen` - *flag*, success return true.

##### Example

deleted when the txfeed exists, and create it with alias and filter:

```java
// Request
String filter = "asset_id='84778a666fe453cf73b2e8c783dbc9e04bc4fd7cbb4f50caeaee99cf9967ebed' AND amount_lower_limit = 50 AND amount_upper_limit = 80";
boolean flag = TransactionFeed.update(client, "test1", filter);

// Result
true
```

----

#### `get-unconfirmed-transaction`

##### Parameters

`Object`:

- `String` - *tx_id*, transaction id, hash of transaction.

##### Returns

`Object`:

- `String` - *id*, transaction id, hash of the transaction.
- `Integer` - *version*, version of transaction.
- `Integer` - *size*, size of transaction.
- `Integer` - *time_range*, the time range of transaction.
- `Boolean` - *status_fail*, whether the state of the request has failed.
- `Array of Object` - *inputs*, object of inputs for the transaction.
- `Array of Object` - *outputs*, object of outputs for the transaction.

##### Example

```java
// Request
UnconfirmedTransaction transaction = UnconfirmedTransaction.get(client, txId);

// Result
{
  "id": "382090f24fbfc2f737fa7372b9d161a43f00d1c597a7130a56589d1f469d04b5",
  "inputs": [
    {
      "address": "bm1qqrm7ruecx7yrg9smtwmnmgj3umg9vcukgy5sdj",
      "amount": 41250000000,
      "asset_definition": {},
      "asset_id": "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
      "control_program": "001400f7e1f338378834161b5bb73da251e6d0566396",
      "spent_output_id": "161b44e547a6cc68d732eb64fa38031da98211a99319e088cfe632223f9ac6d8",
      "type": "spend"
    }
  ],
  "outputs": [
    {
      "address": "bm1qehxd5cdnepckh5jc72ggn30havd78lsgcqmt7k",
      "amount": 21230000000,
      "asset_definition": {},
      "asset_id": "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
      "control_program": "0014cdccda61b3c8716bd258f29089c5f7eb1be3fe08",
      "id": "a8f21ad24689c290634db85278f56d152efe6fe08bc194e5dee5127ed6d3ebee",
      "position": 0,
      "type": "control"
    },
    {
      "address": "bm1q2me9gwccnm3ehpnrcr99gcnj730js2zfucms3r",
      "amount": 20000000000,
      "asset_definition": {},
      "asset_id": "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
      "control_program": "001456f2543b189ee39b8663c0ca546272f45f282849",
      "id": "78219e422ea3257aeb32f6d952b5ce5560dab1d6440c9f3aebcdaad2a852d2a8",
      "position": 1,
      "type": "control"
    }
  ],
  "size": 664,
  "status_fail": false,
  "time_range": 0,
  "version": 1
}
```

----

#### `list-unconfirmed-transactions`

##### Parameters

none

##### Returns

`Object`:

- `Integer` - *total*, version of transaction.
- `Array of Object` - *tx_ids*, list of transaction id.

##### Example

```java
// Request
UnconfirmedTransaction.Items items = UnconfirmedTransaction.list(client);

// Result
{
  "total": 2,
  "tx_ids": [
    "382090f24fbfc2f737fa7372b9d161a43f00d1c597a7130a56589d1f469d04b5",
    "fc2da5dfa094c2170144f149fa07a312983157aec0ec95063a1319eedcb2d23b"
  ]
}
```

----

#### `decode-raw-transaction`

##### Parameters

`Object`:

- `String` - *raw_transaction*, hexstring of raw transaction.

##### Returns

`Object`:

- `Integer` - *version*, version of transaction.
- `String` - *size*, size of transaction.
- `String` - *time_range*, time range of transaction.
- `String` - *fee*, fee for sending transaction.
- `Array of Object` - *inputs*, object of inputs for the transaction.
- `Array of Object` - *outputs*, object of outputs for the transaction.

##### Example

```java
// Request
 RawTransaction transaction = RawTransaction.decode(client, txId);

// Result
{
  "version": 1,
  "size": 332,
  "time_range": 0,
  "fee": 20000000,
  "inputs": [
    {
      "address": "tm1q26kpwrrevhh2c8xrfy5vnaryu0ugc97c4yxp66",
      "amount": 41250000000,
      "asset_definition": {},
      "asset_id": "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
      "control_program": "001456ac170c7965eeac1cc34928c9f464e3f88c17d8",
      "spent_output_id": "01bb3309666618a1507cb5be845b17dee5eb8028ee7e71b17d74b4dc97085bc8",
      "type": "spend"
    }
  ],
  "outputs": [
    {
      "address": "tm1qc0fjpcwuflnc06038s2xfcl2t2hfdfv06pfd70",
      "amount": 41030000000,
      "asset_definition": {},
      "asset_id": "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
      "control_program": "0014c3d320e1dc4fe787e9f13c1464e3ea5aae96a58f",
      "id": "567b34857614d16292220beaca16ce34b939c75023a49cc43fa432fff51ca0dd",
      "position": 0,
      "type": "control"
    },
    {
      "address": "tm1qhwfumd8v5a9sdqepa6uy43wnx6rzsxm9uhmk4q",
      "amount": 200000000,
      "asset_definition": {},
      "asset_id": "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
      "control_program": "0014bb93cdb4eca74b068321eeb84ac5d33686281b65",
      "id": "a8069d412e48c2b2994d2816758078cff46b215421706b4bad41f72a32928d92",
      "position": 1,
      "type": "control"
    }
  ]
}
```

----

#### `get-block-count`

##### Parameters

none

##### Returns

`Object`:

- `Integer` - *block_count*, recent block height of the blockchain.

##### Example

```java
// Request
int blockCount = Block.getBlockCount(client);

// Result
{
    "block_count": 519
}
```

----

#### `get-block-hash`

##### Parameters

none

##### Returns

`Object`:

- `String` - *block_hash*, recent block hash of the blockchain.

##### Example

```java
// Request
String blockHash = Block.getBlockHash(client);

// Result
{
  "block_hash": "997bf5cecb4df097991a7a121a7fd3cb5a404fa856e3d6032c791ac07bc7c74c"
}
```

----

#### `get-block`

##### Parameters

`Object`: block_height | block_hash

optional:

- `String` - *block_hash*, hash of block.
- `Integer` - *block_height*, height of block.

##### Returns

`Object`:

- `String` - *hash*, hash of block.
- `Integer` - *size*, size of block.
- `Integer` - *version*, version of block.
- `Integer` - *height*, height of block.
- `String` - *previous_block_hash*, previous block hash.
- `Integer` - *timestamp*, timestamp of block.
- `Integer` - *nonce*, nonce value.
- `Integer` - *bits*, bits of difficulty.
- `String` - *difficulty*, difficulty value(String type).
- `String` - *transaction_merkle_root*, merkle root of transaction.
- `String` - *transaction_status_hash*, merkle root of transaction status.
- `Array of Object` - *transactions*, transaction object:
  - `String` - *id*, transaction id, hash of the transaction.
  - `String` - *version*, version of transaction.
  - `String` - *size*, size of transaction.
  - `String` - *time_range*, the unix timestamp for when the requst was responsed. 
  - `String` - *status_fail*, whether the state of the request has failed.
  - `Array of Object` - *inputs*, object of inputs for the transaction.
    - `String` - *type*, the type of input action, available option include: 'spend', 'issue', 'coinbase'.
    - `String` - *asset_id*, asset id.
    - `String` - *asset_alias*, name of asset.
    - `Object` - *asset_definition*, definition of asset(json object).
    - `Integer` - *amount*, amount of asset.
    - `String` - *issuance_program*, issuance program, it only exist when type is 'issue'.
    - `String` - *control_program*, control program, it only exist when type is 'spend'.
    - `String` - *address*, address of account.
    - `String` - *spent_output_id*, the front of SpentOutputID, it only exist when type is 'spend'.
    - `String` - *account_id*, account id.
    - `String` - *account_alias*, name of account.
    - `String` - *arbitrary*, arbitrary infomation can be set by miner, it only exist when type is 'coinbase'.
  - `Array of Object` - *outputs*, object of outputs for the transaction.
    - `String` - *type*, the type of output action, available option include: 'retire', 'control'.
    - `String` - *id*, outputid related to utxo.
    - `String` - *position*, position of outputs.
    - `String` - *asset_id*, asset id.
    - `String` - *asset_alias*, name of asset.
    - `Object` - *asset_definition*, definition of asset(json object).
    - `Integer` - *amount*, amount of asset.
    - `String` - *account_id*, account id.
    - `String` - *account_alias*, name of account.
    - `String` - *control_program*, control program of account.
    - `String` - *address*, address of account.

##### Example

get specified block information by block_hash or block_height, if both exists, the block result is querying by hash.

```java
// Request
Block block = new Block.QueryBuilder()
                       .setBlockHeight(blockHeight)
		       .getBlock(client);

// Result
{
  "bits": 2305843009222082600,
  "difficulty": "57896037716911750921221705069588091649609539881711309849342236841432341020672",
  "hash": "886a8e85b275e7d65b569ba510875c0e63dece1a94569914d7624c0dac8002f9",
  "height": 43,
  "nonce": 3,
  "previous_block_hash": "2c72ccbd53b92a4f9423c5b610b4e106bbe8fbf3ecf2e16a1266b17ee323ff9d",
  "size": 386,
  "timestamp": 1521614189,
  "transaction_merkle_root": "77d40262cfeca3a16d4587132974552ef00951e43ce567a26801ebc3dbdb4d96",
  "transaction_status_hash": "53c0ab896cb7a3778cc1d35a271264d991792b7c44f5c334116bb0786dbc5635",
  "transactions": [
    {
      "id": "4576b1b1ec251da3263dbdd5486bcbf9a1cd1f712172dbe7a7a5fe46ab194629",
      "inputs": [
        {
          "amount": 0,
          "arbitrary": "09",
          "asset_definition": "7b7d",
          "asset_id": "0000000000000000000000000000000000000000000000000000000000000000",
          "type": "coinbase"
        }
      ],
      "outputs": [
        {
          "amount": 624000000000,
          "asset_definition": "7b7d",
          "asset_id": "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
          "control_program": "0014f3403bcd8b443d03882a280b50f6f98986e1a255",
          "id": "da87b40854a9b93be72ecdc24fe7bb03986ea3530e344b0f918f0788c3d83717",
          "position": 0,
          "type": "control"
        }
      ],
      "size": 77,
      "status_fail": false,
      "time_range": 0,
      "version": 1
    }
  ],
  "version": 1
}
```

----

#### `get-block-header`

##### Parameters

`Object`: block_height | block_hash

optional:

- `String` - *block_hash*, hash of block.
- `Integer` - *block_height*, height of block.

##### Returns

`Object`:

- `Object` - *block_header*, header of block.
- `Integer` - *reward*, reward.

##### Example

```java
// Request
Block.BlockHeader blockHeader = new Block.QueryBuilder()
                                         .setBlockHeight(blockHeight)
					 .getBlockHeader(client);
			

// Result
{
  "block_header": "01019601e87da37e7d73f31d54304c719c9058ec7bc7de7819deda89a7c8834a99bc05b8fbdbe6d60540eba9e5d5cb79fd87b3c0fad32f6772c1e4483f2a070e093a6176d85226d986a8c9c377e5192668bc0a367e4a4764f11e7c725ecced1d7b6a492974fab1b6d5bc00ad918480808080801e",
  "reward": 41250000000
}
```

----

#### `get-difficulty`

##### Parameters

`Object`:

optional:

- `String` - *block_hash*, hash of block.
- `Integer` - *block_height*, height of block.

##### Returns

`Object`:

- `Integer` - *bits*, bits of block.
- `String` - *difficulty*, difficulty of block.
- `String` - *hash*, block hash.
- `Integer` - *height*, block height.

##### Example

Get difficulty for current block or specified block hash / height.

```java
// Request
Block.BlockDifficulty blockDifficulty = new Block.QueryBuilder()
                                                 .setBlockHeight(blockHeight)
					         .getBlockDifficulty(client);

// Result
{
  "bits": 2161727821137910500,
  "difficulty": "15154807",
  "hash": "d1fce60caea5466eae2b812e4586b55120c52aca27b6c92bd7c51e9cda82dcdf",
  "height": 506
}
```

----

#### `get-hash-rate`

##### Parameters

`Object`:

optional:

- `String` - *block_hash*, hash of block.
- `Integer` - *block_height*, height of block.

##### Returns

`Object`:

- `Integer` - *hash_rate*, difficulty of block.
- `String` - *hash*, block hash.
- `Integer` - *height*, block height.

##### Example

Get hash rate for current block or specified block hash / height.

```java
// Request
Block.BlockHashRate blockHashRate = new Block.QueryBuilder().setBlockHeight(blockHeight)
					.getHashRate(client);

// Result
{
  "hash": "d1fce60caea5466eae2b812e4586b55120c52aca27b6c92bd7c51e9cda82dcdf",
  "hash_rate": 7577403,
  "height": 506
}
```

----

#### `net-info`

##### Parameters

none

##### Returns

`Object`:

- `Boolean` - *listening*, whether the node is listening.
- `Boolean` - *syncing*, whether the node is syncing.
- `Boolean` - *mining*, whether the node is mining.
- `Integer` - *peer_count*, current count of connected peers.
- `Integer` - *current_block*, current block height in the node's blockchain.
- `Integer` - *highest_block*, current highest block of the connected peers.
- `String` - *network_id*, network id.
- `String` - *version*, bytom version.

##### Example

```java
// Request
NetInfo netInfo = NetInfo.getNetInfo(client);

// Result
{
  "listening": true,
  "syncing": true,
  "mining": true,
  "peer_count": 0,
  "current_block": 627,
  "highest_block": 0,
  "network_id": "mainnet",
  "version": "0.5.0"
}
```

----

#### `is-mining`

##### Parameters

none

##### Returns

`Object`:

- `Boolean` - *is_mining*, whether the node is mining.

##### Example

```java
// Request
boolean flag = Miner.istMining(client);

// Result
{
  "is_mining": true
}
```

----

### `set-mining`

##### Parameters

`Object`:

- `Boolean` - *is_mining*, whether the node is mining.

##### Example

```java
// Request
boolean flag = Miner.setMining(client, isMining);

// Result
```

----

#### `gas-rate`

##### Parameters

none

##### Returns

`Object`:

- `Integer` - *gas_rate*, gas rate.

##### Example

```java
// Request
Gas gas = Gas.gaRate(client);

// Result
{
  "gas_rate": 1000
}
```

----

#### `verify-message`

##### Parameters

`Object`:

- `String` - *address*, address for account.
- `String` - *derived_xpub*, derived xpub.
- `String` - *message*, message for signature by derived_xpub.
- `String` - *signature*, signature for message.

##### Returns

`Object`:

- `Boolean` - *result*, verify result.

##### Example

```java
// Request
boolean flag =  new Message.VerifyBuilder().verifyMessage(client);  

// Result
{
  "result": true
}
```

----

#### `get-work`

##### Parameters

none

##### Returns

`Object`:

- `Object` - *block_header*, raw block header.
- `Object` - *seed*, seed.

```go
type GetWorkResp struct {
	BlockHeader *types.BlockHeader `json:"block_header"`
	Seed        *bc.Hash           `json:"seed"`
}
```

##### Example

```java
// Request
Miner.MinerWork minerWork = Miner.getWork(client);

// Result
{
  "block_header": "0101870103f2c7495164c8f3af43697e81faa21dcb2d60aa5e10ce4f233491e62420742fbeadfcd50540bef2670a5fade2e58ad4955e2375a04ad1e4cb9c104faddab43f4a79e35be253c9c377e5192668bc0a367e4a4764f11e7c725ecced1d7b6a492974fab1b6d5bc00ffffff838080808020",
  "seed": "702bef3f1707577fd0d75b6359a2919fa216487fe306771e27710acbaa9164ce"
}
```

----

#### `submit-work`

##### Parameters

`Object`:

- `Object` - *block_header*, raw block header.

```go
type SubmitWorkReq struct {
	BlockHeader *types.BlockHeader `json:"block_header"`
}
```

##### Returns

true if success

##### Example

```java
// Request
client = TestUtils.generateClient();

// Result
true / error
```
