package command;

import java.io.IOException;
import context.RequestContext;
import context.ResponseContext;
import exception.business.BusinessLogicException;

public abstract class AbstractCommand{ 
	private RequestContext _reqContext;
	public void init(RequestContext rc){
		_reqContext=rc;
	}
	public RequestContext getRequestContext(){
		return _reqContext;
	}
	public abstract ResponseContext execute(ResponseContext resc)throws BusinessLogicException;
}