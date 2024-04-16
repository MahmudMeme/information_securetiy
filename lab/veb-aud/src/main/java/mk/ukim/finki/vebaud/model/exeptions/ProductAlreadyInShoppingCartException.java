package mk.ukim.finki.vebaud.model.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class ProductAlreadyInShoppingCartException extends RuntimeException {
    public ProductAlreadyInShoppingCartException(Long id, String username) {
        super(String.format("product with id: %d already exist for user with username: %s", id, username));
    }
}
