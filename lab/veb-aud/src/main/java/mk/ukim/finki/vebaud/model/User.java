package mk.ukim.finki.vebaud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "web_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    @Column(name = "user_password")
    private String password;

    private String name;

    private String surName;

    private String salt;

    @Enumerated(value = EnumType.STRING)
    private Role userRole;

    public User() {
    }

    public User(String username, String password, String name, String surName, String salt, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surName = surName;
        this.salt = salt;
        this.userRole = role;
    }
}
