package controller;

import java.util.Map;

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
		
		req.setAttribute("data",resc.getResult());
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