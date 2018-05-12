package io.bytom.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

public class HttpClientUtil {
	private static final Log logger = LogFactory.getLog(HttpClientUtil.class);

	public static BasicResult httpByPost(String url, Map<String, String> params) {
		BasicResult returnSt = new BasicResult();
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(url);
		if (params != null) {
			try {
				StringEntity stringEntity = new StringEntity(JSON.toJSONString(params));
				System.out.println(JSON.toJSONString(params));
				request.setEntity(stringEntity);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		request.setConfig(getRequestConfig());
		try {
			HttpResponse response = httpClient.execute(request);

			returnSt.setHttpCode(response.getStatusLine().getStatusCode());
			if (response.getEntity() != null) {
				returnSt.setResultObj(EntityUtils.toString(response.getEntity(), "utf-8"));
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
			logger.info("协议错误");
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("网络异常");
		}
		return returnSt;
	}

	public static BasicResult httpByPost(String url, Map<String, String> params, String authorizationStr) {
		BasicResult returnSt = new BasicResult();
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(url);
		request.addHeader("Authorization", authorizationStr);
		if (params != null) {
			try {
				StringEntity stringEntity = new StringEntity(JSON.toJSONString(params));
				System.out.println(JSON.toJSONString(params));
				request.setEntity(stringEntity);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}
		request.setConfig(getRequestConfig());
		try {
			HttpResponse response = httpClient.execute(request);

			returnSt.setHttpCode(response.getStatusLine().getStatusCode());
			if (response.getEntity() != null) {
				returnSt.setResultObj(EntityUtils.toString(response.getEntity(), "utf-8"));
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			// 协议错误
			logger.info("协议错误");
		} catch (IOException e) {
			e.printStackTrace();
			// 网络异常
			logger.info("网络异常");
		}
		return returnSt;
	}

	/**
	 * 配置请求的超时设置
	 * 
	 * @return
	 */
	private static RequestConfig getRequestConfig() {
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(6000).setConnectTimeout(6000)
				.setSocketTimeout(6000).build();
		return requestConfig;
	}

}
