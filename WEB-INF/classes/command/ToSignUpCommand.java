/*----------ユーザー登録画面に移動するためのコマンド----------*/
package command;

import context.ResponseContext;

public class ToSignUpCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		//転送先情報をセットする
		resc.setTarget("signup");
		return resc;
	}
}