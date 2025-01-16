package Hope.controller.home;

import Hope.controller.tool.ToolService;
import Hope.model.Tool;
import Hope.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    private final HomeService homeService;
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final ToolService toolService;


    public HomeController(HomeService homeService, ToolService toolService) {
        this.homeService = homeService;
        this.toolService = toolService;
    }

    @RequestMapping("/home")
    public String home(Principal principal, Model model) {
        if (principal != null) {
            User user = homeService.getUser(principal.getName());
            logger.info("Chargement de la page d'accueil pour l'utilisateur '{}'", user.getUsername());
            model.addAttribute("username", user.getFirstName() + " " + user.getLastName());
        } else {
            logger.warn("Accès à la page d'accueil sans utilisateur connecté.");
            return "redirect:/login";
        }

        List<Tool> dataList = homeService.getPreviewsData();
        List<Tool> proposalList = toolService.getProposalTool();
        logger.info("Chargement des outils : {} outils récupérés", dataList.size());
        model.addAttribute("dataList", dataList);
        model.addAttribute("proposalList", proposalList);
        return "home";
    }

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Principal principal, Model model) {
        if (principal != null) {
            User user = homeService.getUser(principal.getName());
            logger.info("Utilisateur '{}' effectue une recherche avec le terme '{}'", user.getUsername(), query);
            model.addAttribute("username", user.getFirstName() + " " + user.getLastName());
        } else {
            logger.warn("Recherche effectuée sans utilisateur connecté.");
            return "redirect:/login";
        }

        if (query == null || query.isEmpty()) {
            logger.warn("Recherche vide effectuée, redirection vers la page d'accueil.");
            return "redirect:/home";
        }

        List<Tool> searchResults = homeService.searchData(query);
        logger.info("Recherche pour '{}': {} résultats trouvés", query, searchResults.size());
        model.addAttribute("dataList", searchResults);
        model.addAttribute("query", query);

        return "home";
    }

}
