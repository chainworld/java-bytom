package com.bytom.http;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author https://github.com/JackyKen
 */
public class BytomResponse<T> {

	@SerializedName("status")
	public String status;

	@SerializedName("data")
	public List<T> data;

	protected Client client;

	public void setClient(Client client) {
		this.client = client;
	}
	
}
