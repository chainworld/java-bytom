package io.bytom.bean;

/**
 * http请求的 返货code，和错误信息
 * 
 * @author niyue
 *
 */
public class HttpResult {

	/**
	 * http请求 返回code
	 */
	private int httpCode;
	/**
	 * 非200code均为失败
	 */
	private boolean codeSuccess;

	/**
	 * bytom错误Code
	 */
	private String code;
	/**
	 * bytom错误message
	 */
	private String message;
	/**
	 * bytom错误temporary
	 * 
	 */
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
