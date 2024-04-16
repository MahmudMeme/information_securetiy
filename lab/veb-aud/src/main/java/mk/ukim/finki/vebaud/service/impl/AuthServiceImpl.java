package mk.ukim.finki.vebaud.service.impl;

import mk.ukim.finki.vebaud.model.User;
import mk.ukim.finki.vebaud.model.exeptions.InvalidArgumentException;
import mk.ukim.finki.vebaud.model.exeptions.InvalidUserCredentialsException;
import mk.ukim.finki.vebaud.model.exeptions.PasswordDoNotMatchException;
import mk.ukim.finki.vebaud.model.exeptions.UsernameNotFoundException;
import mk.ukim.finki.vebaud.repository.InMemoryUserRepository;
import mk.ukim.finki.vebaud.repository.UserRepository;
import mk.ukim.finki.vebaud.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentException();
        }
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        String salt = user.getSalt();
        String hashedPassword = UserServiceImpl.generateHashPassword(password, salt);
        return userRepository.findByUsernameAndPassword(username, hashedPassword).orElseThrow(InvalidUserCredentialsException::new);
    }


}
