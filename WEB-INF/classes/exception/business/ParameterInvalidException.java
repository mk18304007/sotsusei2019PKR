package exception.business;

public class ParameterInvalidException extends BusinessLogicException{
	public ParameterInvalidException(String mess,Throwable t){
		super(mess,t);
	}
}
