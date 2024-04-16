package mk.ukim.finki.vebaud.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.vebaud.model.*;
import mk.ukim.finki.vebaud.repository.UserRepository;
import mk.ukim.finki.vebaud.service.impl.UserServiceImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<User> users = null;
    private final UserRepository userRepository;

    public DataHolder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {

        users = new ArrayList<>();
        if (userRepository.count() == 0) {
            String mahmudPassword = "mm";
            String mahmudSalt = "haha";
            String hash = UserServiceImpl.generateHashPassword(mahmudPassword, mahmudSalt);
            users.add(new User("Mahmud.memdovski", hash, "Mahmud", "Memedovski", mahmudSalt, Role.GOD));
            String informaciskaPassword = "ib";
            String informaciskaSalt = "info";
            String hashInfo = UserServiceImpl.generateHashPassword(informaciskaPassword, informaciskaSalt);
            users.add(new User("Informaciska.bezbednostt", hashInfo, "Informaciksa", "Bezbednost", informaciskaSalt, Role.ADMIN));
            userRepository.saveAll(users);
        }
    }

}
