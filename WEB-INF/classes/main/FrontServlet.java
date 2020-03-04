package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.annotation.MultipartConfig;

import controller.*;
import context.RequestContext;
import context.ResponseContext;

import exception.PresentationException;

@MultipartConfig(maxFileSize=100000000)

public class FrontServlet extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
		doPost(req,res);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
		req.setCharacterEncoding("UTF-8");
		try{
			ApplicationController app=new WebApplicationController();
			
			RequestContext reqc=app.getRequest(req);
			
			ResponseContext resc=app.handleRequest(reqc);
			
			resc.setResponse(res);
			
			app.handleResponse(reqc,resc);
		}catch(PresentationException e){
			throw new ServletException(e.getMessage(),e);
		}
	}
}