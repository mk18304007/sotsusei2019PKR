package exception;

public class ApplicationException extends Exception{
	public ApplicationException(String mess,Throwable t){
		super(mess,t);
	}
}