package mk.ukim.finki.vebaud.model.exeptions;

public class ManufacturerNotFoundException extends RuntimeException {
    public ManufacturerNotFoundException(Long manufacturerId) {
        super(String.format("Manufacturer with id %d does not exist.", manufacturerId));
    }
}

