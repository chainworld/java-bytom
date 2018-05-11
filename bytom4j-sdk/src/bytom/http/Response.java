
package bytom.http;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import bytom.model.Configuration;
import bytom.model.BytomException;
import bytom.org.json.JSONArray;
import bytom.org.json.JSONException;
import bytom.org.json.JSONObject;

/**
 * 
 * @author https://github.com/JackyKen
 *
 */
public class Response {
    private final static boolean DEBUG = Configuration.getDebug();
    
    static Logger log = Logger.getLogger(Response.class.getName());


    private static ThreadLocal<DocumentBuilder> builders =
            new ThreadLocal<DocumentBuilder>() {
                @Override
                protected DocumentBuilder initialValue() {
                    try {
                        return
                                DocumentBuilderFactory.newInstance()
                                        .newDocumentBuilder();
                    } catch (ParserConfigurationException ex) {
                        throw new ExceptionInInitializerError(ex);
                    }
                }
            };

    private int statusCode;
    private Document responseAsDocument = null;
    private String responseAsString = null;
    private InputStream is;
    private HttpURLConnection con;
    private boolean streamConsumed = false;

    public Response()  {
    	
    }
    public Response(HttpURLConnection con) throws IOException {
        this.con = con;
        this.statusCode = con.getResponseCode();
        if(null == (is = con.getErrorStream())){
            is = con.getInputStream();
        }
        if (null != is && "gzip".equals(con.getContentEncoding())) {
            is = new GZIPInputStream(is);
        }
    }

    // for test purpose
    /*package*/ Response(String content) {
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
        if(streamConsumed){
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
    public String asString() throws BytomException{
        if(null == responseAsString){
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
            } catch (NullPointerException npe) {
                // don't remember in which case npe can be thrown
                throw new BytomException(npe.getMessage(), npe);
            } catch (IOException ioe) {
                throw new BytomException(ioe.getMessage(), ioe);
            }
        }
        return responseAsString;
    }

    /**
     * Returns the response body as org.w3c.dom.Document.<br>
     * Disconnects the internal HttpURLConnection silently.
     * @return response body as org.w3c.dom.Document
     * @throws BytomException
     */
    public Document asDocument() throws BytomException {
        if (null == responseAsDocument) {
            try {
                // it should be faster to read the inputstream directly.
                // but makes it difficult to troubleshoot
                this.responseAsDocument = builders.get().parse(new ByteArrayInputStream(asString().getBytes("UTF-8")));
            } catch (SAXException saxe) {
                throw new BytomException("The response body was not well-formed:\n" + responseAsString, saxe);
            } catch (IOException ioe) {
                throw new BytomException("There's something with the connection.", ioe);
            }
        }
        return responseAsDocument;
    }

    /**
     * Disconnects the internal HttpURLConnection silently.
     * @return response body 
     * @throws BytomException
     */
    public JSONObject asJSONObject() throws BytomException {
        try {
            return new JSONObject(asString());
        } catch (JSONException jsone) {
            throw new BytomException(jsone.getMessage() + ":" + this.responseAsString, jsone);
        }
    }

    /**
     * Disconnects the internal HttpURLConnection silently.
     * @return response body 
     * @throws BytomException
     */
    public JSONArray asJSONArray() throws BytomException {
        try {
        	return  new JSONArray(asString());  
        } catch (Exception jsone) {
            throw new BytomException(jsone.getMessage() + ":" + this.responseAsString, jsone);
        }
    }

    public InputStreamReader asReader() {
        try {
            return new InputStreamReader(is, "UTF-8");
        } catch (java.io.UnsupportedEncodingException uee) {
            return new InputStreamReader(is);
        }
    }

    public void disconnect(){
        con.disconnect();
    }

    private static Pattern escaped = Pattern.compile("&#([0-9]{3,5});");

    /**
     * @param original The string to be unescaped.
     * @return The unescaped string
     */
    public static String unescape(String original) {
        Matcher mm = escaped.matcher(original);
        StringBuffer unescaped = new StringBuffer();
        while (mm.find()) {
            mm.appendReplacement(unescaped, Character.toString(
                    (char) Integer.parseInt(mm.group(1), 10)));
        }
        mm.appendTail(unescaped);
        return unescaped.toString();
    }

    @Override
    public String toString() {
        if(null != responseAsString){
            return responseAsString;
        }
        return "Response{" +
                "statusCode=" + statusCode +
                ", response=" + responseAsDocument +
                ", responseString='" + responseAsString + '\'' +
                ", is=" + is +
                ", con=" + con +
                '}';
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
