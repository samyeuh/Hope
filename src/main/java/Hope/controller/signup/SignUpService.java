package Hope.controller.signup;

import Hope.exceptions.InvalidInputException;
import Hope.exceptions.ResourceNotFoundException;
import Hope.exceptions.UserAlreadyExistsException;
import Hope.model.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignUpService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(SignUpService.class);

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
            logger.error("Tentative d'inscription avec de mauvaises informations.");
            throw new InvalidInputException("Invalid input provided.");
        }

        boolean userExists = userRepository.findUserByUsername(username).isPresent();
        if (userExists) {
            logger.error("Utilisateur déjà existant avec le nom d'utilisateur : '{}'", username);
            throw new UserAlreadyExistsException("User already exists with username: " + username);
        }

        String encodedPassword = passwordEncoder.encode(password);
        userRepository.insertUser(username, encodedPassword, firstName, lastName);
        userRepository.findUserByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        logger.info("Utilisateur '{}' inscrit avec succès.", username);
        return true;
    }

}
