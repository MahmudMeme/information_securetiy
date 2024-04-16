package mk.ukim.finki.vebaud.service;

import mk.ukim.finki.vebaud.model.Role;
import mk.ukim.finki.vebaud.model.User;

import java.util.List;

public interface UserService {
    User register(String username, String password, String repeatPassword, String name, String surname/*, Role role*/);

    List<User> AllUsersWIhtRoleUser();

    void makeAdmin(Long id);
    List<User> findAll();
}
