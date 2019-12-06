/*----------ユーザー登録画面に移動するためのコマンド----------*/
package command;

import java.io.IOException;
import context.ResponseContext;

public class ToSignupCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		//転送先情報をセットする
		resc.setTarget("signup");
		return resc;
	}
}