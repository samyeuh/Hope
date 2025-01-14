package Hope.controller.signup;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignUpController {

    private final SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @RequestMapping("/signUp")
    public String signUp() {
        return "signUpPage";
    }

    @RequestMapping("/signUp-error")
    public String signUpError(Model model) {
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
            return "redirect:/login";
        } else {
            model.addAttribute("signUpError", true);
            return "signUpPage";
        }

    }
}
