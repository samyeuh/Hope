package Hope.controller.tool;

import Hope.controller.feedback.FeedbackService;
import Hope.controller.home.HomeService;
import Hope.exceptions.UnauthorizedActionException;
import Hope.model.Tool;
import Hope.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class ToolController {

    private final ToolService toolService;
    private final FeedbackService feedbackService;
    private final HomeService homeService;
    private static final Logger logger = LoggerFactory.getLogger(ToolController.class);

    @Autowired
    public ToolController(ToolService toolService, FeedbackService feedbackService, HomeService homeService) {
        this.toolService = toolService;
        this.feedbackService = feedbackService;
        this.homeService = homeService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/mainData")
    public String showAllMainData(Model model) {
        logger.info("Chargement de la page d'accueil");
        List<Tool> dataList = toolService.getAllMainTool();
        model.addAttribute("dataList", dataList);
        return "home";
    }

    @GetMapping("/details/{id}")
    public String getDataById(@PathVariable int id, Model model, Principal principal) {

        User user = homeService.getUser(principal.getName());
        if (user == null) {
            logger.warn("Accès à la page de détails sans utilisateur connecté.");
            return "redirect:/login";
        }

        Tool data = toolService.getTool(id);
        logger.info("Chargement de la page de détails pour l'outil ID: {}", id);
        model.addAttribute("data", data);
        model.addAttribute("comments", feedbackService.getComments(id));
        model.addAttribute("isAdmin", user.getRole().equals("admin"));
        model.addAttribute("username", user.getFirstName() + " " + user.getLastName());

        return "details";
    }

    @GetMapping("/update/{id}")
    public String updateDataById(@PathVariable int id, Model model, Principal principal) {
        User user = homeService.getUser(principal.getName());
        if (!user.getRole().equals("admin")) {
            logger.error("Tentative de modification d'un outil par un utilisateur non autorisé.");
            throw new UnauthorizedActionException("You are not allowed to update this tool");
        }
        Tool data = toolService.getTool(id);
        model.addAttribute("data", data);
        model.addAttribute("username", user.getFirstName() + " " + user.getLastName());

        return "update";
    }

    @PostMapping("/updateData/{id}")
    public String updateData(@PathVariable int id, @ModelAttribute Tool dataObj, Principal principal) {
        User user = homeService.getUser(principal.getName());
        if (!user.getRole().equals("admin")) {
            logger.error("Tentative de modification d'un outil par un utilisateur non autorisé.");
            throw new UnauthorizedActionException("You are not allowed to update this tool");
        }
        dataObj.setId(id);
        toolService.updateTool(dataObj);
        logger.info("Modification de l'outil ID: {} effectuée", id);
        return "redirect:/details/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteDataById(@PathVariable int id, Principal principal) {
        User user = homeService.getUser(principal.getName());
        if (!user.getRole().equals("admin")) {
            logger.error("Tentative de suppression d'un outil par un utilisateur non autorisé.");
            throw new UnauthorizedActionException("You are not allowed to update this tool");
        }
        toolService.deleteTool(id);
        logger.info("Suppression de l'outil ID: {} effectuée", id);
        return "redirect:/mainData";
    }

    @GetMapping("/addElement")
    public String addElementPage(Model model, Principal principal){
        User user = homeService.getUser(principal.getName());
        if (!user.getRole().equals("admin")) {
            logger.error("Tentative d'ajout d'un outil par un utilisateur non autorisé.");
            throw new UnauthorizedActionException("You are not allowed to update this tool");
        }
        Tool tool = new Tool();
        model.addAttribute("newTool", tool);
        model.addAttribute("username", user.getFirstName() + " " + user.getLastName());
        return "addElement";
    }

    @PostMapping("/submissionAddElement")
    public String addElement(@ModelAttribute("newTool") Tool tool, Principal principal){
        User user = homeService.getUser(principal.getName());
        if (!user.getRole().equals("admin")) {
            logger.error("Tentative d'ajout d'un outil par un utilisateur non autorisé.");
            throw new UnauthorizedActionException("You are not allowed to update this tool");
        }
        toolService.addTool(tool);
        logger.info("Ajout d'un nouvel outil effectué");
        return "redirect:/home";
    }
}
