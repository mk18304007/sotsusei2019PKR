/*----------ログアウトするためのコマンド----------*/
package command;

import java.util.Map;
import java.util.HashMap;

import context.ResponseContext;

public class LogoutCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		Map<String,String> result=new HashMap<String,String>();
		result.put("logout","logout");
		resc.setResult(result);
		resc.setTarget("index");
		return resc;
	}
}