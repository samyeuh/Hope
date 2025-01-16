package Hope.controller.tool;

import Hope.controller.feedback.FeedbackService;
import Hope.controller.home.HomeService;
import Hope.model.Tool;
import Hope.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ToolController {

    private final ToolService toolService;
    private final FeedbackService feedbackService;
    private final HomeService homeService;

    @Autowired
    public ToolController(ToolService toolService, FeedbackService feedbackService, HomeService homeService) {
        this.toolService = toolService;
        this.feedbackService = feedbackService;
        this.homeService = homeService;
    }

    @GetMapping("/")
    public String index() {
        // TODO: verifier si l'utilisateur est connecté
        return "redirect:/login";
    }

    @GetMapping("/mainData")
    public String showAllMainData(Model model) {
        List<Tool> dataList = toolService.getAllMainTool();
        model.addAttribute("dataList", dataList);
        return "home";
    }

    @GetMapping("/details/{id}")
    public String getDataById(@PathVariable int id, Model model, Principal principal) {
        Optional<Tool> data = toolService.getTool(id);
        User user = homeService.getUser(principal.getName());
        if (user == null) {
            return "redirect:/login";
        }
        if (data.isPresent()) {
            Tool dataObj = data.get();
            model.addAttribute("data", dataObj);
            model.addAttribute("comments", feedbackService.getComments(id));
            model.addAttribute("isAdmin", user.getRole().equals("admin"));
        }

        return "details";
    }

    @GetMapping("/update/{id}")
    public String updateDataById(@PathVariable int id, Model model) {
        Optional<Tool> data = toolService.getTool(id);
        if (data.isPresent()) {
            Tool dataObj = data.get();
            model.addAttribute("data", dataObj);
        }

        return "update";
    }

    @PostMapping("/updateData/{id}")
    public String updateData(@PathVariable int id, @ModelAttribute Tool dataObj) {
        dataObj.setId(id);
        toolService.updateTool(dataObj);

        return "redirect:/details/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteDataById(@PathVariable int id) {
        toolService.deleteTool(id);

        return "redirect:/mainData";
    }

}
