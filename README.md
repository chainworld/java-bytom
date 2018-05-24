## Bytom Java SDK

This SDK contains methods for easily interacting with the Bytom API.
Below are examples to get you started. For more information, please see Bytom API reference
documentation at https://github.com/Bytom/bytom/wiki

[![Latest Version](https://img.shields.io/badge/releases-v1.0.1-brightgreen.svg)](https://github.com/lxlxw/bytom-php-sdk/releases)
[![Total Downloads](https://img.shields.io/badge/packagist-v1.0.1-orange.svg)](https://packagist.org/packages/lxlxw/bytom-php-sdk)

## Table of Contents
- [Installation](#installation)
    - [Maven](#maven)
    - [Building from source code](#building-from-source-code)
- [5-Minute Guide](#5-minute-guide)
    - [Maven](#maven)
    - [Initialize the SDK](#initialize-the-sdk) 
    - [Create Keys](#create-keys)
    - [Create an Asset](#create-an-asset)
    - [Create an Account](#create-an-account)
    - [Create an Account Address](#create-an-account-address)
    - [Build the Transaction](#build-the-transaction)
    - [Sign the Transaction](#sign-the-transaction)
    - [Submit the Transaction](#submit-the-transaction)
    - [All usage examples](#all-usage-examples)
- [Support and Feedback](#support-and-feedback)
- [License](#license)


## Installation

There are various ways to install and use this sdk. We'll elaborate on a couple here. Note that the Bytom JAVA SDK requires JAVA 7 or newer.

### Maven
```
<dependency>
  <groupId>io.bytom</groupId>
  <artifactId>bytom-sdk-java</artifactId>
  <version>1.0.1</version>
</dependency>
```


### Building from source code

To clone, compile, and install in your local maven repository (or copy the artifacts from the target/ directory to wherever you need them):
```bash
git clone git@github.com:chainworld/java-bytom.git
cd java-bytom
mvn install
```

## 5-Minute Guide

This guide will walk you through the basic functions of Bytom:

### Initialize the SDK
Create an instance of the SDK. By default, the SDK will try to access a core located at http://127.0.0.1:9888, which is the default if you’re running Bytom Wallet locally.

```java

public static Client generateClient() throws BytomException {
	String coreURL = Configuration.getValue("bytom.api.url");
	String accessToken = Configuration.getValue("client.access.token");
	if (coreURL == null || coreURL.isEmpty()) {
		coreURL = "http://127.0.0.1:9888/";
	}
	return new Client(coreURL, accessToken);
}
```

### Create Keys

```java
Key key = Key.create(client, "alias", "password");
```
It will create a key whose alias is 'alias' while password is 'password'.


### Create an Asset
Create a new asset, providing an alias, key, and quorum. 

```java
String asset = "GOLD";
Asset testAsset = new Asset.Builder()
		      .setAlias(asset)
		      .addRootXpub(key.xpub)
		      .setQuorum(1)
		      .create(client);
```

### Create an Account
Create an account, providing an alias, key, and quorum.

```java
Account account = new Account.Builder()
		      .setAlias("alice")
		      .addXpub(key.xpub)
		      .setQuorum(1)
		      .create(client);
```

### Create an Account Address
 
```java
new Account.ReceiverBuilder()
   .setAccountId(account.id)
   .create(client);
```

### Build the Transaction
 
```java
Transaction.Template controlAddressTx = new Transaction.Builder()
			.addAction(new Transaction.Action.SpendFromAccount()
					.setAccountId(account.id)
					.setAssetId(asset.id)
					.setAmount(300000000))
			.addAction(new Transaction.Action.ControlWithAddress()
					.setAddress(address.id)
					.setAssetId(asset.id)
					.setAmount(200000000))
					.build(client);
```
### Sign the Transaction
```java
Transaction.Template singerTx = new Transaction.SignerBuilder()
                                   .sign(client,controlAcontrolAddressTxddress, "password");
```
### Submit the Transaction
```java
Transaction.submit(client, singerTx); 
```

### All usage examples

You find more detailed documentation at [/doc](doc/index.md). 
Also you can find Junit Test Cases at [/test/java/io/bytom/integration]


## Support and Feedback

If you find a bug, please submit the issue in Github directly.
[Bytom-JAVA-SDK Issues](https://github.com/chainworld/java-bytom/issues)


## License

Bytom JAVA SDK is based on the MIT protocol.

<http://www.opensource.org/licenses/MIT>
