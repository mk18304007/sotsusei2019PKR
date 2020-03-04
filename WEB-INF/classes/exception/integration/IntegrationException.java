package exception.integration;

import exception.business.BusinessLogicException;

public class IntegrationException extends BusinessLogicException{
    public IntegrationException(String mess,Throwable t){
        super(mess,t);
    }
}
