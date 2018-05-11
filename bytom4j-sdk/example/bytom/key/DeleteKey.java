package bytom.key;

import org.apache.log4j.Logger;

import bytom.api.KeysApi;
import bytom.model.BytomException;
import bytom.model.BytomResponse;
import bytom.org.json.JSONException;

/**
 * 
 * @author https://github.com/JackyKen
 *
 */
public class DeleteKey {
	static Logger log = Logger.getLogger(DeleteKey.class.getName());
	public static void main(String[] args) {
		KeysApi k = new KeysApi(); 
		try {
			String xpub="0bfeeeda0722d9dc95548b75cfc4a294cf425ddf68c363d21aefd3ff5a862fd8c5e93f0108442e33a2da384f788ed945c0c29d376ee57ddbb03e1a27ff3c115e";
			String password="123456";
			BytomResponse reponse = k.deleteKey(xpub, password); 
			log.debug("getStatus:"+reponse.getStatus());  
			log.debug("getMsg:"+reponse.getMsg()); 
		}
		catch (BytomException e) {
			e.printStackTrace();
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
