package com.bytom.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import org.apache.log4j.Logger;

import com.bytom.exception.BytomException;

/**
 * 
 * @author https://github.com/JackyKen
 *
 */
public class Response {

	private final static boolean DEBUG = true;

	static Logger log = Logger.getLogger(Response.class.getName());

	private int statusCode;
	private String responseAsString = null;
	private InputStream is;
	private HttpURLConnection con;
	private boolean streamConsumed = false;

	public Response() {

	}

	public Response(HttpURLConnection con) throws IOException {
		this.con = con;
		this.statusCode = con.getResponseCode();
		if (null == (is = con.getErrorStream())) {
			is = con.getInputStream();
		}
	}

	public Response(String content) {
		this.responseAsString = content;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getResponseHeader(String name) {
		if (con != null)
			return con.getHeaderField(name);
		else
			return null;
	}

	/**
	 * Returns the response stream.<br>
	 * This method cannot be called after calling asString() or asDcoument()<br>
	 * It is suggested to call disconnect() after consuming the stream.
	 *
	 * Disconnects the internal HttpURLConnection silently.
	 * @return response body stream
	 * @throws BytomException
	 * @see #disconnect()
	 */
	public InputStream asStream() {
		if (streamConsumed) {
			throw new IllegalStateException("Stream has already been consumed.");
		}
		return is;
	}

	/**
	 * Returns the response body as string.<br>
	 * Disconnects the internal HttpURLConnection silently.
	 * @return response body
	 * @throws BytomException
	 */
	public String asString() throws BytomException {
		if (null == responseAsString) {
			BufferedReader br;
			try {
				InputStream stream = asStream();
				if (null == stream) {
					return null;
				}
				br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
				StringBuffer buf = new StringBuffer();
				String line;
				while (null != (line = br.readLine())) {
					buf.append(line).append("\n");
				}
				this.responseAsString = buf.toString();
				log(responseAsString);
				stream.close();
				con.disconnect();
				streamConsumed = true;
			}
			catch (NullPointerException npe) {
				throw new BytomException(npe.getMessage(), npe);
			}
			catch (IOException ioe) {
				throw new BytomException(ioe.getMessage(), ioe);
			}
		}
		return responseAsString;
	}

	public InputStreamReader asReader() {
		try {
			return new InputStreamReader(is, "UTF-8");
		}
		catch (java.io.UnsupportedEncodingException uee) {
			return new InputStreamReader(is);
		}
	}

	public void disconnect() {
		con.disconnect();
	}

	@Override
	public String toString() {
		if (null != responseAsString) {
			return responseAsString;
		}
		return "Response{" + "statusCode=" + statusCode + ", responseString='"
				+ responseAsString + '\'' + ", is=" + is + ", con=" + con + '}';
	}

	private void log(String message) {
		if (DEBUG) {
			log.debug("[" + new java.util.Date() + "]" + message);
		}
	}

	public String getResponseAsString() {
		return responseAsString;
	}

	public void setResponseAsString(String responseAsString) {
		this.responseAsString = responseAsString;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
