package semicolon.africa.exceptions;

public class EmailError extends RuntimeException{
    public EmailError(String message){
        super(message);
    }
}
