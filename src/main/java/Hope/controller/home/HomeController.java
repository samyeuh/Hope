package Hope.controller.home;

import Hope.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController {

    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @RequestMapping("/home")
    public String home(Principal principal, Model model) {
        if (principal != null) {
            User user = homeService.getUser(principal.getName());
            model.addAttribute("username", user.getFirstName() + " " + user.getLastName());
        } else {
            return "redirect:/login";
        }
        return "home";
    }
}
