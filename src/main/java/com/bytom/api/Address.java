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

	@SerializedName("address")
	public String address;

	@SerializedName("change")
	public Boolean change;

	@SerializedName("vaild")
	public Boolean vaild;

	@SerializedName("is_local")
	public Boolean is_local;

}
