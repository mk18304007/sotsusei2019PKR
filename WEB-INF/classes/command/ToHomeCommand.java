/*----------Home画面へ移動するためのコマンド----------*/
package command;

import context.ResponseContext;

public class ToHomeCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		//転送先情報をセットする
		resc.setTarget("home");
		return resc;
	}
}