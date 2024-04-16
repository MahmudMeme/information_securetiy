package mk.ukim.finki.vebaud.model.exeptions;

public class PasswordDoNotMatchException extends RuntimeException{
    public PasswordDoNotMatchException() {
        super("Password Do No tMatch Exception");
    }
}
