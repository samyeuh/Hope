package Hope.controller.login;

import Hope.exceptions.InvalidInputException;
import Hope.exceptions.ResourceNotFoundException;
import Hope.model.User;
import Hope.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
       this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public boolean login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidInputException("Invalid input provided.");
        }

        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        return passwordEncoder.matches(password, user.getPassword());
    }
}
