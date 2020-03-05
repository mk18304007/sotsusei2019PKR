/*-----------へ移動するためのコマンド-----------*/
package command;

import context.ResponseContext;

import exception.business.BusinessLogicException;

public class ToBlockProfileCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
		//転送先情報をセットする
		resc.setTarget("blockProfile");
		return resc;
	}
}