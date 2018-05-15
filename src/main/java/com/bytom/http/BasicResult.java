package com.bytom.http;

public class BasicResult {

	private int httpCode;
	private String resultObj;

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

	public String getResultObj() {
		return resultObj;
	}

	public void setResultObj(String resultObj) {
		this.resultObj = resultObj;
	}

	@Override
	public String toString() {
		return "BasicResult [httpCode=" + httpCode + ", resultObj=" + resultObj + "]";
	}

}
