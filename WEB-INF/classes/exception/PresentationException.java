package exception;

public class PresentationException extends ApplicationException{
    public PresentationException(String mess,Throwable t){
        super(mess,t);
    }
}
