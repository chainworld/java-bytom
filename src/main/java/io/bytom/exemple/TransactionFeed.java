package io.bytom.exemple;


import io.bytom.api.TransactionFeedApi;
import io.bytom.bean.TransactionFeedResult;

public class TransactionFeed {
	private static TransactionFeedApi tfapi;

	public static void main(String[] args) {
//		tfapi = new TransactionFeedApi("http://127.0.0.1:9888");
		tfapi = new TransactionFeedApi("http://10.100.7.47:9888", "wt", "3d17dbb953cedd53353bf3f342bb2929e9505105ffeb21670e6bd00abeef3772");
//		createtransactionFeed();
		System.out.println(tfapi.listTransactionFeeds());
		
	}

	private static void createtransactionFeed() {
		String alias = "test2";
		String filter = "asset_id='84778a666fe453cf73b2e8c783dbc9e04bc4fd7cbb4f50caeaee99cf9967ebed' AND amount_lower_limit = 50 AND amount_upper_limit = 100";
		
		TransactionFeedResult result= tfapi.createTransactionFeed(alias, filter);
		
		System.out.println(result);
	}

}
