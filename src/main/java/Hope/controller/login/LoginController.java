package Hope.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping("/login")
    public String login(
            @RequestParam(value = "loginError", required = false) String loginError,
            @RequestParam(value = "sessionExpired", required = false) String sessionExpired,
            Model model
    ) {
        if (loginError != null) {
            model.addAttribute("loginError", true);
        }
        if (sessionExpired != null) {
            model.addAttribute("sessionExpired", true);
        }
        return "loginPage";
    }


    @PostMapping("/do-login")
    public String processLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model
    ) {
        if (loginService.login(username, password)) {
            return "redirect:/home";
        } else {
            model.addAttribute("loginError", true);
            return "loginPage";
        }
    }
}
