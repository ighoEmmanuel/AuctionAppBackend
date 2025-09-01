package semicolon.africa.exceptions;

public class PasswordError extends RuntimeException {
    public PasswordError(String message){
        super(message);
    }
}
