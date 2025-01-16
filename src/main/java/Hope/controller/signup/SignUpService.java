package Hope.controller.signup;

import Hope.exceptions.InvalidInputException;
import Hope.exceptions.ResourceNotFoundException;
import Hope.exceptions.UserAlreadyExistsException;
import Hope.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignUpService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public SignUpService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Transactional
    public boolean signUp(String username, String password, String firstName, String lastName) {
        if (username == null || username.isEmpty() ||
                password == null || password.isEmpty() ||
                firstName == null || firstName.isEmpty() ||
                lastName == null || lastName.isEmpty()) {
            throw new InvalidInputException("Invalid input provided.");
        }

        boolean userExists = userRepository.findUserByUsername(username).isPresent();
        if (userExists) {
            throw new UserAlreadyExistsException("User already exists with username: " + username);
        }

        String encodedPassword = passwordEncoder.encode(password);

        userRepository.insertUser(username, encodedPassword, firstName, lastName);
        userRepository.findUserByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        return true;
    }

}
