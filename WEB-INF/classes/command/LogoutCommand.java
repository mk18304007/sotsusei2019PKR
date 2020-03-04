/*----------ログアウトするためのコマンド----------*/
package command;

import util.SessionManager;

import context.RequestContext;
import context.ResponseContext;

import exception.business.BusinessLogicException;
import exception.business.ParameterInvalidException;

public class LogoutCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
		try{
			RequestContext reqc=getRequestContext();
			SessionManager session=new SessionManager(reqc);
			session.invalidate();
			resc.setTarget("index");
			return resc;
		}catch(NullPointerException e){
			throw new ParameterInvalidException("入力内容が足りません",e);
		}
	}
}