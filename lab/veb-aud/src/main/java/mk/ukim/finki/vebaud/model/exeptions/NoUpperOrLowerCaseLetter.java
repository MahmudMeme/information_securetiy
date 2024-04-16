package mk.ukim.finki.vebaud.model.exeptions;

public class NoUpperOrLowerCaseLetter extends RuntimeException {
    public NoUpperOrLowerCaseLetter() {
        super("your password needs to have at least one upper and one lower letter");
    }
}
