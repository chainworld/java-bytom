package com.bytom.model;

public class TransactionFeedParam {
	private String assetid ;
	private long lowerlimit ;
	
	private long upperlimit;

	public String getAssetid() {
		return assetid;
	}

	public void setAssetid(String assetid) {
		this.assetid = assetid;
	}

	public long getLowerlimit() {
		return lowerlimit;
	}

	public void setLowerlimit(long lowerlimit) {
		this.lowerlimit = lowerlimit;
	}

	public long getUpperlimit() {
		return upperlimit;
	}

	public void setUpperlimit(long upperlimit) {
		this.upperlimit = upperlimit;
	}
	
	
}
