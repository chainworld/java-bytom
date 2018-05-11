package bytom.account;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.log4j.Logger;

import bytom.api.AccountsApi;
import bytom.model.Account;
import bytom.model.BytomException;
import bytom.org.json.JSONException;

/**
 * 
 * @author https://github.com/JackyKen
 *
 */
public class ListAccounts {
	static Logger log = Logger.getLogger(ListAccounts.class.getName());

	public static void main(String[] args) throws UnsupportedEncodingException,
			JSONException {
		AccountsApi api = new AccountsApi();
		try {
			List<Account> list = api.listAccounts();
			for (Account account : list) {
				log.debug(account.getAlias());
				log.debug(account.getId());
				log.debug(account.getKey_index());
				log.debug(account.getQuorom());
				log.debug(account.getXpubs());
			}
		}
		catch (BytomException e) {
			e.printStackTrace();
		}
	}
}
