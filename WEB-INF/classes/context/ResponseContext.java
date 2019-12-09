package context;

public interface ResponseContext{
	public Object getResult();
	public String getTarget();
	public void setResult(Object bean);
	public void setTarget(String transferInfo);
	public void setResponse(Object res);
	public Object getResponse();
}