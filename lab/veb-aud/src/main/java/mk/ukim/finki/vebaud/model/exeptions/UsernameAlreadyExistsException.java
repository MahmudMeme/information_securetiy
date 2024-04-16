package mk.ukim.finki.vebaud.model.exeptions;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String username) {
        super(String.format("imeto %s veke postoi", username));
    }
}
