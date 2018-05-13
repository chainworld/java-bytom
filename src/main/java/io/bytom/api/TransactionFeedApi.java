package io.bytom.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import io.bytom.bean.TransactionFeedResult;
import io.bytom.bean.model.TransactionFeed;
import io.bytom.http.BasicResult;
import io.bytom.util.StringUtil;

/**
 * transaction-feed api
 * 
 * @author niyue
 *
 */
public class TransactionFeedApi extends BasisModel {

	public TransactionFeedApi(String url) {
		super();
		this.setUrl(url);
	}

	public TransactionFeedApi(String url, String username, String password) {
		this.setAuthorization(username, password);
		this.setUrl(url);
	}

	/**
	 * 新增
	 * @param alias，name of the transaction feed.
	 * @param filter
	 * @return
	 */
	public TransactionFeedResult createTransactionFeed(String alias, String filter) {
		Map<String, String> m = new HashMap<>();
		m.put("alias", alias);
		m.put("filter", filter);

		System.out.println(this.getUrl() + "  " + this.getAuthorization());
		BasicResult returnSt = this.dohttpPost("/create-transaction-feed", m);

		TransactionFeedResult feedResult = new TransactionFeedResult(returnSt.getHttpCode());
		if (returnSt.getHttpCode() == 200) {
			feedResult.setCodeSuccess(true);
		}

		if (StringUtil.notNull(returnSt.getResultObj())) {
			JSONObject jsonObject = JSON.parseObject(returnSt.getResultObj());
			if (returnSt.getHttpCode() == 200) {
				feedResult.setStatus(jsonObject.getString("status"));
				feedResult.setMsg(jsonObject.getString("msg"));
			} else {
				setFailData(feedResult, jsonObject);
			}
		}
		return feedResult;
	}

	/**
	 * list
	 * @return
	 */
	public TransactionFeedResult listTransactionFeeds() {
		BasicResult returnSt = this.dohttpPost("/list-transaction-feeds", null);

		TransactionFeedResult feedResult = new TransactionFeedResult(returnSt.getHttpCode());
		if (returnSt.getHttpCode() == 200) {
			feedResult.setCodeSuccess(true);
		}

		if (StringUtil.notNull(returnSt.getResultObj())) {
			JSONObject jsonObject = JSON.parseObject(returnSt.getResultObj());
			if (returnSt.getHttpCode() == 200) {
				feedResult.setStatus(jsonObject.getString("status"));
				feedResult.setMsg(jsonObject.getString("msg"));
				feedResult.setData(
						JSON.parseObject(jsonObject.getString("data"), new TypeReference<List<TransactionFeed>>() {
						}));
			} else {
				setFailData(feedResult, jsonObject);
			}
		}
		return feedResult;
	}

	/**
	 * get
	 * @param alias0， name of the transaction feed.
	 * @return
	 */
	public TransactionFeedResult getTransactionFeed(String alias0) {
		Map<String, String> m = new HashMap<>();
		m.put("alias", alias0);
		BasicResult returnSt = this.dohttpPost("/get-transaction-feed", m);

		TransactionFeedResult feedResult = new TransactionFeedResult(returnSt.getHttpCode());
		if (returnSt.getHttpCode() == 200) {
			feedResult.setCodeSuccess(true);
		}
		if (StringUtil.notNull(returnSt.getResultObj())) {
			JSONObject jsonObject = JSON.parseObject(returnSt.getResultObj());
			if (returnSt.getHttpCode() == 200) {
				feedResult.setStatus(jsonObject.getString("status"));
				feedResult.setMsg(jsonObject.getString("msg"));
				feedResult.setData(JSON.parseObject(jsonObject.getString("data"),
						new TypeReference<Map<String, TransactionFeed>>() {
						}));
			} else {
				setFailData(feedResult, jsonObject);
			}
		}
		return feedResult;
	}

	/**
	 * 设置错误信息
	 * 
	 * @param feedResult 
	 * @param jsonObject 返回值
	 */
	private void setFailData(TransactionFeedResult feedResult, JSONObject jsonObject) {
		feedResult.setCode(jsonObject.getString("code"));
		feedResult.setMessage(jsonObject.getString("message"));
		feedResult.setTemporary(jsonObject.getBooleanValue("temporary"));
	}

	/**
	 * 修改
	 * @param alias, name of the transaction feed.
	 * @param filter filter of the transaction feed.
	 * @return
	 */
	public TransactionFeedResult updateTransactionFeed(String alias, String filter) {
		Map<String, String> m = new HashMap<>();
		m.put("alias", alias);
		m.put("filter", filter);

		BasicResult returnSt = this.dohttpPost("/update-transaction-feed", m);

		TransactionFeedResult feedResult = new TransactionFeedResult(returnSt.getHttpCode());
		if (returnSt.getHttpCode() == 200) {
			feedResult.setCodeSuccess(true);
		}

		if (StringUtil.notNull(returnSt.getResultObj())) {
			JSONObject jsonObject = JSON.parseObject(returnSt.getResultObj());
			if (returnSt.getHttpCode() == 200) {
				feedResult.setStatus(jsonObject.getString("status"));
				feedResult.setMsg(jsonObject.getString("msg"));
			} else {
				setFailData(feedResult, jsonObject);
			}
		}
		return feedResult;
	}

	/**
	 * 删除
	 * @param  alias, name of the transaction feed.
	 * @return
	 */
	public TransactionFeedResult deleteTransactionFeed(String alias) {
		Map<String, String> m = new HashMap<>();
		m.put("alias", alias);
		BasicResult returnSt = this.dohttpPost("/delete-transaction-feed", m);

		TransactionFeedResult feedResult = new TransactionFeedResult(returnSt.getHttpCode());
		if (returnSt.getHttpCode() == 200) {
			feedResult.setCodeSuccess(true);
		}
		if (StringUtil.notNull(returnSt.getResultObj())) {
			JSONObject jsonObject = JSON.parseObject(returnSt.getResultObj());
			if (returnSt.getHttpCode() == 200) {
				feedResult.setStatus(jsonObject.getString("status"));
				feedResult.setMsg(jsonObject.getString("msg"));
			} else {
				setFailData(feedResult, jsonObject);
			}
		}
		return feedResult;
	}

}
