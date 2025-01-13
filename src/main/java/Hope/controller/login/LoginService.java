package Hope.controller.login;

import Hope.model.User;
import Hope.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    private final UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(String username, String password) {
        // TODO: fix the repository :/
       Optional<User> users = userRepository.findUserByUsername(username);
        if (users.isEmpty()){
            System.out.println("No user found with username: " + username);
            return false;
        }
        User user = users.get();
        boolean passwordMatch = user.getPassword().equals(password);
        return passwordMatch;

    }
}
