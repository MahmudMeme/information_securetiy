package mk.ukim.finki.vebaud.service;

import mk.ukim.finki.vebaud.model.User;

public interface AuthService {

    User login(String username, String password);

}
