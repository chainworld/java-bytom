package io.bytom.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import io.bytom.bean.TransactionFeedResult;
import io.bytom.bean.model.Transaction;
import io.bytom.http.BasicResult;
import io.bytom.http.HttpClientUtil;
import io.bytom.util.StringUtil;

public class TransactionFeedApi extends BasisModel {

	public TransactionFeedApi(String url) {
		super();
		this.setUrl(url);
	}

	public TransactionFeedApi(String url, String username, String password) {
		this.setAuthorization(username, password);
		this.setUrl(url);
	}

	public TransactionFeedResult createTransactionFeed(String alias, String filter) {
		Map<String, String> m = new HashMap<>();
		m.put("alias", alias);
		m.put("filter", filter);
		BasicResult returnSt = new BasicResult();
		System.out.println(this.getUrl() + "  " + this.getAuthorization());
		if (StringUtil.notNull(this.getAuthorization())) {
			returnSt = HttpClientUtil.httpByPost(this.getUrl() + "/create-transaction-feed", m,
					this.getAuthorization());
		} else {
			returnSt = HttpClientUtil.httpByPost(this.getUrl() + "/create-transaction-feed", m);
		}

		TransactionFeedResult feedResult = new TransactionFeedResult();
		feedResult.setHttpCode(returnSt.getHttpCode());
		if (returnSt.getHttpCode() == 200) {
			feedResult.setSuccess(true);
		}
		if (StringUtil.notNull(returnSt.getResultObj())) {
			JSONObject jsonObject = JSON.parseObject(returnSt.getResultObj());
			if (returnSt.getHttpCode() == 200) {
				feedResult.setStatus(jsonObject.getString("status"));
			} else {
				feedResult.setCode(jsonObject.getString("code"));
				feedResult.setMessage(jsonObject.getString("message"));
				feedResult.setTemporary(jsonObject.getBooleanValue("temporary"));
			}
		}
		return feedResult;
	}

	public TransactionFeedResult listTransactionFeeds() {
		BasicResult returnSt = new BasicResult();
		if (this.getAuthorization() != null) {
			returnSt = HttpClientUtil.httpByPost(this.getUrl() + "/list-transaction-feeds", null,
					this.getAuthorization());
		} else {
			returnSt = HttpClientUtil.httpByPost(this.getUrl() + "/list-transaction-feeds", null);

		}
		TransactionFeedResult feedResult = new TransactionFeedResult();
		feedResult.setHttpCode(returnSt.getHttpCode());
		if (returnSt.getHttpCode() == 200) {
			feedResult.setSuccess(true);
		}
		if (StringUtil.notNull(returnSt.getResultObj())) {
			JSONObject jsonObject = JSON.parseObject(returnSt.getResultObj());
			if (returnSt.getHttpCode() == 200) {
				feedResult.setStatus(jsonObject.getString("status"));
				JSON.parseObject(jsonObject.getString("data"), new TypeReference<List<Transaction>>() {
				});
			} else {
				feedResult.setCode(jsonObject.getString("code"));
				feedResult.setMessage(jsonObject.getString("message"));
				feedResult.setTemporary(jsonObject.getBooleanValue("temporary"));
			}
		}
		return feedResult;
	}

}
