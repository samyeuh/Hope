package Hope.controller.feedback;

import Hope.controller.home.HomeService;
import Hope.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final HomeService homeService;
    private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);


    public FeedbackController(FeedbackService feedbackService, HomeService homeService) {
        this.feedbackService = feedbackService;
        this.homeService = homeService;
    }

    @PostMapping("/addComment/{id}")
    public String addComment(@PathVariable int id, String comment, Principal principal) {
        if (principal == null) {
            logger.warn("Tentative d'ajout d'un commentaire sans utilisateur connecté.");
            return "redirect:/login";
        }
        User user = homeService.getUser(principal.getName());
        logger.info("Utilisateur '{}' ajoute un commentaire pour l'outil ID: {}", user.getUsername(), id);
        try {
            feedbackService.addComment(id, user, comment);
            logger.info("Commentaire ajouté avec succès par '{}' pour l'outil ID: {}", user.getUsername(), id);
        } catch (Exception e) {
            logger.error("Erreur lors de l'ajout d'un commentaire pour l'utilisateur '{}': {}", user.getUsername(), e.getMessage());
        }
        return "redirect:/details/" + id;
    }

}
