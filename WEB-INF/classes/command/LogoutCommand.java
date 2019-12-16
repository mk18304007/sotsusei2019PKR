/*----------���O�A�E�g���邽�߂̃R�}���h----------*/
package command;

import util.SessionManager;

import context.RequestContext;
import context.ResponseContext;

public class LogoutCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		RequestContext reqc=getRequestContext();
		SessionManager session=new SessionManager(reqc);
		session.invalidate();
		resc.setTarget("index");
		return resc;
	}
}