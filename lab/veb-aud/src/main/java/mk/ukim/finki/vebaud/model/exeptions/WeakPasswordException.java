package mk.ukim.finki.vebaud.model.exeptions;

public class WeakPasswordException extends RuntimeException{
    public WeakPasswordException() {
        super("Your password has to be longer then 8, must have one digit, and one special character");
    }
}
