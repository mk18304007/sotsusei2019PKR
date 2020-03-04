package controller;

import context.RequestContext;
import context.ResponseContext;

import exception.PresentationException;

public interface ApplicationController{
	RequestContext getRequest(Object request)throws PresentationException;
	ResponseContext handleRequest(RequestContext req)throws PresentationException;
	void handleResponse(RequestContext req,ResponseContext res)throws PresentationException;
}