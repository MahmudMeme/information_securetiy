package mk.ukim.finki.vebaud.model.exeptions;

public class InvalidUserCredentialsException extends RuntimeException{
    public InvalidUserCredentialsException() {
        super("Invalid User Credentials Exception");
    }
}
