package bytom.key;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.log4j.Logger;

import bytom.api.KeysApi;
import bytom.model.BytomException;
import bytom.model.Key;
import bytom.org.json.JSONException;

/**
 * 
 * @author https://github.com/JackyKen
 *
 */
public class ListKeys {
	static Logger log = Logger.getLogger(ListKeys.class.getName());

	public static void main(String[] args) throws UnsupportedEncodingException,
			JSONException {
		KeysApi api = new KeysApi();
		try {
			List<Key> list = api.listKeys();
			for (Key k : list) {
				log.debug("alias:" + k.getAlias());
				log.debug("file:" + k.getFile());
				log.debug("xpub:" + k.getXpub());
			}
		}
		catch (BytomException e) {
			e.printStackTrace();
		}
	}
}
