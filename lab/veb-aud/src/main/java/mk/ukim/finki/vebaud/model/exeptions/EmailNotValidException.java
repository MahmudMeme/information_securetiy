package mk.ukim.finki.vebaud.model.exeptions;

public class EmailNotValidException extends RuntimeException {
    public EmailNotValidException(String email) {
        super(String.format(" email %s is not valid", email));
    }
}
