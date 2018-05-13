package io.bytom.exemple;

import io.bytom.api.TransactionFeedApi;
import io.bytom.bean.TransactionFeedResult;

/**
 * transaction-feed ÔöÉ¾¸Ä²é
 * 
 * @author niyue
 *
 */
public class TransactionFeed {
	private static TransactionFeedApi tfapi;

	public static void main(String[] args) {
		// tfapi = new TransactionFeedApi("http://127.0.0.1:9888");
		tfapi = new TransactionFeedApi("http://10.100.7.47:9888", "wt",
				"3d17dbb953cedd53353bf3f342bb2929e9505105ffeb21670e6bd00abeef3772");
		String alias = "test1";
		createtransactionFeed(alias);
		getTransactionFeed(alias);
		updateTransactionFeed(alias);
		getTransactionFeed(alias);
		listTransactionFeeds();
		deleteTransactionFeed(alias);
		listTransactionFeeds();
	}

	private static void createtransactionFeed(String alias0) {
		String alias = alias0;
		String filter = "asset_id='84778a666fe453cf73b2e8c783dbc9e04bc4fd7cbb4f50caeaee99cf9967ebed' AND amount_lower_limit = 50 AND amount_upper_limit = 100";

		TransactionFeedResult result = tfapi.createTransactionFeed(alias, filter);

		if (result.isCodeSuccess()) {
			System.out.println("createtransactionFeed: " + result);
		} else {
			System.out.println("createtransactionFeed fail: " + result.toHttpResultString());
		}

	}

	private static void listTransactionFeeds() {
		TransactionFeedResult feedResult = tfapi.listTransactionFeeds();
		if (feedResult.isCodeSuccess()) {
			System.out.println("listTransactionFeeds: " + feedResult.getData());
		} else {
			System.out.println("listTransactionFeeds fail: " + feedResult.toHttpResultString());
		}
	}

	private static void getTransactionFeed(String alias0) {
		TransactionFeedResult result = tfapi.getTransactionFeed(alias0);
		if (result.isCodeSuccess()) {
			System.out.println("getTransactionFeed: " + result);
		} else {
			System.out.println("getTransactionFeed fail: " + result.toHttpResultString());
		}
	}

	private static void deleteTransactionFeed(String alias0) {
		TransactionFeedResult result = tfapi.deleteTransactionFeed(alias0);
		if (result.isCodeSuccess()) {
			System.out.println("getTransactionFeed: " + result);
		} else {
			System.out.println("getTransactionFeed fail: " + result.toHttpResultString());
		}
	}

	private static void updateTransactionFeed(String alias0) {
		String alias = alias0;
		String filter = "asset_id='84778a666fe453cf73b2e8c783dbc9e04bc4fd7cbb4f50caeaee99cf9967ebed' AND amount_lower_limit = 50 AND amount_upper_limit = 80";

		TransactionFeedResult result = tfapi.updateTransactionFeed(alias, filter);
		if (result.isCodeSuccess()) {
			System.out.println("getTransactionFeed: " + result);
		} else {
			System.out.println("getTransactionFeed fail: " + result.toHttpResultString());
		}
	}
}
