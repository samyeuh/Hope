package Hope.controller.login;

import Hope.model.User;
import Hope.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    private final UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(String username, String password) {
        // TODO: fix the repository :/
        List<User> users = userRepository.findPasswordByUsername(username);
        System.out.println(users.toString() + " users found " + username + password);
        return !users.isEmpty();

    }
}
