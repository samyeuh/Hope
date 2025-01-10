package Hope.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "loginPage";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "loginPage";
    }

    @PostMapping("/login")
    public String processLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model
    ) {
        System.out.println("username: " + username + " password: " + password);
        if ("samy@".equals(username) && "123".equals(password)) {
            return "redirect:/home";
        } else {
            model.addAttribute("loginError", true);
            return "loginPage";
        }

    }
}
