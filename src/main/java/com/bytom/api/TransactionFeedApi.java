package com.bytom.api;

import java.util.HashMap;
import java.util.Map;

import com.bytom.exception.BytomException;
import com.bytom.http.BytomResponse;
import com.bytom.http.Client;

/**
 * 
 * @author niyuelin1990
 *
 */
public class TransactionFeedApi {

	private String alias;
	private String filter;

	private TransactionFeedParam param;

	/**
	 * 创建
	 * 
	 * @param client
	 * @param alias
	 * @param filter
	 * @throws BytomException
	 */
	public void create(Client client, String alias, String filter) throws BytomException {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("alias", alias);
		req.put("filter", filter);
		client.request("create-transaction-feed", req);
	}

	/**
	 * 获取
	 * 
	 * @param client
	 * @param alias
	 * @return
	 * @throws BytomException
	 */
	public TransactionFeedApi get(Client client, String alias) throws BytomException {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("alias", alias);
		return client.requestGet("get-transaction-feed", req, "txfeed", TransactionFeedApi.class);
	}

	/**
	 * 修改
	 * 
	 * @param client
	 * @param alias
	 * @param filter
	 * @throws BytomException
	 */
	public void update(Client client, String alias, String filter) throws BytomException {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("alias", alias);
		req.put("filter", filter);
		client.request("update-transaction-feed", req);
	}

	/**
	 * 列表
	 * 
	 * @param client
	 * @return
	 * @throws BytomException
	 */
	public Items list(Client client) throws BytomException {
		Items items = new Items();
		items.setClient(client);
		return items.query();
	}

	/**
	 * 
	 * @param client
	 * @param alias
	 * @throws BytomException
	 */
	public void delete(Client client, String alias) throws BytomException {
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("alias", alias);
		client.request("delete-transaction-feed", req);
	}

	public class Items extends BytomResponse<TransactionFeedApi> {
		public Items query() throws BytomException {
			Items items = this.client.requestList("list-transaction-feeds", null, Items.class);
			return items;
		}
	}

	public class TransactionFeedParam {
		private String assetid;
		private long lowerlimit;

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

		@Override
		public String toString() {
			return "TransactionFeedParam [assetid=" + assetid + ", lowerlimit=" + lowerlimit + ", upperlimit="
					+ upperlimit + "]";
		}

	}

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
		return "TransactionFeedApi2 [alias=" + alias + ", filter=" + filter + ", param=" + param + "]";
	}

}
