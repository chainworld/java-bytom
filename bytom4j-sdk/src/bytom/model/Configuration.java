
package bytom.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.AccessControlException;
import java.util.Properties;

/**
 * 
 * @author https://github.com/JackyKen
 *
 */
public class Configuration {
    private static Properties defaultProperty;

    static {
        init();
    }

   static void init() {
        defaultProperty = new Properties();
        defaultProperty.setProperty("bytom.debug", "true");
        defaultProperty.setProperty("bytom.clientURL", "");
        defaultProperty.setProperty("bytom.http.userAgent", "");
        defaultProperty.setProperty("bytom.user","");
        defaultProperty.setProperty("bytom.password","");
        defaultProperty.setProperty("bytom.http.useSSL", "false");
        defaultProperty.setProperty("bytom.http.proxyHost","");
        defaultProperty.setProperty("bytom.http.proxyHost.fallback", "http.proxyHost");
        defaultProperty.setProperty("bytom.http.proxyUser","");
        defaultProperty.setProperty("bytom.http.proxyPassword","");
        defaultProperty.setProperty("bytom.http.proxyPort","");
        defaultProperty.setProperty("bytom.http.proxyPort.fallback", "http.proxyPort");
        defaultProperty.setProperty("bytom.http.connectionTimeout", "20000");
        defaultProperty.setProperty("bytom.http.readTimeout", "120000");
        defaultProperty.setProperty("bytom.http.retryCount", "3");
        defaultProperty.setProperty("bytom.http.retryIntervalSecs", "10");
        defaultProperty.setProperty("bytom.async.numThreads", "1");
        defaultProperty.setProperty("bytom.clientVersion", Version.getVersion());
       
        String bytom4jProps = "bytom.properties";
        boolean ploaded = loadProperties(defaultProperty, "." + File.separatorChar + bytom4jProps) ||
                loadProperties(defaultProperty, Configuration.class.getResourceAsStream("/WEB-INF/" + bytom4jProps)) ||
                loadProperties(defaultProperty, Configuration.class.getResourceAsStream("/" + bytom4jProps));
    }

    private static boolean loadProperties(Properties props, String path) {
        try {
            File file = new File(path);
            if(file.exists() && file.isFile()){
                props.load(new FileInputStream(file));
                return true;
            }
        } catch (Exception ignore) {
        }
        return false;
    }

    private static boolean loadProperties(Properties props, InputStream is) {
        try {
            props.load(is);
            return true;
        } catch (Exception ignore) {
        }
        return false;
    }

    public static boolean useSSL() {
        return getBoolean("bytom.http.useSSL");
    }
    public static String getScheme(){
        return useSSL() ? "https://" : "http://";
    }

    public static String getCilentVersion() {
        return getProperty("bytom.clientVersion");
    }

    public static String getCilentVersion(String clientVersion) {
        return getProperty("bytom.clientVersion", clientVersion);
    }

    public static String getSource() {
        return getProperty("bytom.source");
    }

    public static String getSource(String source) {
        return getProperty("bytom.source", source);
    }

    public static String getProxyHost() {
        return getProperty("bytom.http.proxyHost");
    }

    public static String getProxyHost(String proxyHost) {
        return getProperty("bytom.http.proxyHost", proxyHost);
    }

    public static String getProxyUser() {
        return getProperty("bytom.http.proxyUser");
    }

    public static String getProxyUser(String user) {
        return getProperty("bytom.http.proxyUser", user);
    }

    public static String getClientURL() {
        return getProperty("bytom.clientURL");
    }

    public static String getClientURL(String clientURL) {
        return getProperty("bytom.clientURL", clientURL);
    }

    public static String getProxyPassword() {
        return getProperty("bytom.http.proxyPassword");
    }

    public static String getProxyPassword(String password) {
        return getProperty("bytom.http.proxyPassword", password);
    }

    public static int getProxyPort() {
        return getIntProperty("bytom.http.proxyPort");
    }

    public static int getProxyPort(int port) {
        return getIntProperty("bytom.http.proxyPort", port);
    }

    public static int getConnectionTimeout() {
        return getIntProperty("bytom.http.connectionTimeout");
    }

    public static int getConnectionTimeout(int connectionTimeout) {
        return getIntProperty("bytom.http.connectionTimeout", connectionTimeout);
    }

    public static int getReadTimeout() {
        return getIntProperty("bytom.http.readTimeout");
    }

    public static int getReadTimeout(int readTimeout) {
        return getIntProperty("bytom.http.readTimeout", readTimeout);
    }

    public static int getRetryCount() {
        return getIntProperty("bytom.http.retryCount");
    }

    public static int getRetryCount(int retryCount) {
        return getIntProperty("bytom.http.retryCount", retryCount);
    }

    public static int getRetryIntervalSecs() {
        return getIntProperty("bytom.http.retryIntervalSecs");
    }

    public static int getRetryIntervalSecs(int retryIntervalSecs) {
        return getIntProperty("bytom.http.retryIntervalSecs", retryIntervalSecs);
    }

    public static String getUser() {
        return getProperty("bytom.user");
    }

    public static String getUser(String userId) {
        return getProperty("bytom.user", userId);
    }

    public static String getPassword() {
        return getProperty("bytom.password");
    }

    public static String getPassword(String password) {
        return getProperty("bytom.password", password);
    }

    public static String getUserAgent() {
        return getProperty("bytom.http.userAgent");
    }

    public static String getUserAgent(String userAgent) {
        return getProperty("bytom.http.userAgent", userAgent);
    }

    public static boolean getBoolean(String name) {
        String value = getProperty(name);
        return Boolean.valueOf(value);
    }

    public static int getIntProperty(String name) {
        String value = getProperty(name);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    public static int getIntProperty(String name, int fallbackValue) {
        String value = getProperty(name, String.valueOf(fallbackValue));
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    public static long getLongProperty(String name) {
        String value = getProperty(name);
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    public static String getProperty(String name) {
        return getProperty(name, null);
    }

    public static String getProperty(String name, String fallbackValue) {
        String value;
        try {
            value = System.getProperty(name, fallbackValue);
            if (null == value) {
                value = defaultProperty.getProperty(name);
            }
            if (null == value) {
                String fallback = defaultProperty.getProperty(name + ".fallback");
                if (null != fallback) {
                    value = System.getProperty(fallback);
                }
            }
        } catch (AccessControlException ace) { 
            value = fallbackValue;
        }
        return replace(value);
    }

    private static String replace(String value) {
        if (null == value) {
            return value;
        }
        String newValue = value;
        int openBrace = 0;
        if (-1 != (openBrace = value.indexOf("{", openBrace))) {
            int closeBrace = value.indexOf("}", openBrace);
            if (closeBrace > (openBrace + 1)) {
                String name = value.substring(openBrace + 1, closeBrace);
                if (name.length() > 0) {
                    newValue = value.substring(0, openBrace) + getProperty(name)
                            + value.substring(closeBrace + 1);

                }
            }
        }
        if (newValue.equals(value)) {
            return value;
        } else {
            return replace(newValue);
        }
    }

    public static int getNumberOfAsyncThreads() {
        return getIntProperty("bytom.async.numThreads");
    }

    public static boolean getDebug() {
        return getBoolean("bytom.debug");

    }
}
