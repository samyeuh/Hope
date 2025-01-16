package Hope.controller.login;

import Hope.exceptions.InvalidInputException;
import Hope.exceptions.ResourceNotFoundException;
import Hope.model.User;
import Hope.model.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);


    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
       this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public boolean login(String username, String password) {
        logger.info("Vérification des informations de connexion pour l'utilisateur '{}'", username);
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            logger.error("Tentative de connexion avec des informations incomplètes.");
            throw new InvalidInputException("Invalid input provided.");
        }

        try {
            User user = userRepository.findUserByUsername(username)
                    .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
            logger.info("Utilisateur trouvé : '{}'. Vérification du mot de passe.", username);
            boolean match = passwordEncoder.matches(password, user.getPassword());
            if (match) {
                logger.info("Mot de passe valide pour l'utilisateur '{}'.", username);
            } else {
                logger.warn("Mot de passe incorrect pour l'utilisateur '{}'.", username);
            }
            return match;
        } catch (ResourceNotFoundException e) {
            logger.error("Utilisateur '{}' introuvable : {}", username, e.getMessage());
            throw e;
        }
    }

}
