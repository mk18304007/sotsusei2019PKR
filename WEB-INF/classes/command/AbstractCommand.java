package command;

import java.io.IOException;
import context.RequestContext;
import context.ResponseContext;
import bean.UserProduct;

public abstract class AbstractCommand{
	private RequestContext _reqContext;
	private UserProduct product;
	public void init(RequestContext rc){
		_reqContext=rc;
	}
	public RequestContext getRequestContext(){
		return _reqContext;
	}
	public abstract ResponseContext execute(ResponseContext resc);
}