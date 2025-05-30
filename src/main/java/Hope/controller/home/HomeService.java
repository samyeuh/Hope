package Hope.controller.home;

import Hope.exceptions.ResourceNotFoundException;
import Hope.model.Tool;
import Hope.model.ToolRepository;
import Hope.model.User;
import Hope.model.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {

    private final UserRepository userRepository;
    private final ToolRepository toolRepository;

    public HomeService(UserRepository userRepository, ToolRepository toolRepository) {
        this.userRepository = userRepository;
        this.toolRepository = toolRepository;
    }

    public User getUser(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    }

    public List<Tool> getPreviewsData() {
        return toolRepository.findAllByVisible(true).orElse(null);
    }

    public List<Tool> searchData(String query) {
        return toolRepository.search(query);
    }
}
