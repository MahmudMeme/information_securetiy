package mk.ukim.finki.vebaud.model.exeptions;

public class UsernameNotFoundException extends RuntimeException{
    public UsernameNotFoundException(String username) {
        super(String.format("nemoze da se najde imeto %s",username));
    }
}
