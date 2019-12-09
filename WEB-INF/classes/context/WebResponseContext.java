package context;

import javax.servlet.http.HttpServletResponse;

public class WebResponseContext implements ResponseContext{
	private Object _result;
	private String target;
	private HttpServletResponse _response;
	
	public void setTarget(String transferInfo){
		target="WEB-INF/jsp/"+transferInfo+".jsp";
	}
	public String getTarget(){
		return target;
	}
	public void setResult(Object bean){
		_result=bean;
	}
	public Object getResult(){
		return _result;
	}
	public Object getResponse(){
		return _response;
	}
	public void setResponse(Object res){
		_response=(HttpServletResponse)res;
	}
}
