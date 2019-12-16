/*----------ログインするためのコマンド---------*/
package command;

import context.ResponseContext;

public class ToProfileCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		//転送先情報をセットする
		resc.setTarget("profile");
		return resc;
	}
}