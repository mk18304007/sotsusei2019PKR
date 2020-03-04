package exception.business;

import exception.PresentationException;

public class BusinessLogicException extends PresentationException{
    public BusinessLogicException(String mess,Throwable t){
        super(mess,t);
    }
}
