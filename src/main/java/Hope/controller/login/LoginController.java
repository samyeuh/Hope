package Hope.controller.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final LoginService loginService;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


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
            logger.error("La connexion a eu un problème");
            model.addAttribute("loginError", true);
        }
        if (sessionExpired != null) {
            logger.error("La session a expiré");
            model.addAttribute("sessionExpired", true);
        }
        return "loginPage";
    }


    @PostMapping("/do-login")
    public String processLogin(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        logger.info("Tentative de connexion pour l'utilisateur '{}'", username);
        if (loginService.login(username, password)) {
            logger.info("Connexion réussie pour l'utilisateur '{}'", username);
            return "redirect:/home";
        } else {
            logger.warn("Échec de connexion pour l'utilisateur '{}'", username);
            model.addAttribute("loginError", true);
            return "loginPage";
        }
    }
}
