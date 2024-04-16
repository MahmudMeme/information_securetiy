package mk.ukim.finki.vebaud.model.exeptions;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(Long categoryId) {
        super(String.format("Category with id %d does not exist.", categoryId));
    }
}

