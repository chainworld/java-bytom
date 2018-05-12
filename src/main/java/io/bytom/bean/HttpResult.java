package io.bytom.bean;

public class HttpResult {
	
	private static int httpCode;

	public static int getHttpCode() {
		return httpCode;
	}

	public static void setHttpCode(int httpCode) {
		HttpResult.httpCode = httpCode;
	}
	

}
