/*----------投稿画面に移動するためのコマンド----------*/
package command;

import context.ResponseContext;

public class ToPostCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		//転送先情報をセットする
		resc.setTarget("post");
		return resc;
	}
}