package bytom.account;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import bytom.api.AccountsApi;
import bytom.model.Account;
import bytom.model.BytomException;
import bytom.model.BytomResponse;
import bytom.org.json.JSONException;

/**
 * 
 * @author https://github.com/JackyKen
 *
 */
public class CreateAccount {

	static Logger log = Logger.getLogger(CreateAccount.class.getName());

	public static void main(String[] args) throws UnsupportedEncodingException,
			JSONException {
		AccountsApi accounts = new AccountsApi();
		try {
			String[] root_xpubs = {
					"02581f1a2099e1696c498901c0049a22cc3e7f85db71c4ebb78f238d3ef8b323d2fd5c33b6f634aacdd25eb5e09c0c803077c521ef0524e4cc64d1a4420c8bc6",
					"02581f1a2099e1696c498901c0049a22cc3e7f85db71c4ebb78f238d3ef8b323d2fd5c33b6f634aacdd25eb5e09c0c803077c521ef0524e4cc64d1a4420c8bc7" };
			String alias = "bytommaster003";
			Integer quorum = 1;
			String access_token = "";
			BytomResponse result = accounts.createAccount(root_xpubs, alias, quorum,
					access_token);
			if ("success".equals(result.getStatus())) {
				Account account = new Account(result.getData());
				log.debug("id:" + account.getId());
				log.debug("alias:" + account.getAlias());
				log.debug("key_index:" + account.getKey_index());
				log.debug("quorom:" + account.getQuorom());
				log.debug("xpubs:" + account.getXpubs());
			}
			else {
				log.debug(result.getMsg());
			}
		}
		catch (BytomException e) {
			e.printStackTrace();
		}
	}
}
