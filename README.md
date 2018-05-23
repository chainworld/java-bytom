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
- [Usage](#usage)
    - [Basic Usage](#basic-usage)
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

## Usage


### Basic Usage

```
   public static Client generateClient() throws BytomException {
		String coreURL = Configuration.getValue("bytom.api.url");
		String accessToken = Configuration.getValue("client.access.token");
		if (coreURL == null || coreURL.isEmpty()) {
			coreURL = "http://127.0.0.1:9888/";
		}
		return new Client(coreURL, accessToken);
	}

   Account account = new Account.Builder().setAlias("hello_bytom9")
					.addXpub(key.xpub).setQuorum(1).create(client);
   ...
```


### All usage examples

You find more detailed documentation at [/doc](doc/index.md).


## Support and Feedback

If you find a bug, please submit the issue in Github directly.
[Bytom-JAVA-SDK Issues](https://github.com/chainworld/java-bytom/issues)


## License

Bytom JAVA SDK is based on the MIT protocol.

<http://www.opensource.org/licenses/MIT>