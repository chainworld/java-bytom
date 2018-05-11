package bytom.key;

import org.apache.log4j.Logger;

import bytom.api.KeysApi;
import bytom.model.BytomException;
import bytom.model.BytomResponse;
import bytom.model.Key;
import bytom.org.json.JSONException;

/**
 * 
 * @author https://github.com/JackyKen
 *
 */
public class CreateKey {
	
	static Logger log = Logger.getLogger(CreateKey.class.getName());

	public static void main(String[] args) {
		KeysApi k = new KeysApi();
		try {
			BytomResponse reponse = k.createKey("jackyken8", "123456");
			if (reponse.getStatus().equals("success")) {
				Key key = new Key(reponse.getData());
				log.debug(key.getAlias());
				log.debug(key.getFile()); 
				log.debug(key.getXpub());
			}
			if (reponse.getStatus().equals("fail")) {
				log.debug(reponse.getMsg());
			}
		}
		catch (BytomException e) {
			e.printStackTrace();
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
