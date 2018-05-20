package com.bytom.api;

import com.google.gson.annotations.SerializedName;

/**
 * @author https://github.com/JackyKen
 */
public class Address {

	@SerializedName("account_alias")
	public String accountAlias;

	@SerializedName("account_id")
	public String accountId;

	public String address;

	public boolean change;

	public boolean vaild;

	@SerializedName("is_local")
	public boolean isLocal;

}
