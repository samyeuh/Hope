package Hope.controller.home;

import Hope.model.DataHope;
import Hope.model.DataRepository;
import Hope.model.User;
import Hope.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {

    private final UserRepository userRepository;
    private final DataRepository dataRepository;

    @Autowired
    public HomeService(UserRepository userRepository, DataRepository dataRepository) {
        this.userRepository = userRepository;
        this.dataRepository = dataRepository;
    }

    public User getUser(String username) {
        return userRepository.findUserByUsername(username).orElse(null);
    }

    public List<DataHope> getPreviewsData() {
        return dataRepository.findAll();
    }

    public List<DataHope> searchData(String query) {
        return dataRepository.search(query);
    }
}
