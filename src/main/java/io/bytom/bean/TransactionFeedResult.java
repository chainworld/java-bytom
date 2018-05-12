package io.bytom.bean;

import java.util.List;

import io.bytom.bean.model.Transaction;

public class TransactionFeedResult extends HttpResult {
	private String status;
	private List<Transaction> data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Transaction> getData() {
		return data;
	}

	public void setData(List<Transaction> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "TransactionFeedResult [status=" + status + ", data=" + data + ", toString()=" + super.toString() + "]";
	}


}
