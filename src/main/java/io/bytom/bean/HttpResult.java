package io.bytom.bean;

public class HttpResult {

	// httpÇëÇó ·µ»Øcode
	private int httpCode;
	// ·Ç200code¾ùÎªÊ§°Ü
	private boolean codeSuccess;

	// bytom´íÎóCode
	private String code;
	// bytom´íÎómessage
	private String message;
	// bytom´íÎótemporary
	private boolean temporary;

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

	public boolean isCodeSuccess() {
		return codeSuccess;
	}

	public void setCodeSuccess(boolean codeSuccess) {
		this.codeSuccess = codeSuccess;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isTemporary() {
		return temporary;
	}

	public void setTemporary(boolean temporary) {
		this.temporary = temporary;
	}

	@Override
	public String toString() {
		return "HttpResult [httpCode=" + httpCode + ", codeSuccess=" + codeSuccess + ", code=" + code + ", message="
				+ message + ", temporary=" + temporary + "]";
	}

	public String toHttpResultString() {
		return "HttpResult [httpCode=" + httpCode + ", codeSuccess=" + codeSuccess + ", code=" + code + ", message="
				+ message + ", temporary=" + temporary + "]";
	}

}
