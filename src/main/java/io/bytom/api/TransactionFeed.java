package io.bytom.api;

import java.util.HashMap;
import java.util.Map;
import io.bytom.exception.BytomException;
import io.bytom.http.BytomResponse;
import io.bytom.http.Client;

/**
 * 
 * @author niyuelin1990
 *
 */
public class TransactionFeed {

	/**
	 * name of the transaction feed.
	 */
	public String alias;
	/**
	 * filter, filter of the transaction feed.
	 */
	public String filter;

	/**
	 *  param, param of the transaction feed.
	 */
	public TransactionFeedParam param;

	public static class Builder {
		/**
		 * Token id
		 */
		public String alias;
		/**
		 * Token type
		 */
		public String filter;

		public Builder() {
		}

		/**
		 * 
		 * @param alias the alias to set
		 * @return Builder
		 */
		public Builder setAlias(String alias) {
			this.alias = alias;
			return this;
		}

		/**
		 * 
		 * @param filter  the type to set
		 * @return Builder
		 */
		public Builder setFilter(String filter) {
			this.filter = filter;
			return this;
		}

		/**
		 * 创建
		 * @param client client
		 * @return  true or false
		 * @throws BytomException error
		 */
		public boolean create(Client client) throws BytomException {
			return client.request("create-transaction-feed", this);
		}
	}

	/**
	 * 获取
	 * 
	 * @param client client
	 * @param alias alias
	 * @return txfeed
	 * @throws BytomException Exception
	 */
	public static TransactionFeed get(Client client, String alias) throws BytomException {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("alias", alias);
		return client.requestGet("get-transaction-feed", req, "txfeed", TransactionFeed.class);
	}

	/**
	 * 修改
	 * 
	 * @param client client
	 * @param alias alias
	 * @param filter filter
	 * @return success return true
	 * @throws BytomException Exception
	 */
	public static boolean update(Client client, String alias, String filter) throws BytomException {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("alias", alias);
		req.put("filter", filter);
		return client.request("update-transaction-feed", req);
	}

	/**
	 * 列表
	 * 
	 * @param client client
	 * @return Items
	 * @throws BytomException Exception
	 */
	public static Items list(Client client) throws BytomException {
		Items items = new Items();
		items.setClient(client);
		return items.query();
	}

	/**
	 * delete
	 * 
	 * @param client client
	 * @param alias alias
	 * @return success return true
	 * @throws BytomException Exception
	 */
	public static boolean delete(Client client, String alias) throws BytomException {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("alias", alias);
		return client.request("delete-transaction-feed", req);
	}

	public static class Items extends BytomResponse<TransactionFeed> {
		public Items query() throws BytomException {
			Items items = this.client.requestList("list-transaction-feeds", null, Items.class);
			return items;
		}
	}

	public class TransactionFeedParam {

		/**
		 * assetid
		 */
		public String assetid;

		/**
		 * lowerlimit
		 */
		public long lowerlimit;

		/**
		 * upperlimit
		 */
		public long upperlimit;

	}

}
