/*-----------ログイン画面へ移動するためのコマンド-----------*/
package command;

import context.ResponseContext;

import exception.business.BusinessLogicException;

public class ToLoginCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
		//転送先情報をセットする
		resc.setTarget("login");
		return resc;
	}
}