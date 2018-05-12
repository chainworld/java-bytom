package io.bytom.http;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class HttpClientUtil {
	private static final Log logger = LogFactory.getLog(HttpClientUtil.class);

	
	public static String httpByPost(String url, Map<String, Object> params) {
		String returnSt = "";
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(url);
		if (params != null) {
			List<NameValuePair> valuePairs = new LinkedList<NameValuePair>();
			Iterator<Entry<String, Object>> it = params.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Object> entry = it.next();
				valuePairs.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue().toString()));
			}
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(valuePairs,
					Consts.UTF_8);
			request.setEntity(entity);
		}
		request.setConfig(getRequestConfig());
		try {
			HttpResponse response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				returnSt = EntityUtils.toString(response.getEntity(), "utf-8");
			}
		} catch (ClientProtocolException e) {
			// 协议错误
			logger.info("协议错误");
		} catch (IOException e) {
			// 网络异常
			logger.info("网络异常");
		}
		return returnSt;
	}
	
	public static String httpByPost(String url, Map<String, Object> params, String authorizationStr) {
		String returnSt = "";
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(url);
		request.addHeader("Authorization", authorizationStr);  
		if (params != null) {
			List<NameValuePair> valuePairs = new LinkedList<NameValuePair>();
			Iterator<Entry<String, Object>> it = params.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Object> entry = it.next();
				valuePairs.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue().toString()));
			}
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(valuePairs,
					Consts.UTF_8);
			request.setEntity(entity);
		}
		request.setConfig(getRequestConfig());
		try {
			HttpResponse response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				returnSt = EntityUtils.toString(response.getEntity(), "utf-8");
			}
		} catch (ClientProtocolException e) {
			// 协议错误
			logger.info("协议错误");
		} catch (IOException e) {
			// 网络异常
			logger.info("网络异常");
		}
		return returnSt;
	}
	
	
	
	/**
	 * 配置请求的超时设置
	 * @return
	 */
	private static RequestConfig getRequestConfig() {
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(6000).setConnectTimeout(6000)
				.setSocketTimeout(6000).build();
		return requestConfig;
	}
}
