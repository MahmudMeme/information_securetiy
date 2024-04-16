package mk.ukim.finki.vebaud.service.impl;

import mk.ukim.finki.vebaud.model.Role;
import mk.ukim.finki.vebaud.model.User;
import mk.ukim.finki.vebaud.model.exeptions.*;
import mk.ukim.finki.vebaud.repository.InMemoryUserRepository;
import mk.ukim.finki.vebaud.repository.UserRepository;
import mk.ukim.finki.vebaud.service.UserService;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException(s));
    }


    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname/*, Role userRole*/) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidUserCredentialsException();
        if (!password.equals(repeatPassword))
            throw new PasswordDoNotMatchException();
        if (this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        if (!checkStrongPassword(password)) {
            throw new WeakPasswordException();
        }
        if (!hasUpperCaseAndLowerCase(password)) {
            throw new NoUpperOrLowerCaseLetter();
        }
        if (!isValidEmail(username)) {
            throw new EmailNotValidException(username);
        }
        //User user = new User(username,passwordEncoder.encode(password),name,surname/*,userRole*/);

        String salt = generateSalt();

        String hashedPassword = generateHashPassword(password, salt);

        User user = new User(username, hashedPassword, name, surname, salt, Role.USER);
        return userRepository.save(user);
    }

    @Override
    public List<User> AllUsersWIhtRoleUser() {
        Role role = Role.USER;
        List<User> userList = userRepository.findAllByUserRole(role);
        return userList;
    }

    @Override
    public void makeAdmin(Long id) {
        User user = userRepository.findById(id).get();
        user.setUserRole(Role.ADMIN);
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    public Boolean checkStrongPassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        if (!password.matches(".*\\d.*")) {
            return false;
        }
        if (!containsSpecialCharacter(password)) {
            return false;
        }
        return true;
    }

    private static boolean containsSpecialCharacter(String password) {
        Pattern specialCharPattern = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]");
        Matcher matcher = specialCharPattern.matcher(password);
        return matcher.find();
    }

    public Boolean isValidEmail(String email) {
        final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public Boolean hasUpperCaseAndLowerCase(String password) {
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            }

            if (hasUpperCase && hasLowerCase) {
                return true;
            }
        }
        return false;
    }

    public String generateSalt() {
        Random random = new Random();
        StringBuilder randomString = new StringBuilder(5);

        for (int i = 0; i < 5; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }

    public static String generateHashPassword(String password, String salt) {
        String full = password + salt;
        String generatedPassword;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(full.getBytes());

            byte[] bytes = md.digest();
            //hex
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return generatedPassword;
    }
}

