package controller;

import java.util.Map;
import java.util.List;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import context.RequestContext;
import context.ResponseContext;
import context.WebRequestContext;
import context.WebResponseContext;

import command.AbstractCommand;
import command.CommandFactory;

public class WebApplicationController implements ApplicationController{
	public RequestContext getRequest(Object request){
		RequestContext reqc=new WebRequestContext();
		
		reqc.setRequest(request);
		
		return reqc;
	}
	public ResponseContext handleRequest(RequestContext req){
		AbstractCommand command=CommandFactory.getCommand(req);
		
		command.init(req);
		
		ResponseContext resc=command.execute(new WebResponseContext());
		
		return resc;
	}
	public void handleResponse(RequestContext reqc,ResponseContext resc){
		HttpServletRequest req=(HttpServletRequest)reqc.getRequest();
		HttpServletResponse res=(HttpServletResponse)resc.getResponse();
		
		List list=(List)resc.getResult();
		if(list!=null){
			for(int i=0;i<list.size();i++){
				List result=(List)list.get(i);
				req.setAttribute((String)result.get(0),result.get(1));
			}
		}
		RequestDispatcher dis=req.getRequestDispatcher(resc.getTarget());
		try{
			dis.forward(req,res);
		}catch(ServletException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}