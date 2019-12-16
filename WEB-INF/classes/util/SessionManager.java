package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import context.RequestContext;
import context.ResponseContext;

public class SessionManager{
	private HttpServletRequest req;
	private HttpSession session;
	public SessionManager(RequestContext reqc){
		req=(HttpServletRequest)reqc.getRequest();
		session=req.getSession();
	}
	public void setAttribute(String name,Object result){		
		session.setAttribute(name,result);
	}
	public Object getAttribute(String name){
		return session.getAttribute(name);
	}
	public void invalidate(){
		session.invalidate();
	}
}