package io.bytom.bean;

public class TransactionFeedResult extends HttpResult {
	private String status;
	private String msg;
	private Object data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "TransactionFeedResult [status=" + status + ", msg=" + msg + ", data=" + data + "]";
	}

	public TransactionFeedResult(int httpCode) {
		super();
		this.setHttpCode(httpCode);
	}

	public TransactionFeedResult(){
		super();
	}
	

}
