package io.bytom.bean.model;

public class TransactionFeed {
	private String alias;
	private String filter;
	private TransactionFeedParam param;

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public TransactionFeedParam getParam() {
		return param;
	}

	public void setParam(TransactionFeedParam param) {
		this.param = param;
	}

	@Override
	public String toString() {
		return "TransactionFeed [alias=" + alias + ", filter=" + filter + ", param=" + param + "]";
	}

}
