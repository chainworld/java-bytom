package com.bytom.http;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import com.bytom.common.Utils;
import com.bytom.exception.BytomException;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * @author https://github.com/JackyKen
 */
public class Client {

	private String url;

	private String accessToken;

	private HttpClient client = null;

	private static String version = "dev";

	private MultiThreadedHttpConnectionManager connectionManager;

	private static final int OK = 200;
	private static final int NOT_MODIFIED = 304;
	private static final int BAD_REQUEST = 400;
	private static final int NOT_AUTHORIZED = 401;
	private static final int FORBIDDEN = 403;
	private static final int NOT_FOUND = 404;
	private static final int NOT_ACCEPTABLE = 406;
	private static final int INTERNAL_SERVER_ERROR = 500;
	private static final int BAD_GATEWAY = 502;
	private static final int SERVICE_UNAVAILABLE = 503;

	private final static boolean DEBUG = true;

	private static final String JSON = new String("application/json; charset=utf-8");

	private static Logger log = Logger.getLogger(Client.class.getName());

	public Client() throws BytomException {
		this(150, 30000, 30000);
		this.url = "http://127.0.0.1:9888";
	}

	public Client(String url) {
		this(150, 30000, 30000);
		this.url = url;
	}

	public Client(String url, String accessToken) {
		this(150, 30000, 30000);
		this.url = url;
		this.accessToken = accessToken;
	}

	/**
	 * @param maxConPerHost
	 * @param conTimeOutMs
	 * @param soTimeOutMs
	 */
	public Client(int maxConPerHost, int conTimeOutMs, int soTimeOutMs) {
		connectionManager = new MultiThreadedHttpConnectionManager();
		HttpConnectionManagerParams params = connectionManager.getParams();
		params.setDefaultMaxConnectionsPerHost(maxConPerHost);
		params.setConnectionTimeout(conTimeOutMs);
		params.setSoTimeout(soTimeOutMs);
		HttpClientParams clientParams = new HttpClientParams();
		client = new HttpClient(clientParams, connectionManager);

	}

	/**
	 * Perform a single HTTP POST request against the API for a specific action, ignoring
	 * the body of the response.
	 * 
	 * @param action The requested API action
	 * @param body Body payload sent to the API as JSON
	 * @throws BytomException
	 */
	public void request(String action, Object body) throws BytomException {
		ResponseCreator<Object> rc = new ResponseCreator<Object>() {
			public Object create(Response response, Gson deserializer)
					throws IOException, BytomException {
				JsonElement root = new JsonParser().parse(response.asString());
				JsonElement status = root.getAsJsonObject().get("status");
				if (status != null && status.toString().contains("fail"))
					throw new BytomException(root.getAsJsonObject().get("msg").toString());
				return null;
			}
		};
		post(action, body, rc);
	}

	/**
	 * Perform a single HTTP POST request against the API for a specific action.
	 *
	 * @param action The requested API action
	 * @param body Body payload sent to the API as JSON
	 * @param tClass Type of object to be deserialized from the response JSON
	 * @return the result of the post request
	 * @throws BytomException
	 */
	public <T> T request(String action, Object body, final Type tClass)
			throws BytomException {
		ResponseCreator<T> rc = new ResponseCreator<T>() {
			public T create(Response response, Gson deserializer) throws IOException,
					BytomException {
				JsonElement root = new JsonParser().parse(response.asString());
				JsonElement status = root.getAsJsonObject().get("status");
				JsonElement data = root.getAsJsonObject().get("data");
				if (status != null && status.toString().contains("fail"))
					throw new BytomException(root.getAsJsonObject().get("msg").toString());
				else if (data != null)
					return deserializer.fromJson(data, tClass);
				else
					return deserializer.fromJson(response.asString(), tClass);
			}
		};
		return post(action, body, rc);
	}

	public <T> T requestList(String action, Object body, final Type tClass)
			throws BytomException {
		ResponseCreator<T> rc = new ResponseCreator<T>() {
			public T create(Response response, Gson deserializer) throws IOException,
					BytomException {
				JsonElement root = new JsonParser().parse(response.asString());
				JsonElement status = root.getAsJsonObject().get("status");
				if (status != null && status.toString().contains("fail"))
					throw new BytomException(root.getAsJsonObject().get("msg").toString());
				return deserializer.fromJson(response.asString(), tClass);
			}
		};
		return post(action, body, rc);
	}

	/**
	 * @param method
	 * @return
	 * @throws BytomException
	 */
	private <T> T post(String action, Object body, ResponseCreator<T> respCreator)
			throws BytomException {
		log("Request:");
		log("Action:" + action);
		int responseCode = -1;
		String content = Utils.serializer.toJson(body);
		System.out.println("content:" + content);
		PostMethod postMethod = new PostMethod(this.url + action);
		try {
			HttpMethodParams param = postMethod.getParams();
			param.setContentCharset("UTF-8");
			postMethod.setRequestHeader("Content-Type", JSON);
			StringRequestEntity requestEntity = null;
			requestEntity = new StringRequestEntity(content,
					"application/x-www-form-urlencoded", "UTF-8");
			postMethod.setRequestEntity(requestEntity);

			List<Header> headers = new ArrayList<Header>();
			headers.add((new Header("User-Agent", "bytom-sdk-java-" + version)));
			client.getHostConfiguration().getParams()
					.setParameter("http.default-headers", headers);
			for (Header hd : headers) {
				log(hd.getName() + ": " + hd.getValue());
			}
			postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(3, false));
			client.executeMethod(postMethod);

			Header[] resHeader = postMethod.getResponseHeaders();
			responseCode = postMethod.getStatusCode();
			log("Response:");
			log("https StatusCode:" + String.valueOf(responseCode));

			for (Header header : resHeader) {
				log(header.getName() + ":" + header.getValue());
			}
			Response response = new Response();
			response.setResponseAsString(postMethod.getResponseBodyAsString());
			log(response.toString() + "\n");
			if (responseCode != OK) {
				throw new BytomException(getCause(responseCode), response.toString(),
						postMethod.getStatusCode());
			}
			return respCreator.create(response, Utils.serializer);
		}
		catch (IOException ioe) {
			throw new BytomException(ioe.getMessage(), ioe, responseCode);
		}
		finally {
			postMethod.releaseConnection();
		}
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
			cause = "The request is understood, but it has been refused.  ";
			break;
		case NOT_FOUND:
			cause = "The URI requested is invalid or the resource requested.";
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

	/**
	 * Defines an interface for deserializing HTTP responses into objects.
	 * @param <T> the type of object to return
	 */
	public interface ResponseCreator<T> {
		/**
		 * Deserializes an HTTP response into a Java object of type T.
		 * @param response HTTP response object
		 * @param deserializer json deserializer
		 * @return an object of type T
		 * @throws BytomException
		 * @throws IOException
		 */
		T create(Response response, Gson deserializer) throws BytomException, IOException;
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
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

}
