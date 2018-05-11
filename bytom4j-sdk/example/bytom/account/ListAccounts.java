package bytom.account;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.log4j.Logger;

import bytom.api.AccountsApi;
import bytom.model.Account;
import bytom.model.BytomException;
import bytom.model.BytomResponse;
import bytom.org.json.JSONArray;
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
		AccountsApi accounts = new AccountsApi();
		try {
			BytomResponse result = accounts.getListAccounts();
			if ("success".equals(result.getStatus())) {
				String data = result.getData();
				JSONArray array = new JSONArray(data);
				List<Account> list = Account.constructAccounts(array);
				for (Account account : list) {
					log.debug(account.getAlias());
					log.debug(account.getId());
					log.debug(account.getKey_index());
					log.debug(account.getQuorom());
					log.debug(account.getXpubs());
				}
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
