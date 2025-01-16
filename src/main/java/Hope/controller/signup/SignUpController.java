package Hope.controller.signup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignUpController {

    private final SignUpService signUpService;
    private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);


    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @RequestMapping("/signUp")
    public String signUp() {
        return "signUpPage";
    }

    @RequestMapping("/signUp-error")
    public String signUpError(Model model) {
        logger.error("Erreur lors de l'inscription");
        model.addAttribute("signUpError", true);
        return "signUpPage";
    }

    @PostMapping("/signUp")
    public String processSignUp(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("first_name") String firstName,
            @RequestParam("last_name") String lastName,
            Model model
    ) {
        if (signUpService.signUp(username, password, firstName, lastName)) {
            logger.info("Inscription réussie pour l'utilisateur '{}'", username);
            return "redirect:/login";
        } else {
            logger.warn("Échec de l'inscription pour l'utilisateur '{}'", username);
            model.addAttribute("signUpError", true);
            return "signUpPage";
        }
    }
}
