package mk.ukim.finki.vebaud.model.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFundException extends RuntimeException {
    public UserNotFundException(String username) {
        super(String.format("User with username: %s was not found", username));
    }
}
