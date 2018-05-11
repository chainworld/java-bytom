package bytom.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import bytom.model.Configuration; 
import bytom.model.PostParameter; 
import bytom.model.BytomException; 
import bytom.org.json.JSONException; 
import bytom.org.json.JSONObject; 

/**
 * 
 * @author https://github.com/JackyKen
 *
 */
public class HttpClient implements java.io.Serializable {

	private static final long serialVersionUID = -176092625883595547L; 
	private static final int OK 				   = 200;// OK: Success!
	private static final int NOT_MODIFIED 		   = 304;// Not Modified: There was no new data to return.
	private static final int BAD_REQUEST 		   = 400;// Bad Request: The request was invalid.  An accompanying error message will explain why. This is the status code will be returned during rate limiting.
	private static final int NOT_AUTHORIZED 	   = 401;// Not Authorized: Authentication credentials were missing or incorrect.
	private static final int FORBIDDEN 			   = 403;// Forbidden: The request is understood, but it has been refused.  An accompanying error message will explain why.
	private static final int NOT_FOUND             = 404;// Not Found: The URI requested is invalid or the resource requested, such as a user, does not exists.
	private static final int NOT_ACCEPTABLE        = 406;// Not Acceptable: Returned by the Search API when an invalid format is specified in the request.
	private static final int INTERNAL_SERVER_ERROR = 500;// Internal Server Error: Something is broken.  Please post to the group so the Bytom team can investigate.
	private static final int BAD_GATEWAY           = 502;// Bad Gateway: Bytom is down or being upgraded.
	private static final int SERVICE_UNAVAILABLE   = 503;// Service Unavailable: The Bytom servers are up, but overloaded with requests. Try again later. The search and trend methods use this to indicate when you are being rate limited.

	private String proxyHost = Configuration.getProxyHost();
	private int proxyPort = Configuration.getProxyPort();
	private String proxyAuthUser = Configuration.getProxyUser();
	private String proxyAuthPassword = Configuration.getProxyPassword();

	private final static boolean DEBUG = Configuration.getDebug();
	static Logger log = Logger.getLogger(HttpClient.class.getName());
	org.apache.commons.httpclient.HttpClient client = null;

	private MultiThreadedHttpConnectionManager connectionManager;

	public HttpClient() {
		this(150, 30000, 30000);
	}
	
	/**
	 * @param maxConPerHost
	 * @param conTimeOutMs
	 * @param soTimeOutMs
	 */
	public HttpClient(int maxConPerHost, int conTimeOutMs, int soTimeOutMs) {
		connectionManager = new MultiThreadedHttpConnectionManager();
		HttpConnectionManagerParams params = connectionManager.getParams();
		params.setDefaultMaxConnectionsPerHost(maxConPerHost);
		params.setConnectionTimeout(conTimeOutMs);
		params.setSoTimeout(soTimeOutMs);

		HttpClientParams clientParams = new HttpClientParams();

		client = new org.apache.commons.httpclient.HttpClient(clientParams, connectionManager);

		// 支持PROXY
		if (proxyHost != null && !proxyHost.equals("")) {
			client.getHostConfiguration().setProxy(proxyHost, proxyPort);
			client.getParams().setAuthenticationPreemptive(true);
			if (proxyAuthUser != null && !proxyAuthUser.equals("")) {
				client.getState().setProxyCredentials(AuthScope.ANY,
						new UsernamePasswordCredentials(proxyAuthUser, proxyAuthPassword));
				log("Proxy AuthUser: " + proxyAuthUser);
				log("Proxy AuthPassword: " + proxyAuthPassword);
			}
		}
	}

	/**
	 * log调试
	 * @param message
	 */
	private static void log(String message) {
		if (DEBUG) {
			log.debug(message);
		}
	}

	/**
	 *  处理 HTTP get method 请求
	 * @param url
	 * @param params
	 * @return
	 * @throws BytomException
	 */
	public Response get(String url, PostParameter[] params) throws BytomException {

		log("Request:");
		log("GET:" + url);
		if (null != params && params.length > 0) {
			String encodedParams = HttpClient.encodeParameters(params);
			if (-1 == url.indexOf("?")) {
				url += "?" + encodedParams;
			} else {
				url += "&" + encodedParams;
			}
		}
		GetMethod getmethod = new GetMethod(url);
		return httpRequest(getmethod);

	}

	/**
	 * 处理HTTP delete method 请求
	 * @param url
	 * @param params
	 * @return
	 * @throws BytomException
	 */
	public Response delete(String url, PostParameter[] params) throws BytomException {
		if (0 != params.length) {
			String encodedParams = HttpClient.encodeParameters(params);
			if (-1 == url.indexOf("?")) {
				url += "?" + encodedParams;
			} else {
				url += "&" + encodedParams;
			}
		}
		DeleteMethod deleteMethod = new DeleteMethod(url);
		return httpRequest(deleteMethod);

	}

	/**
	 * 处理HTTP post请求
	 * 
	 * @throws JSONException
	 * @throws UnsupportedEncodingException
	 * 
	 */
	public Response post(String url, JSONObject body) throws BytomException   {
		log("Request:");
		log("POST:" + url);
		PostMethod postMethod = new PostMethod(url);
		HttpMethodParams param = postMethod.getParams();
		param.setContentCharset("UTF-8");
		postMethod.setRequestHeader("Content-Type", "text/plain;chartset=UTF-8");
		StringRequestEntity requestEntity;
		if (body != null) {
			try {
				requestEntity = new StringRequestEntity(body.toString(), "application/x-www-form-urlencoded", "UTF-8");
				postMethod.setRequestEntity(requestEntity);
			}
			catch (UnsupportedEncodingException e) {
				throw new BytomException(e.getMessage());
			}
		}
		return httpRequest(postMethod);
	}
	
	/**
	 * @param method
	 * @return
	 * @throws BytomException
	 */
	public Response httpRequest(HttpMethod method) throws BytomException {
		InetAddress ipaddr;
		int responseCode = -1;
		try {
			ipaddr = InetAddress.getLocalHost();
			List<Header> headers = new ArrayList<Header>();
			headers.add(new Header("API-RemoteIP", ipaddr.getHostAddress()));
			client.getHostConfiguration().getParams().setParameter("http.default-headers", headers);
			for (Header hd : headers) {
				log(hd.getName() + ": " + hd.getValue());
			}
			method.getParams()
					.setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
			client.executeMethod(method);
			Header[] resHeader = method.getResponseHeaders();
			responseCode = method.getStatusCode();
			log("Response:");
			log("https StatusCode:" + String.valueOf(responseCode));

			for (Header header : resHeader) {
				log(header.getName() + ":" + header.getValue());
			}
			Response response = new Response();
			response.setResponseAsString(method.getResponseBodyAsString());
			log(response.toString() + "\n");

			if (responseCode != OK) {
				try {
					throw new BytomException(getCause(responseCode), response.asJSONObject(), method.getStatusCode());
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return response;

		} catch (IOException ioe) {
			throw new BytomException(ioe.getMessage(), ioe, responseCode);
		} finally {
			method.releaseConnection();
		}

	}

	/*
	 * 对parameters进行encode处理
	 */
	public static String encodeParameters(PostParameter[] postParams) {
		StringBuffer buf = new StringBuffer();
		for (int j = 0; j < postParams.length; j++) {
			if (j != 0) {
				buf.append("&");
			}
			try {
				buf.append(URLEncoder.encode(postParams[j].getName(), "UTF-8")).append("=")
						.append(URLEncoder.encode(postParams[j].getValue(), "UTF-8"));
			} catch (java.io.UnsupportedEncodingException neverHappen) {
			}
		}
		return buf.toString();
	}

	private static String getCause(int statusCode) {
		String cause = null;
		switch (statusCode) {
		case NOT_MODIFIED:
			break;
		case BAD_REQUEST:
			cause = "The request was invalid.  An accompanying error message will explain why. This is the status code will be returned during rate limiting.";
			break;
		case NOT_AUTHORIZED:
			cause = "Authentication credentials were missing or incorrect.";
			break;
		case FORBIDDEN:
			cause = "The request is understood, but it has been refused.  An accompanying error message will explain why.";
			break;
		case NOT_FOUND:
			cause = "The URI requested is invalid or the resource requested, such as a user, does not exists.";
			break;
		case NOT_ACCEPTABLE:
			cause = "Returned by the Search API when an invalid format is specified in the request.";
			break;
		case INTERNAL_SERVER_ERROR:
			cause = "Something is broken.  Please post to the group so the Bytom team can investigate.";
			break;
		case BAD_GATEWAY:
			cause = "Bytom is down or being upgraded.";
			break;
		case SERVICE_UNAVAILABLE:
			cause = "Service Unavailable: The Bytom servers are up, but overloaded with requests. Try again later. The search and trend methods use this to indicate when you are being rate limited.";
			break;
		default:
			cause = "";
		}
		return statusCode + ":" + cause;
	}

	public String getProxyHost() {
		return proxyHost;
	}

	/**
	 * Sets proxy host.
	 * @param proxyHost
	 */
	public void setProxyHost(String proxyHost) {
		this.proxyHost = Configuration.getProxyHost(proxyHost);
	}

	public int getProxyPort() {
		return proxyPort;
	}

	/**
	 * Sets proxy port.
	 * @param proxyPort
	 */
	public void setProxyPort(int proxyPort) {
		this.proxyPort = Configuration.getProxyPort(proxyPort);
	}

	public String getProxyAuthUser() {
		return proxyAuthUser;
	}

	/**
	 * Sets proxy authentication user. 
	 * @param proxyAuthUser
	 */
	public void setProxyAuthUser(String proxyAuthUser) {
		this.proxyAuthUser = Configuration.getProxyUser(proxyAuthUser);
	}

	public String getProxyAuthPassword() {
		return proxyAuthPassword;
	}

	/**
	 * Sets proxy authentication password. System property
	 * @param proxyAuthPassword
	 */
	public void setProxyAuthPassword(String proxyAuthPassword) {
		this.proxyAuthPassword = Configuration.getProxyPassword(proxyAuthPassword);
	}

}
