/*----------プロフィール編集画面へ移動するためのコマンド----------*/
package command;

import context.ResponseContext;

public class ToProfileEditCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		//転送先情報をセットする
		resc.setTarget("profileEdit");
		return resc;
	}
}