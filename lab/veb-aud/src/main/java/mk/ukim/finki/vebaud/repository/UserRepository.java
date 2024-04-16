package mk.ukim.finki.vebaud.repository;

import mk.ukim.finki.vebaud.model.Role;
import mk.ukim.finki.vebaud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);
    List<User> findAllByUserRole(Role role);
}
