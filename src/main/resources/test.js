var buildTransaction = {
	"status" : "success",
	"data" : {
		"raw_transaction" : "07010001012b000855ae95be3d7dc91e919b7782f08bd7b6da3a4c3a549d1281bf1a0f0439c93244e5bfb7b8d45b8faa642b027b7d0125ae208dc90a98f6f312920e85f144afd88423f32d66c69fa53e3c72f80aaf9126fff75151ad00010139919b7782f08bd7b6da3a4c3a549d1281bf1a0f0439c93244e5bfb7b8d45b8faa6401160014130d6c0c9bc8bd441e6c93d923d4f78dd292a9e000",
		"signing_instructions" : [ {
			"position" : 0,
			"witness_components" : [ {
				"type" : "raw_tx_signature",
				"quorum" : 1,
				"keys" : [ {
					"xpub" : "387054d8caf4b51d44714b01a50e38e8507935c1dc6c00b2a3c83a9385d4ce2419620608913249591e8ef6a96ce211450ec2fceeb199c6e6cf78d0ac25364df9",
					"derivation_path" : [ "000700000000000000" ]
				} ],
				"signatures" : null
			} ]
		} ],
		"allow_additional_actions" : false
	}
}

var signTransaction = {
	"status" : "success",
	"data" : {
		"transaction" : {
			"raw_transaction" : "07010001012c00085115c5ecb1246498cde5d4b7b68dbffb47b1f773cd0f467db4b3f7f3119fd3474cb8472919e08924d00f6c027b7d0125ae2066fa0b5131b3903840a4eabdd57fa930d1a4ca2f1d7419cbed3090307b5980cc5151ad0140fe29aed4fa200291e6b9a0da4e40059e1edd79ccf7d14707d54a14c8362d6be2761cf88e934eefa0bbab4ac00600ffcc07fcd20ebb6563a36091459a6443b60d01013acde5d4b7b68dbffb47b1f773cd0f467db4b3f7f3119fd3474cb8472919e08924d00f011600142ee288ea3abfd4f55714079600230a4cfe1108b200",
			"signing_instructions" : [ {
				"position" : 0,
				"witness_components" : [ {
					"type" : "raw_tx_signature",
					"quorum" : 1,
					"keys" : [ {
						"xpub" : "48ee4ce530c02b089c7c6e491acc376fd1aa8fc2a413980fa02eb7e2c3c20e65799f612ae9c74f7d93464d97814cb2b9f9e3c01b1ff931f3270ac2adda522578",
						"derivation_path" : [ "000c00000000000000" ]
					} ],
					"signatures" : [ "fe29aed4fa200291e6b9a0da4e40059e1edd79ccf7d14707d54a14c8362d6be2761cf88e934eefa0bbab4ac00600ffcc07fcd20ebb6563a36091459a6443b60d" ]
				} ]
			} ],
			"allow_additional_actions" : false
		},
		"sign_complete" : true
	}
}

var singerResponse = {
	"status" : "success",
	"data" : {
		"transaction" : {
			"raw_transaction" : "070100010161015fdfebc2f6cc62c45a7e95697e1a9c671f3801d45dafeb6ff69dd6614b31b31296ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8099c4d5990100011600142c8d931da4bf895ca603c5820443767cb1c2bb812201203f1e3abbf0837b4c52a9caf990b87c3cb2ea97d918970e62fca4453e13da75c002013effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff80d3bdc69801011600145bb2dac06868da7a2da5096e48cc80724917c30a00013cffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8084af5f01160014e524fc3f7898a7a811fcdd22b3d7ce5d81d7cd7e00",
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
							"signatures" : [ "" ]
						},
						{
							"type" : "data",
							"value" : "3f1e3abbf0837b4c52a9caf990b87c3cb2ea97d918970e62fca4453e13da75c0"
						} ]
			} ],
			"allow_additional_actions" : false
		},
		"sign_complete" : false
	}
}